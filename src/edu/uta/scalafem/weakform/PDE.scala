package edu.uta.scalafem.weakform

import edu.uta.scalafem.function.MathFun
import edu.uta.futureye.core.intf.WeakForm
import edu.uta.scalafem.function.operator.FMath._
import edu.uta.futureye.lib.weakform.AbstractScalarWeakForm
import edu.uta.futureye.core.intf.WeakForm.ItemType
import edu.uta.futureye.core.Element
import edu.uta.futureye.function.intf.Function

object HandSide extends Enumeration { 
  type HandSide = Value 
  val LHS, RHS = Value 
}

object DomainType extends Enumeration { 
  type DomainType = Value 
  val Inner, Border = Value 
}

class PDE {
	import HandSide._
	import DomainType._
	
	var lhsInner: (MathFun, MathFun) => MathFun = null
	var lhsBorder: (MathFun, MathFun) => MathFun = null
	var rhsInner: (MathFun, MathFun) => MathFun = null
	var rhsBorder: (MathFun, MathFun) => MathFun = null
	
	def integrate (hs: HandSide, dt: DomainType)(integrand: (MathFun, MathFun) => MathFun ): PDE = {
		hs match {
		  case LHS => {
		    dt match {
		      case Inner => lhsInner = integrand
		      case Border => lhsBorder = integrand
		    }
		  }
		  case RHS => {
		    dt match {
		      case Inner => rhsInner = integrand
		      case Border => rhsBorder = integrand
		    }		    
		  }
		}	  
	  this
	}
	
	var map: java.util.HashMap[String,Function] = null
	def getWeakForm: WeakForm = {
	  new AbstractScalarWeakForm {
	    
	    override def preProcess(e: Element) = {
	      val trans = e.getCoordTrans()
	      val funs = trans.getTransformFunction(trans.getTransformShapeFunctionByElement(e))
	      map = new java.util.HashMap[String,Function]();
	      val list = trans.getFromVarNames()
	      var i=0
	      while(i < list.size()) {
	        map.put(list.get(i),funs.get(i))
	        i += 1
	      }
	    }
	    
		override def leftHandSide(e: Element, itemType: ItemType): MathFun = {
			if(itemType==ItemType.Domain)  {
			  return new MathFun(lhsInner(new MathFun(u),new MathFun(v)).compose(map))
			} else if(itemType == ItemType.Border) {
			  //边界单元需要在这里计算坐标变换
			  val trans = e.getCoordTrans()
		      val funs = trans.getTransformFunction(trans.getTransformShapeFunctionByElement(e))
		      map = new java.util.HashMap[String,Function]();
		      val list = trans.getFromVarNames()
		      var i=0
		      while(i < list.size()) {
		        map.put(list.get(i),funs.get(i))
		        i += 1
		      }

			  return new MathFun(lhsBorder(new MathFun(u),new MathFun(v)).compose(map))
			}
			null
		}
	
		override def rightHandSide(e: Element,itemType: ItemType): MathFun = {
			if(itemType==ItemType.Domain)  {
			  return new MathFun(rhsInner(new MathFun(u),new MathFun(v)).compose(map))
			} else if(itemType == ItemType.Border) {
			  //边界单元需要在这里计算坐标变换
			  val trans = e.getCoordTrans()
		      val funs = trans.getTransformFunction(trans.getTransformShapeFunctionByElement(e))
		      map = new java.util.HashMap[String,Function]();
		      val list = trans.getFromVarNames()
		      var i=0
		      while(i < list.size()) {
		        map.put(list.get(i),funs.get(i))
		        i += 1
		      }
		      return new MathFun(rhsBorder(new MathFun(u),new MathFun(v)).compose(map))
			}
			null
		}
	  }
	}
}