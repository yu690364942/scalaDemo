package com.example.scalaDemo.basic.config

import com.example.scalaDemo.basic.interceptor.UserInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{InterceptorRegistry, WebMvcConfigurer}


@Configuration
class InterceptorConfig extends WebMvcConfigurer {

  override def addInterceptors(registry: InterceptorRegistry): Unit = {
    registry.addInterceptor(new UserInterceptor).addPathPatterns("/**").excludePathPatterns("/user/**/*")
    super.addInterceptors(registry)
  }

}
