import processing.core.PApplet

class Main extends PApplet {
  var layout: Layout = _

  override def setup(): Unit = {
    val orientaiton = HexOrientation.Pointy
    val size = Point(10, 10)
    val center = Point(25, 25)
    layout = Layout(orientaiton, size, center)
  }

  override def settings(): Unit = {
    size(800, 800)
  }

  override def draw(): Unit = {
  }
}

object Main {
  def main(args:Array[String]) {
    PApplet.main(Array("Main"))
  }



}