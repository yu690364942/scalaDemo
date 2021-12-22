package com.example.scalaDemo.basic.utils

import com.example.scalaDemo.basic.utils.ExcelParseUtil.logger
import org.apache.poi.ooxml.util.SAXHelper
import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.xssf.eventusermodel.{ReadOnlySharedStringsTable, XSSFReader, XSSFSheetXMLHandler}
import org.apache.poi.xssf.model.StylesTable
import org.apache.poi.xssf.usermodel.XSSFComment
import org.slf4j.{Logger, LoggerFactory}
import org.xml.sax.{InputSource, SAXException}

import java.io.{IOException, InputStream}
import java.lang.reflect.Method
import java.util
import java.util.Date
import javax.xml.parsers.ParserConfigurationException


class ExcelParseUtil() {
  /**
   * 表格
   */
  private var table: Array[Array[AnyRef]] = _
  /**
   * 页面大小
   */
  private var pageSize = 0
  /**
   * 的数据行
   */
  private var dataFromRow = 0
  /**
   * 消费者
   */
  private var consumer: List[Array[AnyRef]] => Unit = _
  /**
   * 输入流
   */
  private var inputStream: InputStream = _
  /**
   * 处理程序映射
   */
  private var handlerMap: Map[String, (Int, String => AnyRef)] = _


  /**
   * 的数据行
   *
   * @param dataFromRow 的数据行
   * @return ExcelParseUtil<T>
   */
  def dataFromRow(dataFromRow: Int): ExcelParseUtil = {
    this.dataFromRow = dataFromRow
    this
  }


  /**
   * 页面大小
   *
   * @param pageSize 页面大小
   * @return ExcelParseUtil<T>
   */
  def pageSize(pageSize: Int): ExcelParseUtil = {
    this.pageSize = pageSize
    this.table = new Array[Array[AnyRef]](pageSize)
    this
  }


  def handlerMap(handlerMap: Map[String, (Int, String => AnyRef)]): ExcelParseUtil = {
    this.handlerMap = handlerMap
    this
  }


  /**
   * 解析
   *
   * @param inputStream 输入流
   * @return ExcelParseUtil<T>
   */
  def parse(inputStream: InputStream): ExcelParseUtil = {
    this.inputStream = inputStream
    this
  }

  /**
   * 然后
   *
   * @param consumer 消费者
   */
  def result(consumer: List[Array[AnyRef]] => Unit): Unit = {
    this.consumer = consumer
    try {
      val opcPackage = OPCPackage.open(inputStream)
      try {
        val xssfReader = new XSSFReader(opcPackage)
        val styles = xssfReader.getStylesTable
        val strings = new ReadOnlySharedStringsTable(opcPackage)
        inputStream = xssfReader.getSheetsData.next
        processSheet(styles, strings, inputStream)
      } catch {
        case e: Exception =>
          e.printStackTrace()
      } finally if (opcPackage != null) opcPackage.close()
    }
  }


  /**
   * 工艺过程卡
   *
   * @param styles           风格
   * @param strings          字符串
   * @param sheetInputStream 表输入流
   * @throws ParserConfigurationException 解析器配置异常
   * @throws SAXException                 saxexception
   */
  @throws[ParserConfigurationException]
  @throws[SAXException]
  private def processSheet(styles: StylesTable, strings: ReadOnlySharedStringsTable, sheetInputStream: InputStream): Unit = {
    val sheetParser = SAXHelper.newXMLReader
    sheetParser.setContentHandler(new XSSFSheetXMLHandler(styles, strings, new XmlSheetContentsHandler, false))
    try {
      sheetParser.parse(new InputSource(sheetInputStream))
      val list = table.filter(item => item != null).toList
      if (list.nonEmpty) consumer(list)
    } catch {
      case e@(_: RuntimeException | _: IOException) =>
        logger.error(e.getMessage, e)
    }
  }


  /**
   * <p>TiTle: XmlSheetContentsHandler</p>
   * <p>Description: Test XmlSheetContentsHandler</p>
   * <p>Company: www.nbcb.cn</p>
   *
   * @author yhq
   * @version 1.0.0
   * @since 2021/12/07 14:44
   */
  private class XmlSheetContentsHandler extends XSSFSheetXMLHandler.SheetContentsHandler {
    /**
     * 行
     */
    protected var row: Array[AnyRef] = _

    /**
     * 开始行
     *
     * @param rowNum 行num
     */
    override def startRow(rowNum: Int): Unit = {
      if (rowNum < dataFromRow) return
      try row = new Array(handlerMap.size)
      catch {
        case e: Exception =>
          e.printStackTrace()
          throw new RuntimeException(e)
      }
    }

    /**
     * 结束行
     *
     * @param rowNum 行num
     */
    override def endRow(rowNum: Int): Unit = { // 判断是否使用异常作为文件读取结束（有些Excel文件格式特殊，导致很多空行，浪费内存）
      // 添加数据到list集合
      if (row == null) return
      val rowIndex = (rowNum - dataFromRow) % pageSize
      table(rowIndex) = row
      if (rowIndex + 1 == pageSize) {
        consumer(table.toList)
        table = new Array[Array[AnyRef]](pageSize)
      }
    }

    /**
     * 细胞
     * 所有单元格数据转换为string类型，需要自己做数据类型处理
     *
     * @param cellReference  单元格索引
     * @param formattedValue 单元格内容（全部被POI格式化为字符串）
     * @param comment        评论
     */
    override def cell(cellReference: String, formattedValue: String, comment: XSSFComment): Unit = {
      if (row == null) return
      try {
        val s = cellReference.replaceAll("[0-9]*", "")
        if (handlerMap.contains(s)) {
          row(handlerMap(s)._1) = handlerMap(s)._2(formattedValue)
        }
      } catch {
        case e: Exception =>
          logger.error(e.getMessage, e: Any)
          throw new RuntimeException(e)
      }
    }
  }
}


object ExcelParseUtil {
  val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def apply(): ExcelParseUtil = new ExcelParseUtil()
}
