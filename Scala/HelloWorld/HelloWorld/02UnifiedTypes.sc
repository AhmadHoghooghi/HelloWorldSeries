var a = null //a: Null = null
val paranteses = () //paranteses: Unit = ()

// type mismatch error
//var b:Int = null

//any val values are not nullable
//a pure expression does nothing in statement position
//var unit:Unit = null
// there is only one instance of unit
var unit2 :Unit = ()

// a list of any
val list = List(
  "String",
  325,
  'c',
  true,
  () => "return string of a function"
)
list.foreach(item=>println(item)+"\n")
