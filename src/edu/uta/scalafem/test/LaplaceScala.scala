package edu.uta.scalafem.test

import edu.uta.scalafem.io.MeshReader
import java.util.HashMap
import edu.uta.futureye.core.NodeType
import edu.uta.futureye.function.intf.Function
import edu.uta.futureye.lib.element.FELinearTriangle
import edu.uta.futureye.lib.weakform.WeakFormLaplace2D
import edu.uta.futureye.lib.assembler.AssemblerScalar
import edu.uta.futureye.algebra.solver.external.SolverJBLAS
import edu.uta.futureye.io.MeshWriter
import edu.uta.futureye.core.Element
import edu.uta.scalafem.core.Mesh
import edu.uta.futureye.algebra.intf.SparseVector
import edu.uta.scalafem.util.container.ElementList
import edu.uta.scalafem.function.operator.FMath._
import edu.uta.futureye.lib.weakform.WeakFormBuilder
import edu.uta.scalafem.function.MathFun
import edu.uta.futureye.core.NodeType
import edu.uta.futureye.lib.weakform.WeakFormBuilder.Type
import edu.uta.scalafem.weakform.PDE
import edu.uta.scalafem.weakform.HandSide._
import edu.uta.scalafem.weakform.DomainType._

/**
 * <blockquote><pre>
 * Problem:
 *   -\Delta{u} = f
 *   u(x,y)=0, (x,y) \in \partial{\Omega}
 * where
 *   \Omega = [-3,3]*[-3,3]
 *   f = -2*(x^2+y^2)+36
 * Solution:
 *   u = (x^2-9)*(y^2-9)
 * </blockquote></pre>
 * 
 * @author liuyueming
 */
object LaplaceScala {
	def run() {
	    //1.Read in a triangle mesh from an input file with
	    //  format ASCII UCD generated by Gridgen
	    val reader = new MeshReader("triangle.grd")
	    var mesh = reader.read2DMesh()
	    //Compute geometry relationship of nodes and elements
	    mesh.computeNodeBelongsToElements()

	    //2.Mark border types
	    val mapNTF = new HashMap[NodeType, Function]()
	    mapNTF.put(NodeType.Dirichlet, null)
	    mesh.markBorderNode(mapNTF)
	
	    //3.Use element library to assign degrees of
	    //  freedom (DOF) to element
	    val feLT = new FELinearTriangle()
	    mesh.elements foreach feLT.assignTo _
	    
	    //4.Weak form
	    val eq1 = new PDE
	    val f = -2.0*(X*X + Y*Y) + 36.0
	    eq1.integrate(LHS, Inner) {
	    	(u,v) => (grad(u, "x","y") dot grad(v, "x","y"))
	    }.integrate(RHS, Inner) {
	    	(u,v) => f.M(v)
	    }
 	    
	    //5.Assembly process
	    val assembler = new AssemblerScalar(mesh, eq1.getWeakForm)
	    assembler assemble
	    val stiff = assembler getStiffnessMatrix
	    val load = assembler getLoadVector
	    
	    //Boundary condition
	    assembler imposeDirichletCondition C0
	
	    //6.Solve linear system
	    val solver = new SolverJBLAS()
	    val u = solver solveDGESV(stiff, load)
	    println("u=");
	    var i = 1
	    while(i <= u.getDim()) {
	      println("%.3f".format(u.get(i)))
	      i += 1
	    } //max = 80.839
	    
	
	    //7.Output results to an Techplot format file
	    val writer = new MeshWriter(mesh);
	    writer.writeTechplot("./tutorial/Laplace2D.dat", u);
	}
	
	def main(args: Array[String]): Unit = {
		run
	}
}
