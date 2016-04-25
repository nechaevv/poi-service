package com.github.nechaevv.poi.core

import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.scaladsl.Sink
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * Created by sgl on 25.04.16.
  */

trait HttpServer {
  def start(route: Route)
  def stop()
}

trait HttpServerComponent {
  def httpServer: HttpServer
}

trait HttpServerComponentImpl extends HttpServerComponent {
  this: ConfigurationComponent
    with ActorSystemComponent
    with ActorMaterializerComponent
    with ExecutionContextComponent =>

  class HttpServerImpl extends HttpServer with LazyLogging {


    lazy val interface = configuration.getString("http.interface")
    lazy val port = configuration.getInt("http.port")
    var binding: Future[Http.ServerBinding] = _

    override def start(route: Route): Unit = {
      val serverSource = Http().bind(interface, port)
      val connectionHandler = Route.handlerFlow(route)
      binding = serverSource.to(Sink.foreach { conn =>
        logger.trace(s"HTTP connection from ${conn.remoteAddress}")
        conn.handleWith(connectionHandler)
      }).run()

      binding.onComplete {
        case Success(_) => logger.trace("HTTP server started")
        case Failure(err) => logger.error("HTTP server startup error", err)
      }

    }

    override def stop(): Unit = {
      val serverBinding = Await.result(binding, 1.minute)
      Await.result(serverBinding.unbind(), 1.minute)
      logger.trace("HTTP server stopped")
    }

  }

}