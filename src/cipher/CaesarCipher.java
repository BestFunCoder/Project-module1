package src.cipher;

import static src.Constants.ALPHABET;

public class CaesarCipher {

    public String shiftText(String text, int shift) {
        StringBuilder result = new StringBuilder();

        int alphabetSize = ALPHABET.size();

        for (char c : text.toCharArray()) {
            int index = ALPHABET.indexOf(c);

            if (index != -1) {
                int newIndex = (index + shift) % alphabetSize;
                if (newIndex < 0) {
                    newIndex += alphabetSize;
                }
                result.append(ALPHABET.get(newIndex));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}