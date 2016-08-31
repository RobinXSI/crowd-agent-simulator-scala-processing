import processing.core.PApplet

class Main extends PApplet {
  var layout: Layout = _

  override def settings(): Unit = {
    size(800, 800)
  }

  override def setup(): Unit = {
    val orientaiton = HexOrientation.Pointy
    val size = Point(50, 50)
    val center = Point(200, 200)
    layout = Layout(orientaiton, size, center)

    background(255)


    val mapWidth = 5
    for (r: Int <- 0 to mapWidth) {
      val rOffset: Int = math.floor(r / 2).toInt
      for (q: Int <- (-rOffset) to (mapWidth - rOffset)) {
        val hex: Hex = Hex(q, r, -q - r)
        val corners: Vector[Point] = layout.polygonCorners(hex)
        stroke(0)
        fill(128)
        beginShape()
        corners.foreach(c => vertex(c.x.toFloat, c.y.toFloat))
        endShape()

        val center = layout.hexToPixel(hex)
        textSize(10)
        fill(0)
        text(hex.roffsetFromCube(r).toString, center.x.toFloat, center.y.toFloat)
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