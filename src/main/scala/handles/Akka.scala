package handles

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import blogs.BlogRoutes

import scala.concurrent.ExecutionContext

//TODO Figure out how to get the Akka actor to run continuously.
final case class Akka(routes: BlogRoutes)(implicit ec: ExecutionContext, sys: ActorSystem[Any]) {
  Http()
    .newServerAt("localhost", 8080)
    .bind(routes.r)
    .flatMap(_.unbind())
}
