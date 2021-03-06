package com.example.scalaDemo.basic.config

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.context.annotation.{Bean, Configuration}

import java.util.Properties

@Configuration
@ConditionalOnExpression("${components.kafka:false}")
class KafkaConf {
  @Bean
  def getProducer: KafkaProducer[String, String] = {
    val props = new Properties
    //    props.put("bootstrap.servers", "hadoop1:9092,hadoop2:9092,hadoop3:9092")
    //    props.put("acks", "all")
    //    //重试次数
    //    props.put("retries", "1")
    //    //批次大小
    //    props.put("batch.size", "16384")
    //    //等待时间
    //    props.put("linger.ms", "1")
    //    //RecordAccumulator 缓冲区大小
    //    props.put("buffer.memory", "33554432")
    //    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    //    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    new KafkaProducer[String, String](props)
  }

  @Bean
  def getConsumer: KafkaConsumer[String, String] = {
    var properties = new Properties()
    //    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
    //      "hadoop1:9092,hadoop2:9092,hadoop3:9092");
    //    properties.put(ConsumerConfig.GROUP_ID_CONFIG,"KafkaConsumerDemo1");
    //    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
    //    properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");
    //    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
    //      "org.apache.kafka.common.serialization.IntegerDeserializer");
    //    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
    //      "org.apache.kafka.common.serialization.StringDeserializer");
    //    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
    new KafkaConsumer(properties)
  }
}
