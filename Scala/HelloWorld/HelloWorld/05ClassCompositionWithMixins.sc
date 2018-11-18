abstract class A{
  val message:String
}

class B extends A{
  override val message: String = "I'm an Instance of B"
}

//trait extends abstract class!!
trait C extends A{
  def loudMessage:String=message.toUpperCase()
}
//boath of b and c has super type of A
class D extends B with C

val d = new D
d.message
d.loudMessage


//-----------------
abstract class AbsIterator{
  type T
  def hasNext():Boolean
  def next():T
}

class StringIterator(s:String)extends AbsIterator{
  private var i = 0
  override type T = Char

  override def hasNext() = i<s.length

  override def next() = {
    val ch = s.charAt(i)
    i +=1
    ch
  }
}

val si = new StringIterator("Ahmad")
while (si.hasNext()) println(si.next())

trait RichIterator extends AbsIterator{
  def foreach(f: T=>Unit) :Unit=while (hasNext()) f(next())
}


class RichStringIterator extends StringIterator("Scala") with RichIterator

val rsi = new RichStringIterator
rsi.foreach(println)

