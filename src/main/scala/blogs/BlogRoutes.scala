package blogs

import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext

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
final case class BlogRoutes(module: BlogModule)(implicit ec: ExecutionContext)  {
  /* val r = pathPrefix("v1") {
    pathPrefix("FoodBlogs") {
      pathPrefix("blogs") {
        get {
          pathEndOrSingleSlash {
            parameter("author") { author =>
              //onComplete(module.getAll(author))
            }
          } ~
          path(JavaUUID) { id =>
            .onComplete(module.getById(id))
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
  }*/
}
