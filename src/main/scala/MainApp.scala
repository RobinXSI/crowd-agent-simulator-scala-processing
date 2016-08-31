import Cell._
import HexagonalGrid._
import processing.core.PApplet

class Main extends PApplet {
  var layout: Layout = _
  var hexMap: HexMap = _

  override def settings(): Unit = {
    size(1200, 1200)
  }

  override def setup(): Unit = {
    val orientation = HexOrientation.Pointy
    val size = Point(40, 40)
    val center = Point(200, 200)

    hexMap = MapBuilder.createMap(System.getProperty("user.dir") + "/src/resources/maps/map1.txt")
    layout = Layout(orientation, size, center)

    background(255)
    for (i <- 0 until hexMap.height) {
      for (j <- 0 until hexMap.width) {
        val offsetCoord = OffsetCoord(i, j)
        val hex: Hex = offsetCoord.offsetToCube
        val cell: Cell = hexMap.get(offsetCoord)
        val center = layout.hexToPixel(hex)
        val corners: Vector[Point] = layout.polygonCorners(hex)

        cell.state match {
          case Wall => fill(0)
          case Empty => fill(255)
        }

        stroke(0)
        beginShape()
        corners.foreach(c => vertex(c.x.toFloat, c.y.toFloat))
        endShape()

        textSize(10)
        fill(0)

        text(offsetCoord.toString, center.x.toFloat - 10, center.y.toFloat)
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