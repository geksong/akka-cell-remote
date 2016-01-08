package org.geksong.akkacellremoteapi.msg

/**
 * Created by geksong on 16/1/8.
 */

case class CellStart()

/**
 * 远程actor通讯消息
 * @param name
 * @param age
 */
case class CellMessage(name: String, age: Int)

/**
 * 远程actor通讯终止消息
 * @param single
 */
case class CellDoneMessage(single: Int)
