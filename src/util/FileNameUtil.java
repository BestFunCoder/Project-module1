package src.util;

import src.CommandType;

import java.nio.file.Path;

public class FileNameUtil {

    public static String getNewFileName(Path inputPath, CommandType command) {
        String originalFileName = inputPath.getFileName().toString();

        return switch (command) {
            case ENCRYPT -> originalFileName.replace(".txt", "[ENCRYPTED].txt");
            case DECRYPT -> originalFileName.replace("[ENCRYPTED]", "[DECRYPTED]");
            case BRUTE_FORCE -> {
                String cleaned = originalFileName.replace("[ENCRYPTED]", "");
                yield cleaned.replace(".txt", "[ENCRYPTED_BY_BRUTE_FORCE].txt");
            }
            default -> throw new IllegalStateException("Unexpected command type: " + command);
        };
    }
}