package blogs

import akka.http.scaladsl.server.Directives._

/**
 * List of routes I need:
 * GET:
 * - All
 * - By Id
 *
 * POST:
 * - One
 *
 * PATCH:
 * - By Id
 *
 * Delete:
 * - By Id.
 */
class Routes extends NameFunctions {
  val routes = pathPrefix("v1") {
    pathPrefix("FoodBlogs") {
      pathPrefix("blogs") {
        get {
          pathEndOrSingleSlash {
            complete()
          } ~
          path(JavaUUID) { id =>
            complete(???)
          }
        } ~
        post {
          ???
        } ~
        patch {
          ???
        } ~
        delete {
          ???
        }
      }
    }
  }
}
