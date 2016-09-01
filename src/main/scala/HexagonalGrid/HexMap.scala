package HexagonalGrid

import Cell._

class HexMap(map: Vector[Vector[Cell]]) {
  def filterAccessibleCoords(currentNeighbors: Vector[Hex]): Vector[Hex] = {
    
    currentNeighbors
  }

  val height = map.length


  val width = map(0).length
  def get(offsetCoord: OffsetCoord): Cell = map(offsetCoord.row)(offsetCoord.col)

  def get(hex: Hex): Cell = get(hex.offsetFromCube)
  def goal: Hex = {
    val indices = for{
      (a, i) <- map.iterator.zipWithIndex
      (c, j) <- a.iterator.zipWithIndex
      if c.state == Goal
    } yield OffsetCoord(j, i)
    indices.toList.head
  }.offsetToCube

  def getCost(current: Hex, next: Hex) = 1 // TODO implement algorithm from redblobgames

}
