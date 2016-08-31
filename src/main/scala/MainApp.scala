import Cell._
import HexagonalGrid._
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
        println(corners.length)

        cell.state match {
          case Wall => fill(128)
          case Empty => fill(255)
        }

        stroke(0)
        beginShape()
        corners.foreach(c => vertex(c.x.toFloat, c.y.toFloat))
        endShape(2) // Mode close == 2

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