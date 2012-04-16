package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;

public class OwnSolvingAlgo extends SolvingAlgorithms implements
		ISolvingAlgorithms {

	OwnSolvingAlgoCalcing _calc;

	public OwnSolvingAlgo(Maze maze, Cell startCell, Cell endCell) {
		super(maze, startCell, endCell);
		setName("<<eigener CreatingAlgo>>");

		_currentCell = startCell;
		UseLogger.LOGGER.finest("Startcelle = " + _startCell);
		// randomCell = null;
		// NeighbourCell = null;
	}

	@Override
	public void resolveMaze() {
		System.out.println("Position letzte Zelle = ["
				+ _maze.getPositionOfCell(_endCell)[0]
				+ _maze.getPositionOfCell(_endCell)[1] + "]");
		_calc = new OwnSolvingAlgoCalcing(_maze, _startCell, _endCell, 3);

		if (_calc.calc()) System.out.println("L�sung yes...");
		else System.out.println("keine L�sung...");
		
		
	}

	// }

	// private boolean isWallHere(){
	// return false;
	//
	//
	// }

	@Override
	public void defineStartandEndCell() {
		// TODO Auto-generated method stub

	}

}
