package blogs

import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

import java.time.LocalDateTime
import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

final case class BlogRepository(client: MongoClient, database: MongoDatabase)(implicit ec: ExecutionContext) {
  val collection: MongoCollection[Blog] = database.getCollection[Blog]("Blogs")

  def getAll(author: String): Future[List[Blog.Fetch]] = {
    val filter = and(equal("author", author), equal("deleted", false))

    collection
      .find[Blog.Fetch](filter)
      .toFuture()
      .map(b => b.toList)
  }

  def getById(id: UUID): Future[Option[Blog]] = {
    val filter = and(equal("id", id), equal("deleted", false))

    collection
      .find[Blog](filter)
      .headOption()
  }

  def create(blog: Blog): Future[Boolean] = {
    collection
      .insertOne(blog)
      .toFuture()
      .map(_.wasAcknowledged())
  }

  def delete(id: UUID): Future[Boolean] = {
    val filter = and(equal("id", id), equal("deleted", false))
    val update = combine(set("deleted", true), set("updatedAt", LocalDateTime.now().withNano(0)))

    collection
      .updateOne(filter, update)
      .toFuture()
      .map(_.wasAcknowledged())
  }

}
