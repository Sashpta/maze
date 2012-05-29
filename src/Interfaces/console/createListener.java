package Interfaces.console;

import main.Conf;
import main._mazeHandler;

public class createListener extends AConsoleListener {
	private String inputString = " ";

	private _mazeHandler _mymaze;

	public createListener(Conf globalConf, _mazeHandler mymaze) {
		super(globalConf);
		_mymaze = mymaze;

	}

	public void startListening(String[] input) {

		stringArray = input;

		while (goingon) {
			_myoutput.print("MAIN(CREATE): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}


			if (stringArray[0].equals(ConsoleCommands.OWN)) {

				_mymaze.createMaze();
				goingon = false;
			}

			else if (stringArray[0].equals(ConsoleCommands.QUIT))
				goingon = false;

			else if (stringArray[0].equals(ConsoleCommands.HELP)
					|| !isValidInput() || stringArray.length == 0)

				getHelp();

			stringArray = removeFirstCommand(stringArray);

		}
	}

	private boolean isValidInput() {
		if (stringArray[0].contains("OWN") || stringArray[0] == "QUIT")
			return true;

		return false;

	}

	@Override
	public void getHelp() {
		if (inputString.isEmpty()
				|| !stringArray[0].equals(ConsoleCommands.HELP)) {

			_myoutput
					.printLine("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");
			_myoutput
					.printLine("OWN:\t\t creates a new maze with the 'own' algorithm.");
			_myoutput.printLine("QUIT:\t\t go back to the main menu. \n");

			reinitializeStringArray();
		}
	}

	@Override
	public String getCommand() {
		return "CREATE";
	}

}