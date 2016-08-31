package Algorithms

import Cell._
import HexagonalGrid.{HexMap, Layout, OffsetCoord}

case class CostPerCell(cost: Int, cell: Cell)

class PathFindingAlgorithm {

  implicit def orderingByCost[A <: CostPerCell]: Ordering[A] = Ordering.by(e => -e.cost)
  val frontier = collection.mutable.PriorityQueue.empty[CostPerCell](orderingByCost)

  var visited = collection.immutable.Queue.empty[CostPerCell]

  def findPath(layout: Layout, map: HexMap) = {
  }




}
