package com.example.scalaDemo.controller

import org.apache.hadoop.fs.{FileSystem, Path}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
@RequestMapping(Array("hdfs"))
@ConditionalOnBean(Array(classOf[FileSystem]))
class HdfsController(@Autowired fileSystem: FileSystem) {
  /**
   * mkdir
   *
   * @return {  @code  String  }
   */
  @RequestMapping(Array("mkdir"))
  def mkdir(): String = {
    fileSystem.listFiles(new Path("/"), true)
    "success"
  }

}
