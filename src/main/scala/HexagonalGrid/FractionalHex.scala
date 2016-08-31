package HexagonalGrid

// used for rounding problems from pixel to hex
case class FractionalHex(q: Double, r: Double, s: Double) {
  def hexRound: Hex = {
    var q = this.q.round.toInt
    var r = this.r.round.toInt
    var s = this.s.round.toInt

    val qDiff = math.abs(q - this.q)
    val rDiff = math.abs(r - this.r)
    val sDiff = math.abs(s - this.s)

    if (qDiff > rDiff && qDiff > sDiff) q = -r - s
    else if (rDiff > sDiff) r = -q - s
    else s = -q - r
    Hex(q, r, s)
  }
}
