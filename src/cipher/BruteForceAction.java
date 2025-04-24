package src.cipher;

import src.Constants;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BruteForceAction extends CaesarCipherAction {

    @Override
    public String execute(String text) {
        return findBestMatchByRating(text);
    }

    private String findBestMatchByRating(String text) {
        return tryAllKeys(text).stream().max(Comparator.comparingInt(this::calculateRating)).map(DecryptionResult::decryptedText).orElse("");
    }

    private List<DecryptionResult> tryAllKeys(String encryptedText) {
        return Arrays.stream(getAllPossibleKeys()).map(key -> new DecryptionResult(decryptWithKey(encryptedText, key), key)).toList();
    }

    private String decryptWithKey(String text, int key) {
        StringBuilder decrypted = new StringBuilder();
        List<Character> alphabet = Constants.ALPHABET;
        int alphabetSize = alphabet.size();

        for (char ch : text.toCharArray()) {
            int index = alphabet.indexOf(ch);
            if (index != -1) {
                int newIndex = (index - key + alphabetSize) % alphabetSize;
                decrypted.append(alphabet.get(newIndex));
            } else {
                decrypted.append(ch);
            }
        }

        return decrypted.toString();
    }

    private int calculateRating(DecryptionResult result) {
        String text = result.decryptedText();
        int key = result.key();
        int score = 0;

        score += (int) text.chars().filter(ch -> ch == ' ').count();

        List<String> words = List.of(text.split("[\\s\\p{Punct}]+"));
        score += (int) words.stream().map(String::toLowerCase).filter(Constants.COMMON_WORDS::contains).count();

        long upper = text.chars().filter(Character::isUpperCase).count();
        long lower = text.chars().filter(Character::isLowerCase).count();
        if (key % 2 == 0 && upper > lower) {
            score += 5;
        } else if (key % 2 != 0 && lower > upper) {
            score += 5;
        }

        return score;
    }

    private Integer[] getAllPossibleKeys() {
        return java.util.stream.IntStream.range(1, Constants.ALPHABET.size()).boxed().toArray(Integer[]::new);
    }

    private record DecryptionResult(String decryptedText, int key) {
    }
}