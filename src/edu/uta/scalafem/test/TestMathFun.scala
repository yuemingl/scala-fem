package edu.uta.scalafem.test

import edu.uta.scalafem.function.operator.FMath._
import edu.uta.scalafem.function.Variable
import edu.uta.scalafem.function.MathFun
import edu.uta.scalafem.function.MathVectorFun

object TestMathFun {

  def main(args: Array[String]): Unit = {
    //f1 = x^2 + 2(x + 1)
    var f1 = X*X + 2.0*(X + 1.0)
    //var f1 = X*X + 2.0*X + 2.0
    println(f1)
    println(f1(2.0))
    println(f1("x",2.0))
    println(f1._d("x"))
    println(f1._d("x")(3.0))

    //f2 = 3x^2 + 4y + 1
    val f2 = MathFun("x","y")(
          v => 3*v("x")*v("x")+4*v("y")+1,
          vn => if (vn == "x") 6*X
          		else if(vn == "y") 4
          		else error("variable name="+vn)
        ).setFName("f(x,y) = 3x^2+4y+1")
    
    println(f2)
    val vv = Variable( ("x",2.0), ("y",3.0) )
    println(f2.value(vv))
    
    val vv2 = Variable("x",2.0)("y",3.0)
    println(f2.value(vv2))

    println(f2( ("x",2.0), ("y",3.0) ))
    
    println(f2._d("x"))
    println(f2._d("y"))
    //println(f2._d("z"))
    
    //f3 = (x+y, x*y, 1.0)
    val f3 = MathVectorFun(
          MathFun("x","y"){ v => v("x") + v("y") }.setFName("x+y"),
          X*Y,
          1.0
        )
    println(f3)
    println(f3( ("x",2.0),("y",2.0) ))
  }
}