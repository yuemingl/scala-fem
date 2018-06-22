package edu.uta.scalafem.core
import edu.uta.scalafem.util.container.ElementList
import scala.collection.immutable.HashMap
import edu.uta.scalafem.function.MathFun
import edu.uta.futureye.core.NodeType
import edu.uta.futureye.function.intf.Function

object Mesh {
  def apply(mesh: edu.uta.futureye.core.Mesh): Mesh = {
    val m = new Mesh()
    m.ref(mesh)
    m
  }
}

class Mesh extends edu.uta.futureye.core.Mesh {
  def elements: ElementList = new ElementList().attach(eleList)
  
  def markBorderNode(mapNTF: HashMap[NodeType, MathFun]): Unit = {
    val map = new java.util.HashMap[NodeType, Function]()
    for( (nt, fun) <- mapNTF ) {
      map.put(nt,fun)
    }
    markBorderNode(map)
  }
}