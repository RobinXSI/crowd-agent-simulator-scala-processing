package HexagonalGrid

import HexagonalGrid.Direction._

// TODO: maybe refactor to array for SIMD operations and vector operations
case class Hex(q: Int, r: Int, s: Int) {
  def this(q: Int, r: Int) = this(q, r, -q - r)

  assert(q + r + s == 0, "sum should equal to 0")

  def +(that: Hex): Hex = Hex(this.q + that.q, this.r + that.r, this.s + that.s)

  def -(that: Hex): Hex = Hex(this.q - that.q, this.r - that.r, this.s - that.s)

  def *(n: Double): Hex = Hex((this.q * n).toInt, (this.r * n).toInt, (this.s * n).toInt)

  def length: Int = (math.abs(q) + math.abs(r) + math.abs(s)) / 2

  def distance(that: Hex): Int = (this - that).length

  def neighbor(direction: Direction): Hex = this + direction

  def offsetFromCube: OffsetCoord = {
    val col = q + (r + r * (r & 1)) / 2 - (r & 1) * (r + 1) / 2
    val row = r
    OffsetCoord(col, row)
  }

  override def toString: String = "(" + q + "," + r + "," + s + ")"
}
