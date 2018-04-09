import scala.util._

val maxIntRadius = 32767
val radius = 10000

val cache = (0 to radius).map(x => {
  (0 to radius).map(y => {
    x * x + y * y < radius * radius
  }).toArray
}).toArray

val oneMillion = 1000000
val numSamples = 1000 * oneMillion

var count = 0
for (i <- 1 to numSamples) {
  val x = Random.nextInt(radius)
  val y = Random.nextInt(radius)
  if (cache(x)(y)) { count += 1 }
}

println(count * 4.0 / numSamples)
