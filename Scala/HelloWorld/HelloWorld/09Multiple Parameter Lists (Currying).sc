// 09Multiple Parameter Lists (Currying).sc

def plusThenMultiply(a:Int,b:Int)(c:Int):Int = {
  (a+b)*c
}

val threeArg = plusThenMultiply(1,2)(3)
// missing argument list for method
//val twoArg = plusThenMultiply(1,2)


val numbers = List(1,2,3,4,5,6,7,8,9,10)
val result1 = numbers.foldLeft(0)(_+_)
//missing argument
// there should be a _ instead of second param list
val result2 = numbers.foldLeft(0) _
val result2val = result2(_+_)

//what is :+ operator
val numberFunc = numbers.foldLeft(List[Int]())_

val function: (List[Int], Int) => List[Int] = (xs, x) => xs :+ x * x
val squares = numberFunc(function)
