package algorithms.generation.solve;

import labyrinth.Cell;
import labyrinth.Maze;
import logging.UseLogger;
import main.Conf;

public class OwnSolvingAlgo extends ASolvingAlgorithms {

	OwnSolvingAlgoCalcing _calc;

	public OwnSolvingAlgo(Maze maze, Cell startCell, Cell endCell, Conf configs) {
		super(maze, startCell, endCell, configs);

		setName("<<eigener CreatingAlgo>>");

		_currentCell = startCell;

	}

	@Override
	public void resolveMaze() {

		UseLogger.LOGGER.info("Position letzte Zelle = ["
				+ _maze.getPositionOfCell(_endCell)[0]
				+ _maze.getPositionOfCell(_endCell)[1] + "]");

		UseLogger.LOGGER.info("First time calcing...");
		_calc = new OwnSolvingAlgoCalcing(_maze, _startCell, _endCell, 99,
				_globalConf);
		boolean _soltutionfound = _calc.calc();

		if (_soltutionfound) {
			_globalConf.get_output().printLine("L�sung yes...");

		} else {
			_globalConf.get_output().printLine("keine L�sung gefunden ;-( ...");
			_globalConf.get_output()
					.printLine(
							"Erstellungs- und L�sungsalgorithmus passen nicht zusammen...");
		}
	}
	
	@Override
	public String getCommand() {
		return "OWN";
	}
}
