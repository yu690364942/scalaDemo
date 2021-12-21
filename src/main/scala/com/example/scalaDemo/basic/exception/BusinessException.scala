package com.example.scalaDemo.basic.exception

/**
 *
 */
class BusinessException extends RuntimeException {
  var code: Integer = _
  var message: String = _
  var data: Any = _
  var throwable: Throwable = _

  def this(code: Integer, message: String) {
    this
    this.code = code
    this.message = message
  }
}

object BusinessException {
  def apply = new BusinessException
}

