package src;

import java.util.List;

public class Constants {

    public static List<String> ALLOWED_COMMAND = List.of("ENCRYPT", "DECRYPT", "BRUTE_FORCE");
    public static final List<Character> ALPHABET = List.of(
            'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e',
            'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j',
            'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o',
            'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
            'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y',
            'Z', 'z', '.', ',', '\u00AB', '\u00BB', '"', '\'', ':', '!',
            '?', ' ');

    public static final List<String> COMMON_WORDS = List.of(
            "the", "and", "of", "to", "a", "in", "that", "it", "with", "is",
            "for", "on", "as", "at", "by", "an", "be", "this", "I", "you",
            "he", "she", "we", "they", "my", "me", "your", "his", "her"
    );

    public static final int COMMAND_TYPE_ARG_POSITION = 0;
    public static final int FILE_ARG_POSITION = 1;
    public static final int KEY_ARG_POSITION = 2;
    public static final int MIN_ALLOWED_ARGS_COUNT = 2;
    public static final int MAX_ALLOWED_ARGS_COUNT = 3;
}