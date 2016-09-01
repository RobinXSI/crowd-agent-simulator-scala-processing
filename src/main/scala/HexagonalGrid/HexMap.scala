package HexagonalGrid

import Cell._

class HexMap(map: Vector[Vector[Cell]]) {

  def filterCoord(coord: Hex): Option[Hex] = {
    val index: OffsetCoord = coord.offsetFromCube
    if (index.row >= 0 && index.row < height && index.col >= 0 && index.col < width) Some(coord)
    else None
  }

  def filterAccessibleCoords(currentNeighbors: Vector[Hex]): Vector[Hex] = currentNeighbors.filter(n => {
    val index: OffsetCoord = n.offsetFromCube
    val inGrid = index.row >= 0 && index.row < height && index.col >= 0 && index.col < width
    if (!inGrid) false
    else get(index).state != Wall
  })

  val height = map.length
  val width = map(0).length

  def get(offsetCoord: OffsetCoord): Cell = map(offsetCoord.row)(offsetCoord.col)

  def get(hex: Hex): Cell = get(hex.offsetFromCube)

  def goal: Hex = {
    val indices = for {
      (a, i) <- map.iterator.zipWithIndex
      (c, j) <- a.iterator.zipWithIndex
      if c.state == Goal
    } yield OffsetCoord(j, i)
    indices.toList.head
  }.offsetToCube

  def getCost(current: Hex, next: Hex) = 1 // TODO implement algorithm from redblobgames

}
