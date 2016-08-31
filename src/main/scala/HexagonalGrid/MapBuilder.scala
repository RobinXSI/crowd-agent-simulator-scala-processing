package HexagonalGrid

import Cell._

import scala.io.Source

object MapBuilder {
  def createMap(filename: String): HexMap = new HexMap(createCellGrid(filename))

  def createLineOfCellGrid(line: String, indexY: Int): Vector[Cell] = (for {
    (char, indexX) <- line.toList.zipWithIndex
  } yield Cell(typeFromChar(char))).toVector

  def createCellGrid(pathToFile: String): Vector[Vector[Cell]] = (for {
    (line, indexY) <- Source.fromFile(pathToFile).getLines().zipWithIndex
  } yield createLineOfCellGrid(line, indexY)).toVector

  def typeFromChar(char: Char): CellState = {
    char match {
      case 'x' => Wall
      case '.' => Empty
    }
  }


}
