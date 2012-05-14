package Interfaces.console;

import java.util.logging.Level;

import logging.UseLogger;
import main.Conf;

public class LogListener extends AConsoleListener {
	private String inputString = " ";
	private String[] possiblesLogs;

	public LogListener(Conf globalConf) {

		super(globalConf);

		initPossibleLogLevels();
	}

	/**
	 * Possible Log shades: FINEST, FINER, FINE, CONFIG, INFO, WARNING, SEVERE
	 */
	public void initPossibleLogLevels() {
		possiblesLogs = new String[] { "OFF", "FINEST", "FINER", "FINE",
				"CONFIG", "INFO", "WARNING", "SEVERE", "ALL" };
	}

	public boolean isLogLevel(String loglevel) {
		for (int i = 0; i < possiblesLogs.length; i++) {
			if (possiblesLogs[i].contains(loglevel.toUpperCase()))
				return true;
		}
		return false;
	}

	public void startListening(String[] input) {
		stringArray = input;
		while (goingon) {
			System.out.print("MAIN(LOG): ");

			if (stringArray.length == 0) {
				inputString = _in.nextLine().toUpperCase();
				stringArray = inputString.toUpperCase().split(" ");
			}

			/**
			 * if the input is LOG, just clear ist
			 */
			if (stringArray[0].equals(ConsoleCommands.LOG)) {
				stringArray = removeFirstCommand(stringArray);
				if (stringArray.length == 0)
					reinitializeStringArray();
			}

			if (stringArray[0].equals(ConsoleCommands.QUIT))
				goingon = false;

			else if (stringArray[0].equals(ConsoleCommands.HELP))
				getHelp();

			else if (stringArray[0].equals(ConsoleCommands.GET)) {
				getcurrentLogLevel();
				reinitializeStringArray();
			} else if (stringArray[0].equals(ConsoleCommands.SET)
					&& isLogLevel(stringArray[1])) {
				String logLevel = removeFirstCommand(stringArray)[0];
				setLogLevel(logLevel);
				stringArray = removeFirstCommand(stringArray);
			}

			else
				getHelp();

			stringArray = removeFirstCommand(stringArray);

		}
	}

	public void getcurrentLogLevel() {
		Conf.LOGSTRING = "The current Log Level is set to: " + getLogLevel();

		System.out.println(Conf.LOGSTRING);
		UseLogger.LOGGER.info(Conf.LOGSTRING);

	}

	public String getLogLevel() {
		return UseLogger.LOGGER.getLevel().toString();
	}

	public void setLogLevel(String level) {

		try {
			UseLogger.LOGGER.setLevel(Level.parse(level));
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("The log level has been set successfully to '"
				+ UseLogger.LOGGER.getLevel() + "' \n");

		goingon = false;
	}

	@Override
	public void getHelp() {
		if (!isLogLevel(stringArray[0]) || inputString.isEmpty()) {
			System.out
					.println("<<"
							+ makeStringArrayToString(stringArray)
							+ ">> is NOT a valid input. Please use one of the following inputs...\n");
		}

		_myoutput.printLine("SET OFF:\t\t turn the Log off.");
		_myoutput.printLine("SET FINEST:\t\t Log on the FINEST Log.");
		_myoutput.printLine("SET FINER:\t\t Log on the FINERI log.");
		_myoutput.printLine("SET FINER:\t\t Log on the FINE log.");
		_myoutput.printLine("SET INFO:\t\t Log an INFO message.");
		_myoutput.printLine("SET SEVERE:\t\t Log a SEVERE message..");
		_myoutput.printLine("SET ALL:\t\t Log all messages.");
		_myoutput.printLine("QUIT:\t\t go back to main menu.");
		_myoutput.printLine("GET:\t\t Gives back the current Log Level");

		reinitializeStringArray();

	}

	public String getCommand() {

		return "LOG";
	}

}