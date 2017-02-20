package org.apache.spark.examples

/**
  * Created by Forrest on 2017. 2. 17..
  */
object ScalaTest {
  case class Email(sourceEmail:String, title: String, body:String)
  def main(args: Array[String]): Unit = {

    var emailFromForrest = Email("Forrest@gmail.com", "Greetings From Forrest", "Hello, Forrest")
    println(emailFromForrest.sourceEmail)
    println(emailFromForrest.title)
    println(emailFromForrest.body)

  }

}
