// 10Case Classes.sc

// in default case parameters of case class are
// public val s

case class Point(x:Int,y:Int)
val p1 = Point(2,3)
val p2 = p1.copy(p1.y,p1.x)
println(p2)
