package edu.uta.scalafem.function
import edu.uta.futureye.core.Element

object Variable {
	def apply(name: String, value: Double): Variable = {
	  new Variable().set(name, value)
	}
	
	def apply(tuples: Tuple2[String, Double]*): Variable = {
	  val vv = new Variable
	  for( (n, v) <- tuples ) {
	    vv.set(n,v)
	  }
	  vv
	}
}

class Variable extends edu.uta.futureye.function.Variable {
  def apply(name: String, value: Double): Variable = { set(name, value); this }
  override def set(name: String, value: Double): Variable = {super.set(name,value); this}
  override def setElement(e: Element): Variable = { super.setElement(e); this }
  override def setIndex(idx: Int): Variable = { super.setIndex(idx); this }
}