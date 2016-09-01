package HexagonalGrid

case class Point(x: Double, y: Double) {
  override def toString: String = x + ":" + y

  def -(that: Point): Point = Point(this.x - that.x, this.y - that.y)
  def +(that: Point): Point = Point(this.x + that.x, this.y + that.y)
  def /(n: Double): Point = Point(this.x / n, this.y / n)
  def *(n: Double): Point = Point(this.x * n, this.y * n)

  def mag(): Double = math.sqrt(magSq())
  def magSq(): Double = x * x + y * y
  def normalize(): Point = {
    val mag = this.mag()
    if (mag != 0 && mag != 1) this / mag
    else this
  }
  def limit(max: Double): Point = {
    if (this.magSq() > max * max) this.normalize() * max
    else this
  }
  def heading(): Double = math.atan2(y, x)
}
