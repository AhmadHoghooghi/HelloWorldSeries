import java.io.{File, FileOutputStream, FileWriter}

object Ojb02UnifiedTypes {
  def main(args: Array[String]): Unit = {
    val fileList = List(
      "08Nested Methods.sc",
      "09Multiple Parameter Lists (Currying).sc",
      "10Case Classes.sc",
      "11Pattern Matching.sc",
      "12Singleton Objects.sc",
      "13Regular Expression Patterns.sc",
      "14Extractor Objects.sc",
      "15For Comprehensions.sc",
      "16Generic Classes.sc",
      "17Variances.sc",
      "18Upper Type Bounds.sc",
      "19Lower Type Bounds.sc",
      "20Inner Classes.sc",
      "21Abstract Types.sc",
      "22Compound Types.sc",
      "23Self-type.sc",
      "24Implicit Parameters.sc",
      "25Implicit Conversions.sc",
      "26Polymorphic Methods.sc",
      "27Type Inference.sc",
      "28Operators.sc",
      "29By-name Parameters.sc",
      "30Annotations.sc",
      "31Default Parameter Values.sc",
      "32Named Arguments.sc",
      "33Packages and Imports.sc"
    )
    fileList.foreach(fileName=>{
      val file = new File(fileName)
      val writer = new FileWriter(file)
      writer.write("// "+fileName)
      writer.close()
    })


  }

}
