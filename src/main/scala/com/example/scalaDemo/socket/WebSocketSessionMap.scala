package com.example.scalaDemo.socket

import com.example.scalaDemo.socket.WebSocketSessionMap.sessionMap
import org.apache.tomcat.websocket.WsSession
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.stereotype.Component
import org.springframework.web.socket.server.standard.ServerEndpointExporter

import javax.websocket.server.ServerEndpoint
import javax.websocket._
import scala.collection.mutable



@Component
@ServerEndpoint(value = "/test/many")
@ConditionalOnBean(Array(classOf[ServerEndpointExporter]))
object WebSocketSessionMap{
  val sessionMap: mutable.Map[String, Session] = mutable.Map[String,Session]()
  def sendMsg(httpSessionId: String,msg:String): Unit ={
    sessionMap(httpSessionId).getBasicRemote.sendText(msg)
  }

}
class WebSocketSessionMap {

  @OnOpen
  def onOpen(session: Session): Unit ={

    sessionMap += session.asInstanceOf[WsSession].getHttpSessionId -> session
  }

  @OnClose
  def onClose(session: Session): Unit ={
    sessionMap -= session.asInstanceOf[WsSession].getHttpSessionId
  }

  @OnMessage
  def onMessage(string: String): Unit ={

  }

  @OnError
  def onError(session: Session,error: Throwable): Unit = {
    error.printStackTrace()
  }

}
