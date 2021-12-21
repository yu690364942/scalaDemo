package com.example.scalaDemo.controller

import com.example.scalaDemo.service.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RestController}
import org.springframework.web.multipart.MultipartFile

/**
 * <p>TiTle: FileController</p>
 * <p>Description: FileController</p>
 * <p>Company: www.nbcb.cn</p>
 *
 * @author yhq
 * @version 1.0
 * @since 2021/12/21
 */
@RestController
@RequestMapping(Array("file"))
class FileController(@Autowired fileService: FileService) {
  @RequestMapping(Array("upload"))
  def uploadFile(file: MultipartFile): String = {
    fileService.upload(file)
    "success"
  }
}
