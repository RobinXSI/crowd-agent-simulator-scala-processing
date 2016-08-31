import processing.core.PApplet

class Main extends PApplet {
  var layout: Layout = _

  override def settings(): Unit = {
    size(800, 800)
  }

  override def setup(): Unit = {
    val orientaiton = HexOrientation.Pointy
    val size = Point(10, 10)
    val center = Point(200, 200)
    layout = Layout(orientaiton, size, center)
  }


  override def draw(): Unit = {
    background(255)


    val mapWidth = 10
    for (r: Int <- 0 to mapWidth) {
      val rOffset: Int = math.floor(r / 2).toInt
      for (q: Int <- (-rOffset) to (mapWidth - rOffset)) {
        val corners: Vector[Point] = layout.polygonCorners(Hex(q, r, -q - r))
        stroke(0)
        fill(128)
        beginShape()
        corners.foreach(c => vertex(c.x.toFloat, c.y.toFloat))
        endShape()
      }
    }
    
  }
}

object Main {
  def main(args: Array[String]) {
    PApplet.main(Array("Main"))
  }


}