package com.example.scalaDemo.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.example.scalaDemo.entity.WhiteList
import org.apache.ibatis.annotations.Mapper

/**
 * <p>TiTle: WhiteListMapper</p>
 * <p>Description: WhiteListMapper</p>
 * <p>Company: www.nbcb.cn</p>
 *
 * @author yhq
 * @version 1.0
 * @since 2021/12/20
 */
@Mapper
trait WhiteListMapper extends BaseMapper[WhiteList] {

}
