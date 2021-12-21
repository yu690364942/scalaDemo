package com.example.scalaDemo.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.example.scalaDemo.entity.WhiteList
import com.example.scalaDemo.mapper.WhiteListMapper
import com.example.scalaDemo.service.WhiteListService
import org.springframework.stereotype.Service

/**
 * <p>TiTle: WhiteListServiceImpl</p>
 * <p>Description: WhiteListServiceImpl</p>
 * <p>Company: www.nbcb.cn</p>
 *
 * @author yhq
 * @version 1.0
 * @since 2021/12/20
 */
@Service
class WhiteListServiceImpl extends ServiceImpl[WhiteListMapper, WhiteList] with WhiteListService {


}
