trait Tree[+T]
case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]

// Define Functor
import cats.Functor

// Function to use
def do10x[F[_]](container: F[Int])(implicit functor: Functor[F]): F[Int] = functor.map(container)( _ * 10)

implicit object TreeFunctor extends Functor[Tree] {
  override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] =  fa match {
    case null => null
    case Node(v, l, r) => Node(f(v), map(l)(f), map(r)(f))
  }
}

val left = Node(2, null, null)
val rightLeft = Node(4, null, null)
val right = Node(3, rightLeft, null)

val tree = Node(10, left, right)

do10x[Tree](tree)