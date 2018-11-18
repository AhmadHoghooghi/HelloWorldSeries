// 16Generic Classes.sc
class Stack[A] {
  private var elements: List[A] = Nil

  def push(x: A) = {
    elements = x :: elements
  }

  def peek:A = elements.head

  def pop():A = {
    val head: A = elements.head
    elements = elements.tail
    head
  }
}

val intStack: Stack[Int] = new  Stack[Int]
intStack.push(10)
intStack.push(11)
println(intStack.pop())
println(intStack.pop())

