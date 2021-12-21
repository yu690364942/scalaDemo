package com.example.scalaDemo.basic.common

import java.util.concurrent.ConcurrentHashMap
import javax.servlet.http.HttpSession

object MySessionContext {


  val sessionMap = new ConcurrentHashMap[String, HttpSession]


  def addSession(session: HttpSession): Unit = {
    sessionMap.put(session.getId, session)
  }

  def delSession(session: HttpSession): Unit = {
    sessionMap.remove(session.getId)
  }

  def getSession(sessionId: String): HttpSession = {
    sessionMap.get(sessionId)
  }

}
