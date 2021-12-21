package com.example.scalaDemo.basic.config

import org.apache.hadoop.fs.FileSystem
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.context.annotation.{Bean, Configuration}

import java.net.URI

@Configuration
@ConditionalOnExpression("${components.hdfs:false}")
class HdfsConf {
  @Bean
  def getFileSystem: FileSystem = {
    val configuration = new org.apache.hadoop.conf.Configuration()
    FileSystem.get(new URI("hdfs://172.16.3.44:9000"), configuration, "root")
  }
}
