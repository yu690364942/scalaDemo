package com.example.scalaDemo.basic.exception.handler

import com.example.scalaDemo.basic.exception.BusinessException
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler}

import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class GlobalExceptionHandler() {
  val log: Logger = LoggerFactory.getLogger(this.getClass)

  @ExceptionHandler()
  def handlerBusinessException(businessException: BusinessException, request: HttpServletRequest): ResponseEntity[String] = {
    log.error(request.getRequestURL.toString)
    log.error(businessException.getMessage, businessException)
    ResponseEntity.status(businessException.code).body(businessException.message)
  }

  /**
   * 获得远程主机IP地址
   *
   * @param request 请求
   * @return String
   */
  private def getRemoteHost(request: HttpServletRequest) = {
    var ip = request.getHeader("x-forwarded-for")
    if (ip == null || ip.isEmpty || "unknown".equalsIgnoreCase(ip)) ip = request.getHeader("Proxy-Client-IP")
    if (ip == null || ip.isEmpty || "unknown".equalsIgnoreCase(ip)) ip = request.getHeader("WL-Proxy-Client-IP")
    if (ip == null || ip.isEmpty || "unknown".equalsIgnoreCase(ip)) ip = request.getRemoteAddr
    if ("0:0:0:0:0:0:0:1" == ip) "127.0.0.1"
    else ip
  }
}
