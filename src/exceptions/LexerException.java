

public class LexerException extends CompilerException {
    public LexerException(String message, int line, int position) {
        super(message, line, position);
    }

    public LexerException(String message) {
        super(message);
    }
}