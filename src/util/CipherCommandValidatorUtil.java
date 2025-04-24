package src.util;

import src.CommandType;
import src.exceptions.InvalidCommandException;

import static src.Constants.ALLOWED_COMMAND;

public class CipherCommandValidatorUtil {

    public static CommandType isArgumentsValid(String commandStr) {
        if (!ALLOWED_COMMAND.contains(commandStr.toUpperCase())) {
            throw new InvalidCommandException("Invalid command!");
        } else {
            return CommandType.fromString(commandStr);
        }
    }
}