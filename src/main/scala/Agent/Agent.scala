import Cell._
import HexagonalGrid.{Hex, HexMap, Layout, Point}

class Agent(var position: Point, maxSpeed: Double, maxForce: Double) {
  var velocity: Point = Point(0, 0)
  var acceleration: Point = Point(0, 0)
  val r: Double = 3

  /*
  TODO: display() ausserhalb aufrufen
  TODO: border() verhalten bei austritt überlegen
   */

  def run(): Unit = {
    update()
  }

  def follow(layout: Layout, map: HexMap): Unit = {
    val actualHexCoord: Hex = map.filterCoord(layout.pixelToHex(position).hexRound).orNull
    val actualCell: Cell = map.get(actualHexCoord)
    if (actualCell.state == Goal) {
      acceleration = Point(0,0)
      velocity = Point(0,0)
    } else {
      if (actualCell.goto != null) {
        // What is the vector at that spot in the flow field? Scale it up by maxspeed
        val desired: Point = (layout.hexToPixel(actualCell.goto) - layout.hexToPixel(actualHexCoord)) * maxSpeed

        //    val desired: Point = maybeCell.goto.orNull.position * maxSpeed // TODO: Test if goto is needed for desired
        // Steering is desired minus velocity
        val steer: Point = (desired - velocity) limit maxForce
        applyForce(steer)
      }

    }




  }

  def applyForce(force: Point): Unit = {
    // We could add mass here if we want A = F / M
    acceleration = acceleration + force
  }

  // Method to update position
  def update(): Unit = {
    // Update velocity
    velocity = (velocity + acceleration) limit maxSpeed
    position = position + velocity
    acceleration = acceleration * 0
  }

  def theta(): Double = velocity.heading() + math.toRadians(90)

}