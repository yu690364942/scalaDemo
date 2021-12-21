package com.example.scalaDemo.basic.interceptor

import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

@Component
class UserInterceptor extends HandlerInterceptor {

  override def preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean = {
    //    if (null == request.getSession.getAttribute("userInfo"))
    //      throw new BusinessException(401, "请登录")
    true
  }
}
