package edu.uta.scalafem.function

import edu.uta.futureye.function.intf.Function
import edu.uta.futureye.function.VariableArray
import edu.uta.futureye.function.AbstractFunction
import edu.uta.futureye.function.operator.FMath

object MathFun {
 /**
   * @param names variable names of function
   * @param f definition of function
   * @param df definition of function derivative
   */
  def apply(names: String*)(f: edu.uta.futureye.function.Variable => Double, df: String => MathFun = null): MathFun = {
	  new MathFun(new AbstractFunction(names.head,names.tail: _*){
	    override def value(v: edu.uta.futureye.function.Variable): Double = f(v)
	    override def _d(varName: String): MathFun = df(varName)
	  })
  }
  
  def apply(fun: Function): MathFun = {
    fun match {
      case x: MathFun => 
        x
      case _ => 
        new MathFun(fun)
    }
  }
}

/**
 * Wrapper class of class Function
 * 
 */
class MathFun(fun: Function) extends AbstractFunction with OperatorSupport {
	override def value(): Double = fun.value()
	override def value(v: edu.uta.futureye.function.Variable): Double = fun.value(v)
	def apply(v: Double): Double = {
	  if(varNames().size() == 1) apply(varNames().get(0),v)
	  else error("More than 1 variables in function: "+this.toString())
	}
	def apply(name: String, value: Double): Double = this.apply( (name, value) )
	def apply(tuples: Tuple2[String, Double]*): Double = {
	  val vv = new Variable
	  for( (n, v) <- tuples ) {
	    vv.set(n,v)
	  }
	  fun.value(vv)
	}
	
	override def value(v: edu.uta.futureye.function.Variable, cache: java.util.Map[Object,Object]): Double = fun.value(v,cache)
	
	override def valueArray(v: VariableArray, cache: java.util.Map[Object,Object]): Array[Double] = fun.valueArray(v, cache)
	
	override def _d(varName: String): MathFun = MathFun(fun._d(varName))
	
	override def compose(fInners: java.util.Map[String, Function]): MathFun = MathFun(fun.compose(fInners))
	
	override def getExpression(): String = fun.getExpression()
	override def setOpOrder(o: Int) = fun.setOpOrder(o)
	override def getOpOrder(): Int  = fun.getOpOrder()
	override def toString(): String = fun.toString()
	override def copy(): MathFun = MathFun(fun.copy())
	override def getFName(): String = fun.getFName()
	override def setFName(name: String): MathFun = { fun.setFName(name); this }
	override def varNames() = fun.varNames()
	override def setVarNames(names: java.util.List[String]) = fun.setVarNames(names)
	override def isConstant() = fun.isConstant()
	
	override def A(g: Double): MathFun = MathFun(fun A g)
	override def A(g: Function): MathFun = MathFun(fun A g)
	override def S(g: Double): MathFun = MathFun(fun S g)
	override def S(g: Function): MathFun = MathFun(fun S g)
	override def M(g: Double): MathFun = MathFun(fun M g)
	override def M(g: Function): MathFun = MathFun(fun M g)
	override def D(g: Double): MathFun = MathFun(fun D g)
	override def D(g: Function): MathFun = MathFun(fun D g)
	
	def + (that: Double): MathFun = MathFun(A(that))
	def + (that: MathFun): MathFun = MathFun(A(that))
	
	def - (that: Double): MathFun = MathFun(S(that))
	def - (that: MathFun): MathFun = MathFun(S(that))
	
	def * (that: Double): MathFun = MathFun(M(that))
	def * (that: MathFun): MathFun = MathFun(M(that))
	
	def / (that: Double): MathFun = MathFun(D(that))
	def / (that: MathFun): MathFun = MathFun(D(that))
//运算符优先级要低于+-*/，为了保证不产生混淆，暂时不实现^函数
//	def ^ (that: Double): MathFun = MathFun(FMath.pow(this,that))
//	def ^ (that: MathFun): MathFun = MathFun(FMath.pow(this,that))

}