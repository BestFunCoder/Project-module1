package src.cipher;

public class EncryptionAction extends CaesarCipherAction {
    private final int key;
    private final CaesarCipher cipher = new CaesarCipher();

    public EncryptionAction(int key) {
        this.key = key;
    }

    @Override
    public String execute(String text) {
        return cipher.shiftText(text, key);
    }
}