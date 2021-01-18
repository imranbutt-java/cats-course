import cats.instances.int._
import cats.Semigroup

val intSG = Semigroup[Int]
intSG.combine(2, 3)

import cats.syntax.semigroup._
2 |+| 3

case class Bar(id: Int, size: Double)
implicit val barSG: Semigroup[Bar] = Semigroup.instance[Bar] { (b1, b2) =>
  Bar(Math.max(b1.id, b2.id), b1.size + b2.size)
}

Bar(1, 10) |+| Bar(2, 15)

//def reduceThings[T](list: List[T])(implicit semigroup: Semigroup[T]): T = list.reduce(_ |+| _)
def reduceThings[T : Semigroup](list: List[T]): T = list.reduce(_ |+| _)

val intList = List(1, 2, 4, 5)
reduceThings(intList)

reduceThings(List( Bar(1, 10), Bar(2, 12), Bar(1, 1), Bar(4, 1) ))