package src.cipher;

public class BruteForceAction extends CaesarCipherAction {
    private final CaesarCipher cipher = new CaesarCipher();

    @Override
    public String execute(String text) {
        cipher.shiftText(text, 1);
        return super.execute(text);
    }
}