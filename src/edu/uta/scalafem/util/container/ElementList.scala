package edu.uta.scalafem.util.container
import edu.uta.futureye.core.Element

class ElementList extends edu.uta.futureye.util.container.ElementList {
	def attach(list: edu.uta.futureye.util.container.ElementList): ElementList = {
	  this.objs = list.toList()
      return this
	}
	
	def foreach[B](F: Element => B): Unit = {
	  var i = 0
	  while(i < objs.size()) {
	    F(objs.get(i))
	    i += 1
	  }
	}
}