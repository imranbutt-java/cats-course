import cats.data.Writer
import cats.instances.vector._

def countAndLog(n: Int): Writer[Vector[String], Int] =  n match {
  case x if x <= 0 => Writer(Vector("Starting"), x)
  case x => countAndLog(x - 1).flatMap(_ => Writer(Vector(s"$x"), x))
}

countAndLog(10).written.foreach(println)

def naiveSum(n: Int): Writer[Vector[String], Int] = {
  def sum(n: Int): Writer[Vector[String],Int] = n match {
    case x if x <= 0 => Writer(Vector(s"$x"), 0)
    case x => sum(n-1).flatMap(w => Writer(Vector(s"$x"), w + x))
  }
  sum(n)
}

naiveSum(10).run match {
  case (l, v) => println(s"$l = $v")
}