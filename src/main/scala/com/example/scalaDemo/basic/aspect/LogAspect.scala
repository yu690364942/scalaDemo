package com.example.scalaDemo.basic.aspect

import com.example.scalaDemo.basic.adapter.ReflectAdapter
import org.aspectj.lang.annotation.{Around, Aspect}
import org.aspectj.lang.reflect.MethodSignature
import org.aspectj.lang.{JoinPoint, ProceedingJoinPoint}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import java.util
import javax.servlet.http.HttpServletRequest

/**
 * <p>TiTle: LogAspect</p>
 * <p>Description: LogAspect</p>
 * <p>Company: www.nbcb.cn</p>
 *
 * @author yhq
 * @version 1.0
 * @since 2021/12/17
 */
@Aspect
@Component
class LogAspect(@Autowired val request: HttpServletRequest) {
  val log: Logger = LoggerFactory.getLogger(this.getClass)

  @Around("bean(*Controller)")
  @throws[Throwable]
  def joinPointAccess(joinPoint: ProceedingJoinPoint): Any = {
    val startTime = System.currentTimeMillis
    log.info("Request uri = [{}],method = [{}] is started": String, request.getRequestURI: Any, request.getMethod: Any)
    log.info("Request params = [{}]", getFieldsName(joinPoint))
    val retMsg = joinPoint.proceed
    log.info("Request retData = [{}]", retMsg)
    log.info("Request uri = [{}],method = [{}] is ended": String, request.getRequestURI: Any, request.getMethod: Any)
    log.info("request cost time {} ms", System.currentTimeMillis - startTime)
    retMsg
  }

  private def getFieldsName(joinPoint: JoinPoint) = {
    val paramMap = new util.HashMap[String, AnyRef]
    try {
      val sig = joinPoint.getSignature
      if (!sig.isInstanceOf[MethodSignature]) throw new IllegalArgumentException("该注解只能用于方法")
      val mSig: MethodSignature = sig.asInstanceOf[MethodSignature]
      val target = joinPoint.getTarget
      val currentMethod = ReflectAdapter.getMethod(target.getClass, mSig.getName, mSig.getParameterTypes)
      //     val currentMethod = target.getClass.getMethod(mSig.getName,mSig.getParameterTypes)
      val args = joinPoint.getArgs
      var i = 0
      for (p <- currentMethod.getParameters) {
        paramMap.put(p.getName, args({
          i += 1
          i - 1
        }))
      }
    } catch {
      case e: Exception =>
        log.error(e.getMessage, e)
    }
    paramMap
  }

}
