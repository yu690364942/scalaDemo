package com.example.scalaDemo.basic.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.socket.server.standard.ServerEndpointExporter

@Configuration
@ConditionalOnExpression("${components.webSocket:false}")
class WebSocketConfig {
  @Bean
  def serverEndpointExporter: ServerEndpointExporter = {
    new ServerEndpointExporter()
  }

}
