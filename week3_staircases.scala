def f(array: Array[Array[Int]], x: Int, y: Int): Int = {
  val sumOfPathsBeforeThisPoint = (x, y) match {
    case (0, 0) => 1
    case (n, 0) => f(array, n - 1, 0)
    case (0, n) => f(array, 0, n - 1)
    case (n, m) => f(array, n - 1, m) + f(array, n, m - 1)
  }
  array(x)(y) * sumOfPathsBeforeThisPoint
}

def f(array: Array[Array[Int]]): Int = {
  if (array.length >= 1 && array(0).length >= 1) {
    f(array, array.length - 1, array(0).length - 1)
  } else {
    0
  }
}

val matrix = Array(
  Array(1, 2, 3),
  Array(4, 5, 6),
  Array(7, 8, 9)
)

println(f(matrix))
