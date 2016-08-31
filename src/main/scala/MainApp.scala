import processing.core.PApplet

class Main extends PApplet {
  var layout: Layout = _

  override def settings(): Unit = {
    size(800, 800)
  }

  override def setup(): Unit = {
    val orientaiton = HexOrientation.Pointy
    val size = Point(40, 40)
    val center = Point(200, 200)
    layout = Layout(orientaiton, size, center)
  }


  override def draw(): Unit = {
    background(255)
    val pixel: Point = layout.hexToPixel(Hex(0, 0, 0))
    val corners: Vector[Point] = layout.polygonCorners(Hex(0, 0, 0))
    println(corners)
    stroke(0)
    fill(128)
    beginShape()
    corners.foreach(c => vertex(c.x.toFloat, c.y.toFloat))
    endShape()
  }
}

object Main {
  def main(args: Array[String]) {
    PApplet.main(Array("Main"))
  }


}