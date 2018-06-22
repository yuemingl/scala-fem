package edu.uta.scalafem.function

trait OperatorSupport {
	def + (that: Double): MathFun

	def + (that: MathFun): MathFun
	
	def - (that: Double): MathFun
	def - (that: MathFun): MathFun
	
	def * (that: Double): MathFun
	def * (that: MathFun): MathFun
	
	def / (that: Double): MathFun
	def / (that: MathFun): MathFun
	
//	def ^ (that: Double): OSFunction
//	def ^ (that: OSFunction): OSFunction
}