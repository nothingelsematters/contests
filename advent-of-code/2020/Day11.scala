import scala.io.StdIn.{readLine}
import scala.util.control.Breaks.{break, breakable}

object Status extends Enumeration {
  type Status = Value
  val FLOOR, EMPTY, OCCUPIED = Value
}

object Day11 {

  import Status._

  def mutate[T](grid: Vector[Vector[T]], step: (Vector[Vector[T]], T, Int, Int) => T): Int = {
    var gridCopy = grid

    breakable {
      while (true) {
        val newSeats = gridCopy.toSeq.zipWithIndex.map { case (row, i) =>
          row.toSeq.zipWithIndex.map { case (seat, j) => step(gridCopy, seat, i, j) }
        }
        if (newSeats == gridCopy) break()
        gridCopy = newSeats
      }
    }

    return gridCopy.toSeq.map(_.count(_ == Status.OCCUPIED)).sum
  }

  def step(status: Status, adjacent: Vector[Status]) =
    if (status == Status.EMPTY && adjacent.forall(_ != Status.OCCUPIED)) Status.OCCUPIED
    else if (status == Status.OCCUPIED && adjacent.count(_ == Status.OCCUPIED) >= 5) Status.EMPTY
    else status

  def neighbours[T](element: Vector[T], i: Int, f: (Seq[T]) => Seq[Status]) =
    Vector(
      f(element.toSeq).take(i).findLast(it => it != null && it != Status.FLOOR),
      f(element.toSeq).drop(i + 1).find(it => it != null && it != Status.FLOOR)
    ).filter(_ != null)

  def main(args: Array[String]) = {
    var seats = Vector[Vector[Status]]()

    var line = readLine()
    while (line != null) {
      seats = seats :+ line.map(it => if (it == 'L') EMPTY else FLOOR).toVector
      line = readLine()
    }

    val first = mutate(
      seats,
      { (grid: Vector[Vector[Status]], seat: Status, i: Int, j: Int) =>
        val adjacent = grid
          .drop(math.max(i - 1, 0))
          .dropRight(grid.size - i - 2)
          .flatMap(it => it.drop(math.max(j - 1, 0)).dropRight(it.size - j - 2))
        step(seat, adjacent)
      }
    )

    val second = mutate(
      seats,
      { (grid: Vector[Vector[Status]], seat: Status, i: Int, j: Int) =>
        val adjacent = Vector(
          neighbours(grid.apply(i), j, (it: Seq[Status]) => it),
          neighbours(grid, i, (it: Seq[Vector[Status]]) => it.map(_.apply(j))),
          neighbours(grid, i, (it: Seq[Vector[Status]]) =>
            it.toSeq.zipWithIndex.map { case (s, index) => {
              val id = index - i + j
              if (id >= 0 && id < s.size) s.apply(id) else null
            }}
          ),
          neighbours(grid, i, (it: Seq[Vector[Status]]) =>
            it.toSeq.zipWithIndex.map { case (s, index) => {
              val id = -index + i + j
              if (id >= 0 && id < s.size) s.apply(id) else null
            }}
          )
        )
          .flatMap(it => it)
          .collect { case Some(i) => i }

        step(seat, adjacent)
      }
    )

    println("%d %d".format(first, second))
  }
}
