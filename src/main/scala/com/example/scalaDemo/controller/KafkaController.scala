package com.example.scalaDemo.controller

import org.apache.kafka.clients.producer._
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.web.bind.annotation.{RequestMapping, RestController}


@RestController
@RequestMapping(Array("kafka"))
@ConditionalOnBean(Array(classOf[KafkaProducer[String, String]]))
class KafkaController(@Autowired producer: Producer[String, String]) {

  private val logger = LoggerFactory.getLogger(this.getClass)

  @RequestMapping(Array("sendMsg"))
  def sendMsg(msg: String): String = {
    val callback: Callback = (metadata: RecordMetadata, exception: Exception) => {
      if (exception == null) System.out.println("success->" + metadata.offset)
      else logger.error(exception.getMessage, exception)
    }
    producer.send(new ProducerRecord[String, String]("yuhaoqiang", msg), callback)
    "success"
  }
}
