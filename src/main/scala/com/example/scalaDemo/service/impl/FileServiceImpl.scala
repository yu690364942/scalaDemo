package com.example.scalaDemo.service.impl

import com.example.scalaDemo.basic.utils.ExcelParseUtil
import com.example.scalaDemo.entity.WhiteList
import com.example.scalaDemo.service.FileService
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * <p>TiTle: FileServiceImpl</p>
 * <p>Description: FileServiceImpl</p>
 * <p>Company: www.nbcb.cn</p>
 *
 * @author yhq
 * @version 1.0
 * @since 2021/12/21
 */
@Service
class FileServiceImpl(@Autowired jdbcTemplate: JdbcTemplate) extends FileService {
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)

  @Async
  override def upload(multipartFile: MultipartFile): Unit = {
    ExcelParseUtil() parse multipartFile.getInputStream dataFromRow 1 pageSize 10001 handlerMap Map("B" -> (0, item => item), "C" -> (1, item => item)) result insertData
  }

  private def insertData(arr: List[Array[AnyRef]]): Unit = {
    val sql = "insert into white_list (name,phone_num) values (?,?)"
    import scala.collection.JavaConverters._
    jdbcTemplate batchUpdate(sql, arr.asJava)
  }
}
