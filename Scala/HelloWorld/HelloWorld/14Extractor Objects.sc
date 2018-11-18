// 14Extractor Objects.sc

import scala.util.Random

object CustomerId {
  def apply(name:String) = s"$name--${Random.nextLong}"

  def unapply(arg: String): Option[String] = {
    val strings: Array[String] = arg.split("--")
    if(strings.tail.nonEmpty && strings.head.equals("ahmad")) Some(strings.head) else None
  }
  
}

val customerId1:String = CustomerId("ahmad")

customerId1 match {
  case CustomerId(name) => println(name)
  case _ =>println("culd not extract customer name")
}