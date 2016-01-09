package org.geksong.akkacellremoteclient.client

import akka.actor.{Props, ActorSystem, Actor}
import akka.actor.Actor.Receive
import org.geksong.akkacellremoteapi.msg.{CellDoneMessage, CellMessage, CellStart}

/**
 * Created by geksong on 16/1/8.
 */
object CellLocal {
  def main(args: Array[String]): Unit = {
    System.setProperty("akka.remote.netty.port", "5152")
    val system = ActorSystem("LocalSystem")
    val localActor = system.actorOf(Props[CellLocalActor], name = "CellLocalActor") // the local actor
    localActor ! CellStart
  }
}

class CellLocalActor extends Actor {
  val remote = context.actorSelection("akka.tcp://CellRemoterSystem@127.0.0.1:5150/user/CellRemotActor")
  override def receive: Receive = {
    case CellStart => {
      println("game begin")
      remote ! CellMessage("geksong", 32)
    }
    case a: CellMessage => {
      println(s"get remote message name=${a.name}, age=${a.age}")
      remote ! CellDoneMessage(2)
      context.system.terminate()
    }
  }
}
