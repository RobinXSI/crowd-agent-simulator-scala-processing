package HexagonalGrid

case class Layout(orientation: Orientation, size: Point, origin: Point) {
  def hexToPixel(h: Hex): Point = {
    val x = (orientation.f0 * h.q + orientation.f1 * h.r) * size.x
    val y = (orientation.f2 * h.q + orientation.f3 * h.r) * size.y
    Point(x + origin.x, y + origin.y)
  }

  def pixelToHex(p: Point): FractionalHex = {
    val point = Point((p.x - origin.x) / size.x, (p.y - origin.y) / size.y)
    val q: Double = orientation.b0 * point.x + orientation.b1 * point.y
    val r: Double = orientation.b2 * point.x + orientation.b3 * point.y
    FractionalHex(q, r, -q - r) // TODO: refactor with constructor overlaoding. Remove -q-r
  }

  def hexCornerOffset(corner: Int): Point = {
    val angle: Double = 2 * math.Pi * (orientation.startAngle + corner) / 6
    Point(size.x * math.cos(angle), size.y * math.sin(angle))
  }

  def polygonCorners(h: Hex): Vector[Point] = {
    val center = hexToPixel(h)
    (for {i <- 1 to 6}
      yield Point(center.x + hexCornerOffset(i).x, center.y + hexCornerOffset(i).y)).toVector

  }
}
