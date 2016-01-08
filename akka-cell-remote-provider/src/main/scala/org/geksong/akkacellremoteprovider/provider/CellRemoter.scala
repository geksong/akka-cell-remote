package org.geksong.akkacellremoteprovider.provider

import akka.actor.Actor.Receive
import akka.actor.{Props, Actor, ActorSystem}
import org.geksong.akkacellremoteapi.msg.{CellDoneMessage, CellMessage}

/**
 * Created by geksong on 16/1/8.
 */
object CellRemoter {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem("CellRemoterSystem")
    val cellRemotActor = actorSystem.actorOf(Props[CellRemotActor], "CellRemotActor")
  }
}

class CellRemotActor extends Actor {
  override def receive: Receive = {
    case b: CellMessage => {
      println(s"get cell message name=${b.name}, age=${b.age}")
      sender() ! CellMessage("中文", 45)
    }
    case a: CellDoneMessage => {
      println(s"get done message single=${a.single}")
      context.system.terminate()
    }
  }
}
