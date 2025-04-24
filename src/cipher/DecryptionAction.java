package src.cipher;

public class DecryptionAction extends CaesarCipherAction {
    private final int keyCipher;
    private final CaesarCipher cipher = new CaesarCipher();

    public DecryptionAction(int keyCipher) {
        this.keyCipher = keyCipher;
    }

    @Override
    public String execute(String text) {
        return cipher.shiftText(text, -keyCipher);
    }
}