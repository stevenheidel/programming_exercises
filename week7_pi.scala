
import scala.math._
import scala.util._

val maxIntRadius = 32767
val cache = (0 to maxIntRadius).map(x => x * x).toArray

case class Point(x: Int, y: Int)

def isInArch(radius: Int, point: Point): Boolean = {
  // pow(point.x, 2) + pow(point.y, 2) < pow(radius, 2)
  // point.x * point.x + point.y * point.y < radius * radius
  cache(point.x) + cache(point.y) < cache(radius)
}

def randomPoint(radius: Int): Point = {
  val x = Random.nextInt(radius)
  val y = Random.nextInt(radius)
  Point(x, y)
}

def getCountInRadius(radius: Int, numSamples: Int) = {
  var count = 0
  for (i <- 1 to numSamples) {
    val point = randomPoint(radius)
    if (isInArch(radius, point)) { count += 1 }
  }
  count
}

val oneMillion = 1000000
val numSamples = 100 * oneMillion
val radius = 32767


// val numParallel = 8
// val sum = (1 to numParallel).toList.par
//   .map(_ => getCountInRadius(radius, numSamples)).sum
// println(sum * 4.0 / (numSamples * numParallel))

val sum = getCountInRadius(radius, numSamples)
println(sum * 4.0 / numSamples)
