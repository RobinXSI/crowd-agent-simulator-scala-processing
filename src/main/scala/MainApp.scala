import Cell._
import HexagonalGrid._
import Algorithms._
import processing.core.PApplet

class Main extends PApplet {
  var layout: Layout = _
  var hexMap: HexMap = _

  override def settings(): Unit = {
    size(1200, 400)
  }

  def sizeToWidth(size: Double): Double = 1 / 2 * math.sqrt(3) * math.sqrt(size * size)
  def widthToSize(width: Double): Double = 2 * width / math.sqrt(3)

  override def setup(): Unit = {
    hexMap = MapBuilder.createMap(System.getProperty("user.dir") + "/src/resources/maps/map1.txt")
    val goal: Hex = hexMap.goal
    new PathFindingAlgorithm(hexMap).findPath(goal)



    val margin = 10


    val w = (width - 2 * margin) / (hexMap.width + 0.5)
    val h = (height - 2 * margin) / (hexMap.height + 7 / 8)
    val cellWidth = math.min(w, h)
    val size = widthToSize(cellWidth  / 2)

    val orientation = HexOrientation.Pointy
    val center = Point(cellWidth / 2 + margin, size + margin)

    layout = Layout(orientation, size, center)

    background(255)
    for (i <- 0 until hexMap.height) {
      for (j <- 0 until hexMap.width) {
        val offsetCoord = OffsetCoord(j, i)
        val hex: Hex = offsetCoord.offsetToCube
        val cell: Cell = hexMap.get(offsetCoord)
        val center = layout.hexToPixel(hex)
        val corners: Vector[Point] = layout.polygonCorners(hex)

        // set cell color
        cell.state match {
          case Wall => fill(128)
          case Goal => fill(0, 255, 0)
          case Empty => fill(255)
        }

        // draw hexagon
        stroke(0)
        beginShape()
        corners.foreach(c => vertex(c.x.toFloat, c.y.toFloat))
        endShape(2) // Mode close == 2

        // show coordinate
        textSize(10)
        fill(0)
        text(offsetCoord.toString, center.x.toFloat - 10, center.y.toFloat)


        // draw direction
        if (cell.goto != null) {
          val gotoCenter = layout.hexToPixel(cell.goto)
          val halfLine = gotoCenter - center
          val pointer = center + halfLine.normalize() * halfLine.mag() / 3
          line(center.x.toFloat, center.y.toFloat, pointer.x.toFloat, pointer.y.toFloat)
        }


      }
    }
  }


  override def draw(): Unit = {


  }
}

object Main {
  def main(args: Array[String]) {
    PApplet.main(Array("Main"))
  }


}