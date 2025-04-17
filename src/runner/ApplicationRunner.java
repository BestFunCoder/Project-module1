package src.runner;

import src.CommandType;
import src.cipher.BruteForceAction;
import src.cipher.CaesarCipherAction;
import src.cipher.DecryptCaesar;
import src.cipher.EncryptionAction;
import src.exceptions.InvalidFileContentException;
import src.exceptions.WrongFilePathException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

import static src.CommandType.*;
import static src.Costants.*;
import static src.util.CipherKeyValidatorUtil.isArgumentsValid;

public class ApplicationRunner {

    public void run(String[] args) {

        // java -jar MyApp.jar [ENCRYPT|DECRYPT] <path_to_file> [KEY]
        //java -jar MyApp.jar ENCRYPT <path_to_file> [KEY]
        //java -jar MyApp.jar <path_to_file> [KEY]

        if (args.length < MAX_ALLOWED_ARGS_COUNT) {
            //throw exception
        }

        CommandType commandType = isArgumentsValid(args[COMMAND_TYPE_ARG_POSITION]);

        String filePath = args[FILE_ARG_POSITION];
        Path path = Path.of(filePath);
        isFileExist(path);
        String content = getFileContent(path);
        if(commandType != BRUTE_FORCE) {

        }

        int key = Integer.parseInt(args[KEY_ARG_POSITION]);

        Map<CommandType, CaesarCipherAction> operations = Map.of(
                ENCRYPT, new EncryptionAction(key),
                DECRYPT, new DecryptCaesar(key),
                BRUTE_FORCE, new BruteForceAction());

        operations.get(commandType).execute(content);
    }

    private void isFileExist(Path path) {
        if (Files.notExists(path)) {
            throw new WrongFilePathException("Invalid path");
        }
    }

    private String getFileContent(Path path) {
        try {
            return Files.lines(path).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new InvalidFileContentException("Wrong file content");
        }
    }
}
