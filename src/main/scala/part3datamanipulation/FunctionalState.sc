import cats.data.State

case class ShoppingCart(items: List[String], total: Double)
// Think from output state, means: MyState[S, A] = OldState => (NewState, A)
def addToCart(item: String, price: Double): State[ShoppingCart, Double] = State { cart =>
  (ShoppingCart(cart.items :+ item, cart.total + price), cart.total + price)
}

addToCart("Iphone", 10).run(ShoppingCart(List("Macbook"), 200)).value

def inspect[A, B](f: A => B): State[A, B] = State(a => (a, f(a)))