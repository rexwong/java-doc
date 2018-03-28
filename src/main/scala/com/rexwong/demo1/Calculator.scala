package com.rexwong.demo1

case class User(
                 id: Int,
                 firstName: String,
                 lastName: String,
                 age: Int,
                 gender: Option[String]
               )

object UserRepository {
  private val users = Map(1 -> User(1, "John", "Doe", 32, Some("male")),
    2 -> User(2, "Johanna", "Doe", 30, None))

  def findById(id: Int): Option[User] = users.get(id)

  def findAll = users.values
}
object Calculator{
  def main(args: Array[String]): Unit = {
    val user1 = UserRepository.findById(1)
    if (user1.isDefined) {
      println(user1.get.firstName)
    } // will print "John"
  }
}