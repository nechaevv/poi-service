package com.github.nechaevv.poi.core

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.Config

import scala.concurrent.ExecutionContext

/**
  * Created by sgl on 25.04.16.
  */
trait ConfigurationComponent {
  def configuration: Config
}

trait ActorSystemComponent {
  implicit def actorSystem: ActorSystem
}

trait ActorMaterializerComponent {
  implicit def actorMaterializer: ActorMaterializer
}

trait ExecutionContextComponent {
  implicit def executionContext: ExecutionContext
}