package src.cipher;

public class DecryptCaesar extends CaesarCipherAction {
    private final int key;
    private final CaesarCipher cipher = new CaesarCipher();

    public DecryptCaesar(int key) {
        this.key = key;
    }

    @Override
    public String execute(String text) {
        return cipher.shiftText(text, key);
    }
}