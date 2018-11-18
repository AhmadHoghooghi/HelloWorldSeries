// 13Regular Expression Patterns.sc

import scala.util.matching.Regex

val numberPattern:Regex = "[0-9]".r

var pass:String = "apassword0"
val maybeMatch = numberPattern.findFirstMatchIn(pass)
 maybeMatch match {
  case Some(_) => println("CorrectPassword")
  case None => println("Pass should have number")
}
