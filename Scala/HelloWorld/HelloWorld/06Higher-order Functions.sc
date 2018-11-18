val salaries = Seq(2000, 7000, 4000)
val doubleSalary = (x:Int) => x*2
val newSalaries = salaries.map(doubleSalary)

val shortNewSalaries = salaries.map(x=>2*x)
val evenMoreShortNewSalaries = salaries.map(_*2)

object SalaryRaiser{
  private def promotion(salaries:List[Double],promotionFunction:Double=>Double): List[Double] ={
    salaries.map(promotionFunction).toList
  }

  def smallPromotion(salaries:List[Double]): List[Double] ={
    promotion(salaries,s=>s*1.1)
  }

  def greatPromotion(salaries:List[Double]):  List[Double] ={
    promotion(salaries,s=>s*math.log(s))
  }

  def hugePromotion(salaries:List[Double]):  List[Double] ={
    promotion(salaries,s=>s*s)
  }
}
val doubleSalaries = List(1.1, 2.2, 3.3)
val list = SalaryRaiser.smallPromotion(doubleSalaries)
println(list)