package com.example.scalaDemo.entity

import com.baomidou.mybatisplus.annotation.{IdType, TableField, TableId, TableName}

import java.util.Date
import javax.persistence.{Entity, Id}

/**
 * <p>TiTle: WhiteList</p>
 * <p>Description: WhiteList</p>
 * <p>Company: www.nbcb.cn</p>
 *
 * @author yhq
 * @version 1.0
 * @since 2021/12/20
 */
@Entity
@TableName(value = "batchtest.white_list")
class WhiteList {
  @Id
  @TableId(value = "id", `type` = IdType.AUTO)
  private var id: Integer = _

  /**
   * 姓名
   */
  @TableField(value = "`name`")
  private var name: String = _

  /**
   * 电话
   */
  @TableField(value = "phone_num")
  private var phoneNum: String = _

  @TableField(value = "create_time") private var createTime: Date = _

  @TableField(value = "update_time") private var updateTime: Date = _


  def setId(id: Integer): Unit = {
    this.id = id
  }

  def getId: Integer = id

  def setName(name: String): Unit = {
    this.name = name
  }

  def getName: String = name

  def setPhoneNum(phoneNum: String): Unit = {
    this.phoneNum = phoneNum
  }

  def getPhoneNum: String = phoneNum

  def setUpdateTime(updateTime: Date): Unit = {
    this.updateTime = updateTime
  }

  def getUpdateTime: Date = updateTime

  def setCreateTime(createTime: Date): Unit = {
    this.createTime = createTime
  }

  def getCreateTime: Date = createTime


}
