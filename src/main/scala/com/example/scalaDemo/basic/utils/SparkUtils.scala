package com.example.scalaDemo.basic.utils

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.stereotype.Component

import scala.collection.mutable

/**
 * <p>TiTle: SparkUtils</p>
 * <p>Description: SparkUtils</p>
 * <p>Company: www.nbcb.cn</p>
 *
 * @author yhq
 * @version 1.0
 * @since 2021/12/13
 */
@Component
@ConditionalOnExpression("${components.spark:false}")
class SparkUtils() {
  val sparkConf: SparkConf = new
      SparkConf().setMaster("local[*]").setAppName("StreamWordCount")
  //2.初始化 SparkStreamingContext
  val ssc = new StreamingContext(sparkConf, Seconds(4))
  val rddQueue = new mutable.Queue[RDD[String]]()
  val inputStream: InputDStream[String] = ssc.queueStream(rddQueue, oneAtATime = false)
  //5.处理队列中的 RDD 数据
  val mappedStream: DStream[(String, Int)] = inputStream.map((_, 1))
  val reducedStream: DStream[(String, Int)] = mappedStream.reduceByKey(_ + _)
  reducedStream.foreachRDD(item => {
    item.foreach(turtle => {
      val strings = turtle._1.split("_")
      //      rankListService.updateRankList(strings(0), Integer.parseInt(strings(1)), turtle._2)
    })
  })
  //7.启动任务
  //  ssc.start()

  def add(string: String): Unit = {
    rddQueue += ssc.sparkContext.makeRDD(List {
      string
    })
  }
}