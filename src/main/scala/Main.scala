import blogs.{BlogModule, BlogRepository, BlogRoutes}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Directives.path

import scala.concurrent.{ExecutionContext, Future}
import handles.{Akka, Mongo}

import scala.io.StdIn

final class Main {
  def start(): Unit = {
    implicit val system: ActorSystem[Any] = ActorSystem(Behaviors.empty, "My-Blogsite")
    implicit val executionContext = system.executionContext

    for {
      //_ <- Future(Unit)
      //blogRepository = BlogRepository(mongo.client, mongo.database)
      //blogModule     = BlogModule(blogRepository)
      //blogRoutes     = BlogRoutes(blogModule)
      akka           = new Akka(blogRoutes)

    } yield akka.
  }
}

object Main extends App {
  new Main()
    .start()
}
