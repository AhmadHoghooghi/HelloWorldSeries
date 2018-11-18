class Point(private var x: Int, private var y: Int) {
  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
  }

  override def toString: String = s"Point($x,$y)"

}

val point = new Point(1, 2)
point.toString

//value z is not a member of Point
//point.z
//variable x can not be accessed
//point.x

// constructors with default value

class Point2(var x: Int = 0, var y: Int = 0)

var p2 = new Point2()
p2.x
p2.y

//----------- method with getters and setters
class EncPoint {
  private var _x: Int = 0
  private var _y: Int = 0
  private val bound: Int = 100

  def x = _x

  def y = _y

  private def printWarning = println("Out of bounds")

  def x_=(newX: Int): Unit = {
    if (newX < bound) _x = newX else printWarning
  }

  def y_=(newY: Int): Unit = {
    if (newY < bound) _y = newY else printWarning
  }

  override def toString: String = s"EncPoint($x,$y)"
}

val en = new EncPoint
en.x = 10
en.y = 101
println(en)



if (3 > 5)
  5
else
  ()