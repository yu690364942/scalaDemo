package com.example.scalaDemo.controller

import com.example.scalaDemo.entity.WhiteList
import com.example.scalaDemo.service.WhiteListService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

import java.util

@RestController
@RequestMapping(Array("test"))
class TestController(val whiteListService: WhiteListService) {
  private val logger = LoggerFactory.getLogger(this.getClass)

  @RequestMapping(Array("test"))
  def test(a: String, b: String): util.List[WhiteList] = {
    logger.info("{},{}", a: Any, b: Any)
    whiteListService.list()
  }
}
