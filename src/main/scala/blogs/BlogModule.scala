package blogs

import akka.actor.Status.{Failure, Status, Success}

import java.time.LocalDateTime
import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

//TODO Add in the ability to choose which fields are shown
final case class BlogModule(repository: BlogRepository)(implicit ec: ExecutionContext) {

  private def mapToBlog(body: Blog.Create): Blog = {
    Blog(
      title     = body.title,
      author    = body.author,
      text      = body.text,
      createdAt = LocalDateTime.now().withNano(0),
      updatedAt = LocalDateTime.now().withNano(0),
      deleted   = false,
      id        = UUID.randomUUID()
    )
  }


  def getAll(author: String): Future[List[Blog.Fetch]] = {
    repository
      .getAll(author)
  }

  def getById(id: UUID): Future[Status] = {
    repository
      .getById(id)
      .map {
        case None    => Failure(new Throwable("404 - The blog you requested was not found."))
        case Some(b) => Success(b)
      }
  }

  def post(body: Blog.Create): Future[Status] = {
    repository
      .create(mapToBlog(body))
      .map {
        case false => Failure(new Throwable("400 - Bad Request"))
        case true  => Success(None)
      }
  }

  def delete(id: UUID): Future[Status] = {
    repository
      .delete(id)
      .map {
        case false => Failure(new Throwable("400 - Bad Request"))\
        case true  => Success(None)
      }
  }

}
