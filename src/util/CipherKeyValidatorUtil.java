package src.util;

import src.CommandType;
import src.exceptions.InvalidKeyException;

import static src.Constants.ALLOWED_KEY;

public class CipherKeyValidatorUtil {

    public static CommandType isArgumentsValid(String commandStr) {
        if (!ALLOWED_KEY.contains(commandStr.toUpperCase())) {
            throw new InvalidKeyException("Invalid key!");
        } else {
            return CommandType.fromString(commandStr);
        }
    }
}
