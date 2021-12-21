package com.example.scalaDemo.service

import org.springframework.web.multipart.MultipartFile

/**
 * <p>TiTle: FileService</p>
 * <p>Description: FileService</p>
 * <p>Company: www.nbcb.cn</p>
 *
 * @author yhq
 * @version 1.0
 * @since 2021/12/21
 */
trait FileService {
  def upload(multipartFile: MultipartFile): Unit
}
