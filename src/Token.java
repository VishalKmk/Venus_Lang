public class Token {
    public enum Type {
        IDENTIFIER, KEYWORD, NUMBER, STRING, OPERATOR, PUNCTUATION, EOF
    }

    private final Type type;
    private final String lexeme;
    private final int line;

    public Token(Type type, String lexeme, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
    }

    public Type getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "Token[type=" + type + ", lexeme='" + lexeme + "', line=" + line + "]";
    }
}
