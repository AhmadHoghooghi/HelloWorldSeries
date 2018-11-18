val x = 1+1
val y:Double = 1+1
// ?? whay says func: Unit = () what is meaning of "= ()"
// ~~ because () is the instace of Unit
//there is actually a singleton value of type Unit, written ().
// It carries no information
val func = println({
  val a = 3
  var b = 6
  b*b
})

val mult = (arg1:Int)=>arg1+arg1
//?? why this does not work?
//mult 1

//-----------------Functions some thing like lamdas in java
val func45 = ()=> ()
//method with no param
val noParmFunction = ()=>45 //noParmFunction: () => Int


//----------------- methods came with def
// in both mehtod and function every thing about body comes
//after = sign. but in methods before equal sign we will
// have def, param list and their types return type

def add(aa:Int,bb:Int):Int = aa+bb
add(2,3)

//mehtods with multiple param list

def addThenMultiply(a:Int,b:Int)(c:Int):Int = (a+b)*c
addThenMultiply(1,2)(3)

// methods with no arg
def name():String = System.getProperty("PATH")
println(name())


// simple class with two field and one method
class Greeting(prefix:String, suffix:String){
  def greet(name:String):Unit = {
    println(prefix+" "+ name+" "+suffix)
  }
}

val greeting = new Greeting("Hello","!")
greeting.greet("Scala Developer")

// simple case class
case class Point(x:Int,y:Int)

val p1 = Point(1,2)
val p2 = Point(1,2)
val p3 = Point(1,3)
println(p1==p2)
println(p1==p3)

// object
object Counter {
  private var value = 0
  def count():Int={
    value +=1
    value
  }
}

println(Counter.count())
println(Counter.count())

// traits

trait GreeterTrait {
  def greet(name:String):Unit
}

trait GreeterTraitWithDefaultImpl {
  def greet(name:String):Unit
}

class EnglishGreeter extends GreeterTrait{
  override def greet(name: String): Unit = println("Hi"+name)
}

//main method should always be in object not in class.