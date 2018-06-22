package edu.uta.scalafem.test
import edu.uta.futureye.algebra.SparseMatrixRowMajor
import edu.uta.futureye.algebra.SpaceVector

object TestMatrixVector {

  def main(args: Array[String]): Unit = {
    var v = new SpaceVector(3)
    v(1) = 1
    v(2) = 2
    v(3) = 3
    println(v(2))

    var m = new SparseMatrixRowMajor(3,3);
    m(1,2) = 5;
    println(m(1,2))
  }

}