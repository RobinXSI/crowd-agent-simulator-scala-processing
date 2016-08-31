package HexagonalGrid

import Cell.Cell

class HexMap(map: Vector[Vector[Cell]], goal: OffsetCoord) {

  val height = map.length
  val width = map(0).length

  def get(offsetCoord: OffsetCoord): Cell = map(offsetCoord.row)(offsetCoord.col)
  def get(hex: Hex): Cell = get(hex.offsetFromCube)
}
