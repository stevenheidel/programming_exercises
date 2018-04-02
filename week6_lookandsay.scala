def lookandsay(list: List[Int]) = {
  list.foldLeft(List[Int]()) {
    case (Nil, elem) => List(elem, 1)
    case (curElem :: curCount :: accum, elem) if curElem == elem =>
      curElem :: curCount + 1 :: accum
    case (accum, elem) => elem :: 1 :: accum
  }
}

var list = List(1)
var i = 0
while (true) {
  val nextList = lookandsay(list)
  println(nextList.length)
  println(nextList.length.toFloat / list.length)
  list = nextList
  i = i + 1
  println(i)
}
