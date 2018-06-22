package edu.uta.scalafem.io
import edu.uta.scalafem.core.Mesh

object MeshReader {
	def apply(fileName: String): MeshReader = {
	  new MeshReader(fileName)
	}
}

class MeshReader(fileName: String) extends edu.uta.futureye.io.MeshReader(fileName) {
  override def read2DMesh(): Mesh = Mesh(super.read2DMesh())
  override def read3DMesh(): Mesh = Mesh(super.read3DMesh())
}