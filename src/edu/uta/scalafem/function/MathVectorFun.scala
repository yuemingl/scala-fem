package edu.uta.scalafem.function

import edu.uta.futureye.function.intf.VectorFunction
import edu.uta.futureye.function.AbstractVectorFunction
import edu.uta.futureye.function.intf.Function
import edu.uta.futureye.function.VariableArray
import edu.uta.futureye.function.AbstractFunction
import edu.uta.futureye.function.operator.FMath
import edu.uta.futureye.algebra.intf.Vector
import edu.uta.futureye.function.basic.SpaceVectorFunction

object MathVectorFun {
  def apply(fun: VectorFunction): MathVectorFun = {
    new MathVectorFun(fun)
  }
  def apply(funs: MathFun*): MathVectorFun = {
    funs match {
      case x: MathVectorFun => 
        x
      case _ => 
        new MathVectorFun(new SpaceVectorFunction(funs: _*))
    }
    
  }
}

/**
 * Wrapper class of class VectorFunction
 * 
 */
class MathVectorFun(fun: VectorFunction) extends AbstractVectorFunction with OperatorSupportVector {
	override def value(v: edu.uta.futureye.function.Variable): Vector = fun.value(v)
	def apply(v: Double): Vector = {
	  if(varNames().size() == 1) apply(varNames().get(0),v)
	  else error("More than 1 variables in function: "+this.toString())
	}
	def apply(tuples: Tuple2[String, Double]*): Vector = {
	  val vv = new Variable
	  for( (n, v) <- tuples ) {
	    vv.set(n,v)
	  }
	  fun.value(vv)
	}
	def apply(name: String, value: Double): Vector = this.apply( (name, value) )
	
	//override def value(v: Variable, cache: java.util.Map[Object,Object]): Vector = fun.value(v,cache)
	override def valueArray(v: VariableArray, cache: java.util.Map[Object,Object]): Array[Vector] = fun.valueArray(v, cache)
	override def getDim: Int = fun getDim
	override def setDim(dim: Int) = fun setDim dim
	override def setVarNames(varNames: java.util.List[String]) = fun setVarNames varNames
	override def varNames(): java.util.List[String] = fun varNames
	override def compose(fInners: java.util.Map[String,Function]): MathVectorFun = new MathVectorFun(fun compose fInners)

	override def set(index: Int, value: Function) = fun.set(index,value)
	override def get(index: Int): MathFun = new MathFun(fun get index);
	
	/////////////////////////////////////////////
	override def set(v: VectorFunction): MathVectorFun = new MathVectorFun(fun set v)
	override def set(a: Double, v: VectorFunction): MathVectorFun = new MathVectorFun(fun set(a,v))
	override def add(g: VectorFunction): MathVectorFun = new MathVectorFun(fun add g)
	override def add(a: Double, g: VectorFunction): MathVectorFun = new MathVectorFun(fun add(a,g))
	override def scale(a: Double): MathVectorFun = new MathVectorFun(fun scale a)
	override def ax(a: Double): MathVectorFun = new MathVectorFun(fun ax a)
	override def axpy(a: Double, g: VectorFunction): MathVectorFun = new MathVectorFun(fun axpy(a,g))
	override def dot(b: VectorFunction): MathFun = new MathFun(fun dot b)
	override def dot(b: Vector): MathFun = new MathFun(fun dot b)
	
	/////////////////////////////////////////////

	override def A(g: Vector): MathVectorFun = new MathVectorFun(fun A g)
	override def A(g: VectorFunction): MathVectorFun = new MathVectorFun(fun A g)
	override def S(g: Vector): MathVectorFun = new MathVectorFun(fun S g)
	override def S(g: VectorFunction): MathVectorFun = new MathVectorFun(fun S g)
	override def M(g: Vector): MathVectorFun = new MathVectorFun(fun M g)
	override def M(g: VectorFunction): MathVectorFun = new MathVectorFun(fun M g)
	override def D(g: Vector): MathVectorFun = new MathVectorFun(fun D g)
	override def D(g: VectorFunction): MathVectorFun = new MathVectorFun(fun D g)
	
	///////////////////////////////////////////////////
	
	override def copy: MathVectorFun = new MathVectorFun(fun.copy)
	override def getExpression: String = fun getExpression
	override def getFName: String = fun getFName
	override def setFName(name: String): MathVectorFun = new MathVectorFun(fun.setFName(name))
	override def toString: String = fun.toString

	////////////////////////////////////////////////////
	
	def + (that: Vector): MathVectorFun = new MathVectorFun(A(that))
	def + (that: MathVectorFun): MathVectorFun = new MathVectorFun(A(that))
	
	def - (that: Vector): MathVectorFun = new MathVectorFun(S(that))
	def - (that: MathVectorFun): MathVectorFun = new MathVectorFun(S(that))
	
	def * (that: Vector): MathVectorFun = new MathVectorFun(M(that))
	def * (that: MathVectorFun): MathVectorFun = new MathVectorFun(M(that))
	
	def / (that: Vector): MathVectorFun = new MathVectorFun(D(that))
	def / (that: MathVectorFun): MathVectorFun = new MathVectorFun(D(that))
}