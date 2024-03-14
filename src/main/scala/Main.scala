import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.{Behaviors => Behaviours}
import akka.http.scaladsl.Http

import scala.concurrent.Future
import scala.io.StdIn

final class Main extends Routes {
  def start(): Unit = {
    implicit val system = ActorSystem(Behaviours.empty, "My-Blogsite")
    implicit val executionContext = system.executionContext

    val bindingFuture =
      Http()
        .newServerAt("localhost", 8080)
        .bind(routes)
    println("Server is running.")
    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}

object Main extends App {
   private def run: Unit = {
    new Main()
      .start()
  }

  run
}
