package HexagonalGrid

class HexMap(map: Vector[Vector[Cell]]) {
  val height = map.length
  val width = map(0).length

  def get(offsetCoord: OffsetCoord): Cell = map(offsetCoord.row)(offsetCoord.col)
  def get(hex: Hex): Cell = get(hex.offsetFromCube)
}
