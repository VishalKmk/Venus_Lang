
public class ParserException extends CompilerException {
    public ParserException(String message, int line, int position) {
        super(message, line, position);
    }

    public ParserException(String message) {
        super(message);
    }
}
