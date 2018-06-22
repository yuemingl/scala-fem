package edu.uta.scalafem.test
import edu.uta.futureye.util.container.ElementList
import edu.uta.futureye.util.container.ObjIndex
import java.util.ArrayList

class A[Int] {
  
}

class B extends A[Int] {
  
}

class C {
  
}
object Test {
  def fun(c: A[Int]) { }
  
  def main(args: Array[String]): Unit = {
    var e = new ElementList()
    var i = new ObjIndex()
    
    println(i.size())
//    for(ee <- i) {
//      
//    }
    
    val b = new B
    fun(b)

    
//    val o = new ObjIndex()
//    o.add(1)
//    o add 2
//    o foreach println _ //works
//    
//    val oo = new ArrayList[Integer]()
//    oo.add(10)
//    oo.add(20)
//    oo.foreach pringln _ //not works
    
  }

}