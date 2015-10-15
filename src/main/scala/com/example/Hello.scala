package com.example

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.stream._
import akka.stream.scaladsl.Source

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object Hello extends App {
  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContext = system.dispatcher
  implicit val mat = ActorMaterializer()

  implicit val log: LoggingAdapter = Logging(system, this.getClass)

  final val Msg = "Hello, world!"

  Source(() => Iterator.from(0))
    .log("EMITTING ELEMENT")
    .buffer(1, OverflowStrategy.backpressure)
    .map { e => Thread.sleep(1000); e }
    .map {
      case e if e % 2 == 0 =>
        log.error(new RuntimeException("This is an error"), "This is an error")
        e
      case e => e
    }
    .runForeach { e =>
      log.debug(e.toString)
      log.info(e.toString)
      log.warning(e.toString)
      log.error(e.toString)
    }

  system.awaitTermination()
}
