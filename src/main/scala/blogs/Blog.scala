package blogs

import java.time.LocalDateTime
import java.util.UUID

final case class Blog(title: String,
                      author: String,
                      text: String,
                      createdAt: LocalDateTime,
                      updatedAt: LocalDateTime,
                      deleted: Boolean,
                      id: UUID)


object Blog {
  final case class Create(title: String,
                          author: String,
                          text: String)

  final case class Fetch(title: Option[String] = None,
                         author: Option[String] = None,
                         text: Option[String] = None,
                         createdAt: Option[LocalDateTime] = None,
                         updatedAt: Option[LocalDateTime] = None,
                         deleted: Option[Boolean] = None,
                         id: Option[UUID] = None)

  final case class Update(title: Option[String] = None,
                          author: Option[String] = None,
                          text: Option[String] = None,
                          createdAt: Option[LocalDateTime] = None,
                          updatedAt: Option[LocalDateTime] = None,
                          deleted: Option[Boolean] = None,
                          id: Option[UUID] = None)
}