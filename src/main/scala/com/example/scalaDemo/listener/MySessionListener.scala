package com.example.scalaDemo.listener

import com.example.scalaDemo.basic.common.MySessionContext
import org.springframework.stereotype.Component

import javax.servlet.annotation.WebListener
import javax.servlet.http.{HttpSessionEvent, HttpSessionListener}


@WebListener
@Component
class MySessionListener extends HttpSessionListener {
  override def sessionCreated(se: HttpSessionEvent): Unit = MySessionContext.addSession(se.getSession)

  override def sessionDestroyed(se: HttpSessionEvent): Unit = MySessionContext.delSession(se.getSession)
}
