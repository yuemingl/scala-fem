package edu.uta.scalafem.function.operator


import edu.uta.futureye.function.basic.FC
import edu.uta.futureye.function.basic.FX
import edu.uta.scalafem.function.MathFun
import edu.uta.futureye.function.intf.Function
import edu.uta.scalafem.function.MathVectorFun

object FMath extends edu.uta.futureye.function.operator.FMath {
	val X = MathFun(new FX("x"))
	val Y = MathFun(new FX("y"))
	val Z = MathFun(new FX("z"))
	
	val R = MathFun(new FX("r"))
	val S = MathFun(new FX("s"))
	val T = MathFun(new FX("t"))
	
	val C0 = MathFun(new FC(0.0))
	val C1 = MathFun(new FC(1.0))
	val Cm1 = MathFun(new FC(-1.0))
	val PI = MathFun(new FC(Math.Pi))
	val E = MathFun(new FC(Math.E))
	
	def C(v: Double): MathFun = new MathFun(FC.c(v))
	
	implicit def int2MathFun(i: Int): MathFun = C(i)
	implicit def long2MathFun(l: Long): MathFun = C(l)
	implicit def float2MathFun(f: Float): MathFun = C(f)
	implicit def double2MathFun(d: Double): MathFun = C(d)
	
	//--- Basic operations ------------------------
	
	def sqrt(f: Function): MathFun = new MathFun(edu.uta.futureye.function.operator.FMath.sqrt(f))
	def pow(f: Function, p: Double): MathFun = new MathFun(edu.uta.futureye.function.operator.FMath.pow(f,p))
    def pow(f1: Function, f2: Function): MathFun = new MathFun(edu.uta.futureye.function.operator.FMath.pow(f1,f2))
	def abs(f: Function): MathFun = new MathFun(edu.uta.futureye.function.operator.FMath.abs(f))
	def sum(fi: Function*) = new MathFun(edu.uta.futureye.function.operator.FMath.sum(fi:_*))
	def linearCombination(c1: Double, f1: Function, c2: Double, f2: Function) = 
	  new MathFun(edu.uta.futureye.function.operator.FMath.linearCombination(c1,f1,c2,f2))
	def linearCombination(ci: Array[Double],fi: Array[Function]) = 
	   new MathFun(edu.uta.futureye.function.operator.FMath.linearCombination(ci,fi))
	
	def grad(fun: Function): MathVectorFun = new MathVectorFun(edu.uta.futureye.function.operator.FMath.grad(fun))	
	def grad(fun: Function, varNames: String*): MathVectorFun = 
	  new MathVectorFun(edu.uta.futureye.function.operator.FMath.grad(fun, varNames: _*))	
//	public static VectorFunction grad(Function fun,ObjList<String> varNames) {
//	public static Function div(VectorFunction vFun) {
//	public static Function div(VectorFunction vFun,String ...varNames) {
//	public static Function div(VectorFunction vFun,ObjList<String> varNames) {
//	public static Function curl(VectorFunction vFun) {
//	public static Function curl(VectorFunction vFun, String ...varNames) {
//	public static Function curl(VectorFunction vFun, ObjList<String> varNames) {

	//--- Vectors operations----------------------------------
	
//	public static Vector sum(Vector ...vi) {
//	public static double sum(Vector v) {
//	public static Vector log(Vector v) {
//	public static Vector exp(Vector v) {
//	public static Vector abs(Vector v) {
//	public static double max(Vector v) {
//	public static double max(double[] a) {
//	public static double min(Vector v) {
//	public static double min(double[] a) {
//	public static Vector ax(double a, Vector x) {
//	public static Vector axpy(double a, Vector x, Vector y) {
//	public static Vector axMuly(double a, Vector x, Vector y) {
//	public static Vector axDivy(double a, Vector x, Vector y) {
	
	//------statistic functions--------------------------------
	
//	public static double mean(Vector v) {
//	public static double variance(Vector v) {
//	public static double standardDeviation(Vector v) {
//	public static double sampleStandardDeviation(Vector v) {
//	public static double averageAbsoluteDeviation(Vector v) {

}