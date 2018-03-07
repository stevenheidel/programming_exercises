case class Graph[T](vertices: List[T], edges: List[(T, T)])

def f[T](graph: Graph[T], output: List[T]): (Graph[T], List[T]) = {
  graph match {
    // Case 1: If the graph is empty then we're done.
    case g @ Graph(Nil, Nil) => (g, output)

    // Case 2: There are vertices left but no edges, so just pick a random
    // vertex, remove it from the graph, and add it to the output.
    case Graph(vertices, Nil) =>
      return (Graph(vertices.tail, Nil), output :+ vertices.head)

    // Case 3: Both vertices and edges are left. Pick a random vertex that has
    // no edges pointing to it by taking the set of vertices and doing set
    // difference with the list of desination vertices. Then, construct a new
    // graph that doesn't include that edge.
    case Graph(vertices, edges) =>
      val (srcs, dests) = graph.edges.unzip
      val vertex = (vertices.toSet -- dests.toSet).head

      val filteredEdges = edges.filterNot {
        case (src, dest) => vertex == src || vertex == dest }
      val newGraph = Graph(vertices.filterNot(_ == vertex), filteredEdges)

      return (newGraph, output :+ vertex)
  }
}

// Find a fixed point of an arbitrary function
def fixedPoint[T](function: Function1[T, T], input: T): T = {
  val result = function(input)
  if (input == result) {
    return result
  } else {
    return fixedPoint(function, result)
  }
}

def test[T](graph: Graph[T]): List[T] = {
  val fTupled = (f[T] _).tupled
  return fixedPoint(fTupled, (graph, Nil))._2
}

val g1 = Graph(List(1, 2, 3), List((1, 2), (2, 3)))
println(test(g1))

val g2 = Graph(List('A', 'B', 'C', 'D', 'E'),
  List(('A', 'B'), ('A', 'C'), ('B', 'D'), ('C', 'D'), ('E', 'C')))
println(test(g2))
