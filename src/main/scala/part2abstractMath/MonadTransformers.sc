import java.util.concurrent.Executors

import cats.data.EitherT
import cats.instances.future._
import scala.concurrent.{ExecutionContext, Future}

val bandwidths = Map(
  "server1.com" -> 50,
  "server2.com" -> 300,
  "server3.com" -> 170
  )

implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(8))
type AsyncResponse[T] = EitherT[Future, String, T]

def getBandWidth(server: String): AsyncResponse[Int] = bandwidths.get(server) match {
  case None => EitherT.left(Future(s"Server $server unreachable."))
  case Some(v) => EitherT.right(Future(v))
}

// Exercise
def canWithstandSurge(s1: String, s2: String): AsyncResponse[Boolean] = for {
  bs1 <- getBandWidth(s1)
  bs2 <- getBandWidth(s2)
} yield bs1 + bs2 > 250

val result = canWithstandSurge("server1.com", "server3.com").transform {
  case Left(reason) => Left(s"Failed with reason: $reason")
  case Right(false) => Left(s"Can't Pass")
  case Right(true) => Right(s"Success !!!")
}
result.value.foreach(println)