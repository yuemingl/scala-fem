package edu.uta.scalafem.algebra

//object SpaceVector {
//  def apply(dim: Int): SpaceVector = { 
//    var v = new SpaceVector
//    v.setDim(dim)
//    return v
//  }
//  
//  def apply(value: Double*): SpaceVector = {
//    var v = new SpaceVector
//    v.setDim(value.length)
//    var i=1
//    while(i <= value.length) {
//      v.set(i,value(i-1))
//      i += 1
//    }
//    return v    
//  }
//}
//
//class SpaceVector extends edu.uta.futureye.algebra.SpaceVector {
//	def apply(index: Int): Double = {
//	  this.get(index)
//	}
//	def update(index: Int, value: Double): Unit = {
//	  this.set(index,value)
//	}
//}
