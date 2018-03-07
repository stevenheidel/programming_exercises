val list = List(1, 1, 1, 2, 2, 3, 4, 4)

val result = list.foldLeft(List[(Int, Int)]()) {
  case (Nil, elem) => List((elem, 1))
  case ((curElem, curCount) :: accum, elem) if curElem == elem =>
    (curElem, curCount + 1) :: accum
  case (accum, elem) => (elem, 1) :: accum
}

print(result.reverse)
