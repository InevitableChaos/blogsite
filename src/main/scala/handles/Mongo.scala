package handles

import blogs.BlogRepository
import com.mongodb.MongoClientSettings
import org.mongodb.scala.{MongoClient, MongoDatabase, ServerAddress}
import org.mongodb.scala.connection.ClusterSettings

import scala.concurrent.ExecutionContext
import scala.jdk.CollectionConverters.SeqHasAsJava

final class Mongo(implicit ec: ExecutionContext) {
  val mongoClientSettings: MongoClientSettings =
    MongoClientSettings
      .builder()
      .applyToClusterSettings((builder: ClusterSettings.Builder) => builder.hosts(List(new ServerAddress("localhost", 27017)).asJava))
      .build()

  val client: MongoClient = MongoClient(mongoClientSettings)

  val database: MongoDatabase = client.getDatabase("Blogs")

  val blogRepository = BlogRepository(client, database)

}
