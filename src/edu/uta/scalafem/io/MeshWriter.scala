package edu.uta.scalafem.io
import edu.uta.futureye.core.Mesh

object MeshWriter {
	def apply(mesh: Mesh): edu.uta.futureye.io.MeshWriter = {
	  new edu.uta.futureye.io.MeshWriter(mesh)
	}
}

