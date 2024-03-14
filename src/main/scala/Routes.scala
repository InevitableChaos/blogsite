import akka.http.scaladsl.server.Directives._

class Routes extends NameFunctions {

  val routes = pathPrefix("v1") {
    pathPrefix("test") {
      path("blogsite") {
        get {
          complete(printWelcomeMessage)
        }
      }
    }
  }

}
