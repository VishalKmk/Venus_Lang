
public class SemanticException extends CompilerException {
    public SemanticException(String message, int line, int position) {
        super(message, line, position);
    }

    public SemanticException(String message) {
        super(message);
    }
}
