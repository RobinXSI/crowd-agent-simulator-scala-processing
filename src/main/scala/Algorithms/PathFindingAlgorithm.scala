package Algorithms

import Cell._
import HexagonalGrid.{HexMap, Hex}

case class CostPerCell(cost: Int, coord: Hex)

class PathFindingAlgorithm(map: HexMap) {

  implicit def orderingByCost[A <: CostPerCell]: Ordering[A] = Ordering.by(e => -e.cost)
  val frontier = collection.mutable.PriorityQueue.empty[CostPerCell](orderingByCost)
  var visited = collection.immutable.Queue.empty[CostPerCell]


  def findPath(startCell: Hex) = {


    frontier += CostPerCell(0, startCell)
    visited = CostPerCell(0, startCell) +: visited

    while (frontier.nonEmpty) {
      val current: CostPerCell = frontier.dequeue()
      val currentNeighbors = current.coord.neighbors // todo filter by accessible
      val accessibleNeighbors: Vector[Hex] = map.filterAccessibleCoords(currentNeighbors)

      currentNeighbors.foreach(next => {
        val newCost = current.cost + map.getCost(current.coord, next)
        val costSoFar: Option[CostPerCell] = visited find { v => v.coord == next }
        costSoFar match {
          case None => test(current, next, newCost)
          case Some(costSoFar) => {
            if (newCost < costSoFar.cost) {
              test(current, next, newCost)
            }
          }
        }

      })

    }
  }

  def test(current: CostPerCell, next: Hex, newCost: Int) = {
    visited = CostPerCell(newCost, next) +: visited
    map.get(next).goto = current.coord
    frontier += CostPerCell(newCost, next)
  }




}
