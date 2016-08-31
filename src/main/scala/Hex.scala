import Direction.Direction

// TODO: maybe rename to x, y, z
// TODO: maybe refactor to array for SIMD operations and vector operations
case class Hex(q: Int, r: Int, s: Int) {
  def this(q: Int, r: Int) = this(q, r, -q - r)

  assert(q + r + s == 0, "sum should equal to 0")

  def +(that: Hex): Hex = Hex(this.q + that.q, this.r + that.r, this.s + that.s)

  def -(that: Hex): Hex = Hex(this.q - that.q, this.r - that.r, this.s - that.s)

  def *(n: Double): Hex = Hex((this.q * n).toInt, (this.r * n).toInt, (this.s * n).toInt)

  //  def /(n: Double): Hex = Hex((this.q / n).toInt, (this.r / n).toInt, (this.s / n).toInt)

  def length: Int = (math.abs(q) + math.abs(r) + math.abs(s)) / 2

  def distance(that: Hex): Int = (this - that).length

  def neighbor(direction: Direction): Hex = this + direction

  def qoffsetFromCube(offset: Int): OffsetCoord = {
    val col = q
    val row = r + (q + offset * (q & 1)) / 2
    OffsetCoord(col, row)
  }

  def roffsetFromCube(offset: Int): OffsetCoord = {
    val col = q + (r + offset * (r & 1)) / 2
    val row = r
    OffsetCoord(col, row)
  }

}

// used for rounding problems from pixel to hex
case class FractionalHex(q: Double, r: Double, s: Double) {
  def hexRound: Hex = {
    var q = this.q.round.toInt
    var r = this.r.round.toInt
    var s = this.s.round.toInt

    val qDiff = math.abs(q - this.q)
    val rDiff = math.abs(r - this.r)
    val sDiff = math.abs(s - this.s)

    if (qDiff > rDiff && qDiff > sDiff) q = -r - s
    else if (rDiff > sDiff) r = -q - s
    else s = -q - r
    Hex(q, r, s)
  }
}

// Directions for neighbors
object Direction extends Enumeration {
  type Direction = Hex
  val NorthEast = Hex(1, 0, -1)
  val East = Hex(1, -1, 0)
  val SouthEast = Hex(0, -1, 1)
  val SouthWest = Hex(-1, 0, 1)
  val West = Hex(-1, 1, 0)
  val NorthWest = Hex(0, 1, -1)
}

// Orientations for Cell Layout
object HexOrientation extends Enumeration {
  type HexOrientation = Orientation
  val Pointy = Orientation(math.sqrt(3.0), math.sqrt(3.0) / 2.0, 0.0, 3.0 / 2.0, math.sqrt(3.0) / 3.0, -1.0 / 3.0, 0.0, 2.0 / 3.0, 0.5)
  val Flat = Orientation(3.0 / 2.0, 0.0, math.sqrt(3.0) / 2.0, math.sqrt(3.0), 2.0 / 3.0, 0.0, -1.0 / 3.0, math.sqrt(3.0) / 3.0, 0.0)
}

case class Orientation(f0: Double, f1: Double, f2: Double, f3: Double, b0: Double, b1: Double, b2: Double, b3: Double, startAngle: Double)

case class Layout(orientation: Orientation, size: Point, origin: Point) {
  def hexToPixel(h: Hex): Point = {
    val x = (orientation.f0 * h.q + orientation.f1 * h.r) * size.x
    val y = (orientation.f2 * h.q + orientation.f3 * h.r) * size.y
    Point(x + origin.x, y + origin.y)
  }

  def pixelToHex(p: Point): FractionalHex = {
    val point = Point((p.x - origin.x) / size.x, (p.y - origin.y) / size.y)
    val q: Double = orientation.b0 * point.x + orientation.b1 * point.y
    val r: Double = orientation.b2 * point.x + orientation.b3 * point.y
    FractionalHex(q, r, -q - r) // TODO: refactor with constructor overlaoding. Remove -q-r
  }

  def hexCornerOffset(corner: Int): Point = {
    val angle: Double = 2 * math.Pi * (orientation.startAngle + corner) / 6
    Point(size.x * math.cos(angle), size.y * math.sin(angle))
  }

  def polygonCorners(h: Hex): Vector[Point] = {
    val center = hexToPixel(h)
    (for {i <- 1 to 6}
      yield Point(center.x + hexCornerOffset(i).x, center.y + hexCornerOffset(i).y)).toVector

  }
}

case class Point(x: Double, y: Double)

case class OffsetCoord(col: Int, row: Int) {
  def qoffsetToCube(offset: Int): Hex = {
    val q = col
    val r = row - (col + offset * (col & 1)) / 2
    val s = -q - r
    Hex(q, r, s)
  }

  def roffsetToCube(offset: Int): Hex = {
    val q = col - (row + offset * (row & 1)) / 2
    val r = row
    val s = -q - r
    Hex(q, r, s)
  }

  override def toString: String = "(" + col + "," + row + ")"
}