package edu.uta.scalafem.function

import edu.uta.futureye.algebra.intf.Vector

trait OperatorSupportVector {
	def + (that: Vector): MathVectorFun

	def + (that: MathVectorFun): MathVectorFun
	
	def - (that: Vector): MathVectorFun
	def - (that: MathVectorFun): MathVectorFun
	
	def * (that: Vector): MathVectorFun
	def * (that: MathVectorFun): MathVectorFun
	
	def / (that: Vector): MathVectorFun
	def / (that: MathVectorFun): MathVectorFun
	
}