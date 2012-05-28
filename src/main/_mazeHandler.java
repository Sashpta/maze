package main;

import ioInferface.gui.MyGameGrid;

import java.util.List;
import java.util.Random;

import labyrinth.Cell;
import labyrinth.Maze;
import algorithms.generation.create.ACreatingAlgorithms;
import algorithms.generation.create.ownCreatingAlgo;
import algorithms.generation.solve.ASolvingAlgorithms;
import algorithms.generation.solve.OwnSolvingAlgo;

public class _mazeHandler {

	private ACreatingAlgorithms[] _possiblesCreatingAlgos = null;
	private ASolvingAlgorithms[] _possiblesSolvingAlgos = null;
	private int _rows;
	private int _cols;
	private Maze _maze;
	private Conf _globalConf;
	// private ACreatingAlgorithms _mycreatingAlgo;
	// private ASolvingAlgorithms _mysolvingAlgo;
	// private Cell _startCell;
	// private Cell _endCell;
	private MyGameGrid _mygrid = null;

	public _mazeHandler(Conf globalConf) {
		_globalConf = globalConf;
		_rows = 9;
		_cols = 9;
		_maze = new Maze(_rows, _cols, _globalConf);

		/**
		 * set _rows, _cols and _endCell new
		 */
		_rows = _maze.getLastPosition()[0];
		_cols = _maze.getLastPosition()[1];

		/**
		 * define globally Position of start and end cell
		 */
		// _startCell = _maze.getCellOnPosition(1, 1);
		// _endCell = _maze.getCellOnPosition(_cols - 2, _rows - 2);

		_possiblesCreatingAlgos = new ACreatingAlgorithms[] { new ownCreatingAlgo(
				_maze, _globalConf) };
		_possiblesSolvingAlgos = new ASolvingAlgorithms[] { new OwnSolvingAlgo(
				_maze, _maze.getStartCell(), _maze.getEndCell(), _globalConf) };

	}

	public void createMaze() {
		ACreatingAlgorithms _mycreatingAlgo = chooseOneRandomCreatingAlgo();

		/**
		 * creates all possibles Algorithms
		 */
		_mycreatingAlgo.createMaze();

		_globalConf.get_output().printLine(
				"\n Das Labyrinth wurde mit dem " + _mycreatingAlgo.getName()
						+ " erstellt... \n\n\n");

	}

	public Maze getMaze() {
		return _maze;
	}

	public void solveMaze() {
		ASolvingAlgorithms _mysolvingAlgo = chooseOneRandomSolvingAlgo();

		// TODO this is in TESING MODE
		_mysolvingAlgo.resolveMaze();

	}

	/**
	 * 
	 * @return gives a Random Creating Algorithms (off all possibles)
	 */
	public ACreatingAlgorithms chooseOneRandomCreatingAlgo() {
		return _possiblesCreatingAlgos[new Random()
				.nextInt(_possiblesCreatingAlgos.length)];

	}

	/**
	 * 
	 * @return gives a Random Solving Algorithms (off all possibles)
	 */
	public ASolvingAlgorithms chooseOneRandomSolvingAlgo() {
		return _possiblesSolvingAlgos[new Random()
				.nextInt(_possiblesSolvingAlgos.length)];

	}

	/**
	 * create a GUI which is implemented with a external Framework called
	 * JGameGrid
	 */
	public void createGUI() {
		_mygrid = new MyGameGrid(_maze.getRows(), _maze.getCols());
		List<Cell> allWalls = _maze.getAllWalls();
		for (int i = 0; i < allWalls.size(); i++) {

			_mygrid.setWall(_maze.getPositionOfCell(allWalls.get(i))[0],
					_maze.getPositionOfCell(allWalls.get(i))[1]);

		}

	}

}
