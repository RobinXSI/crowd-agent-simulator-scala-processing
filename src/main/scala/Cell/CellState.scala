package Cell

sealed trait CellState {}
case object Empty extends CellState {}
case object Active extends CellState {}
case object Wall extends CellState {}
case object Goal extends CellState {}
