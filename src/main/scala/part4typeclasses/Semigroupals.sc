import cats.Monad

import cats.syntax.functor._
import cats.syntax.flatMap._
def productWithMonads[F[_], A, B](fa: F[A], fb: F[B])(implicit monad: Monad[F]): F[(A, B)] =
  for {
    a <- fa
    b <- fb
  } yield (a, b)
  //monad.flatMap(fa)(a => monad.map(fb)(b => (a, b)))

// Law of Associativity
def f(x: Int): List[Int] = x +: List(10)
def g(y: Int): List[Int] = y +: List(100)

List(1, 2, 3).flatMap(x => f(x)).flatMap(y => g(y))
List(1, 2, 3).flatMap(x => f(x).flatMap(g))