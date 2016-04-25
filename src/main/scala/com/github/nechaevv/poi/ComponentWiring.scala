package com.github.nechaevv.poi

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.github.nechaevv.poi.core._
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.ExecutionContext

/**
  * Created by sgl on 25.04.16.
  */
object ComponentWiring extends ConfigurationComponent
  with ActorSystemComponent
  with ActorMaterializerComponent
  with ExecutionContextComponent
  with HttpServerComponentImpl {

  override lazy val configuration: Config = ConfigFactory.load()
  override implicit lazy val actorSystem: ActorSystem = ActorSystem("POI", configuration)
  override implicit lazy val actorMaterializer: ActorMaterializer = ActorMaterializer()
  override implicit def executionContext: ExecutionContext = actorSystem.dispatcher

  override def httpServer: HttpServer = new HttpServerImpl

}
