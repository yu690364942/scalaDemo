package com.example.scalaDemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class Start

object Start extends App {
  SpringApplication.run(classOf[Start])
}
