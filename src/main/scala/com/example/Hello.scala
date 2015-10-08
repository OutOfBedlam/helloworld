package com.example

import akka.actor.ActorSystem
import akka.stream.scaladsl.Source
import akka.stream.{Materializer, ActorMaterializer}

import scala.concurrent.ExecutionContext

import scala.concurrent.duration._

object Hello extends App {
  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContext = system.dispatcher
  implicit val mat: Materializer = ActorMaterializer()

  final val Msg = "Hello, world!"
  println("Foo 4.0!!")

  Source(0.seconds, 1.second, Msg)
    .map(e => e.hashCode + System.currentTimeMillis())
    .runForeach(e => println(e + " v4.0"))

  system.awaitTermination()
}
