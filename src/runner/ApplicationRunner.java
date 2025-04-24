package src.runner;

import src.CommandType;
import src.cipher.BruteForceAction;
import src.cipher.CaesarCipherAction;
import src.cipher.DecryptionAction;
import src.cipher.EncryptionAction;
import src.exceptions.InvalidFileContentException;
import src.exceptions.WrongFilePathException;
import src.util.FileNameUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static src.CommandType.*;
import static src.Constants.*;
import static src.util.CipherCommandValidatorUtil.isArgumentsValid;

public class ApplicationRunner {
    int keyCipher;

    public void run(String[] args) {
        if (args.length < MIN_ALLOWED_ARGS_COUNT || args.length > MAX_ALLOWED_ARGS_COUNT) {
            throw new IllegalArgumentException("Not enough arguments." + "Correct format: ENCRYPT|DECRYPT  [KEY]" + "BRUTE_FORCE <path_to_file>" + "The program is ending.");
        }

        CommandType commandType = isArgumentsValid(args[COMMAND_TYPE_ARG_POSITION]);
        String filePath = args[FILE_ARG_POSITION];
        Path path = Path.of(filePath);
        isFileExist(path);
        String content = getFileContent(path);

        if (commandType == DECRYPT && !filePath.contains("[ENCRYPTED]")) {
            throw new IllegalArgumentException("For decryption, the file must contain [ENCRYPTED] in its name.");
        }

        if (commandType != BRUTE_FORCE) {
            keyCipher = Integer.parseInt(args[KEY_ARG_POSITION]);
        }

        Map<CommandType, CaesarCipherAction> operations = Map.of(ENCRYPT, new EncryptionAction(keyCipher), DECRYPT, new DecryptionAction(keyCipher), BRUTE_FORCE, new BruteForceAction());
        String result = operations.get(commandType).execute(content);
        saveResultToFile(result, path, commandType);
    }

    private void isFileExist(Path path) {
        if (Files.notExists(path)) {
            throw new WrongFilePathException("Invalid path");
        }
    }

    private String getFileContent(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new InvalidFileContentException("Wrong file content.");
        }
    }

    private void saveResultToFile(String resultText, Path inputPath, CommandType command) {
        try {
            if (command == BRUTE_FORCE && resultText.trim().isEmpty()) {
                System.out.println("The file could not be decrypted using brute force.");
                return;
            }

            Path parentDir = inputPath.getParent();
            String newFileName = FileNameUtil.getNewFileName(inputPath, command);
            Path resultPath = parentDir.resolve(newFileName);

            Files.writeString(resultPath, resultText);
            System.out.println("The result is saved to a file: " + resultPath);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save result: " + e.getMessage(), e);
        }
    }
}