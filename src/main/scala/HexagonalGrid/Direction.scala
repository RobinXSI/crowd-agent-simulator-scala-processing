package HexagonalGrid

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
