// 11Pattern Matching.sc

import java.util.Random




def matchStringResult() ={
  val random = new Random()

  var rand: Int = random.nextInt(10)

  rand match {
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case _ => "more"
  }
}

matchStringResult()

