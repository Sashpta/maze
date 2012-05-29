package algorithms.generation;

import labyrinth.Cell;
import labyrinth.Maze;
import main.Conf;

abstract public class Algorithms implements IAlgorithms {

	protected String _algoName;
	protected Maze _maze;
	protected Conf _globalConf;
	protected Cell _startCell;
	protected Cell _endCell;

	// protected IOutput _myOutput = null;

	/**
	 * default constructor (is needed for Solving Algos. The solving Algos
	 * mustn't know the maze!
	 */
	public Algorithms() {
		// _myOutput = _configs.get_output();
	}

	/**
	 * Constructor for all Algorithms
	 */
	public Algorithms(Maze maze) {
		_maze = maze;
		// _myOutput = _configs.get_output();
	}

	public void setName(String name) {
		_algoName = name;
	}

	public String getName() {
		return _algoName;

	}

}
