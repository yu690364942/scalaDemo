package com.example.scalaDemo.listener

import lombok.extern.slf4j.Slf4j
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

import java.util.Optional

@Component
@Slf4j
@ConditionalOnExpression("${components.kafka:false}")
class KafkaConsumerListener{
  @KafkaListener(topicPattern="yuhaoqiang")
  def  listen(record: ConsumerRecord[String,String] ): Unit = {
    val kafkaMessage = Optional.ofNullable(record.value)
    if (kafkaMessage.isPresent) {
      val message = kafkaMessage.get()
      println(message)
    }
  }
}
