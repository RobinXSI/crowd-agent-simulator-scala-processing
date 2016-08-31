package HexagonalGrid

case class OffsetCoord(col: Int, row: Int) {
  def offsetToCube: Hex = {
    val q = col - (row + row * (row & 1)) / 2 + (row & 1) * (row + 1) / 2
    val r = row
    val s = -q - r
    Hex(q, r, s)
  }

  override def toString: String = "(" + col + "," + row + ")"
}
