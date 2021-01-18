case class ShoppingCart(items: List[String], total: Double)
val shop = List(
  ShoppingCart(List("iphone", "shoes"), 799),
  ShoppingCart(List("TV"), 20000),
  ShoppingCart(List(), 0))

shop.foldLeft(ShoppingCart(List(), 0.0))((s1, s2) =>
  ShoppingCart(s1.items ++ s2.items, s1.total + s2.total))

import cats.Monoid
import cats.syntax.monoid._

def combineCart[T](list: List[T])(implicit monoid: Monoid[T]): T =
  list.foldLeft(monoid.empty)(_ |+| _)

implicit val shoppingCartMonoid: Monoid[ShoppingCart] = Monoid.instance(
  ShoppingCart(List(), 0.0),
  (s1, s2) => ShoppingCart(s1.items ++ s2.items, s1.total + s2.total)
)

combineCart(shop)

