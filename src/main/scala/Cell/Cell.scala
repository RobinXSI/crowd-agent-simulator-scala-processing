package Cell

import HexagonalGrid.Hex

class Cell(val state: CellState) {
  var goto: Hex = _
}
