import scala.collection.immutable
// 15For Comprehensions.sc

case class User(name:String, age:Int)

val users: List[User] = List(
  User("ahmad", 28),
  User("ghazal", 25),
  User("navid", 23),
  User("jira", 12),
  User("pascal", 32)
)
val userNameList: List[String] = for(user<-users if(user.age>=20) && user.age <30) yield user.name
userNameList.foreach(println)

def calculateTuples(n:Int,v:Int)={
  for(i<- 0 until n;
      j<- i until n if i+j == v)
    yield (i,j)
}

val tuples: immutable.IndexedSeq[(Int, Int)] = calculateTuples(10,10)