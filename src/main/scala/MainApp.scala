import processing.core.PApplet

class Main extends PApplet {
  var layout: Layout = _

  override def settings(): Unit = {
    size(1200, 1200)
  }

  override def setup(): Unit = {
    val orientaiton = HexOrientation.Pointy
    val size = Point(40, 40)
    val center = Point(200, 200)
    layout = Layout(orientaiton, size, center)

    background(255)


    val mapWidth = 50
    for (r: Int <- 0 to mapWidth) {
      val rOffset: Int = math.floor(r / 2).toInt
      for (q: Int <- (-rOffset) to (mapWidth - rOffset)) {


        val hex: Hex = Hex(q, r, -q - r)
        val cube: OffsetCoord = hex.offsetFromCube(r)

        assert(cube.row == r)
        assert(hex == hex.offsetFromCube(r).offsetToCube(r), (hex, cube.offsetToCube(r)))

        val corners: Vector[Point] = layout.polygonCorners(hex)
        stroke(0)
        fill(128)
        beginShape()
        corners.foreach(c => vertex(c.x.toFloat, c.y.toFloat))
        endShape()

        val center = layout.hexToPixel(hex)
        textSize(10)
        fill(0)

        text(cube.toString, center.x.toFloat - 20, center.y.toFloat + 10)
        text(r, center.x.toFloat - 20, center.y.toFloat)
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