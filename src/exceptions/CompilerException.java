
public class CompilerException extends RuntimeException {
    private final int line;
    private final int position;

    public CompilerException(String message, int line, int position) {
        super(String.format("Line %d:%d - %s", line, position, message));
        this.line = line;
        this.position = position;
    }

    public CompilerException(String message) {
        this(message, -1, -1);
    }

    public int getLine() {
        return line;
    }

    public int getPosition() {
        return position;
    }
}
