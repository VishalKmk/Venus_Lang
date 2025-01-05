import java.util.*;

public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public ASTNode parse() {
        return expression();
    }

    private ASTNode expression() {
        // Example: Binary expressions
        ASTNode left = term();
        while (match("+", "-")) {
            String operator = previous().getLexeme();
            ASTNode right = term();
            left = new ASTNode.BinaryOp(operator, left, right);
        }
        return left;
    }

    private ASTNode term() {
        // Example: Numbers or Variables
        if (match(Token.Type.NUMBER)) {
            return new ASTNode.Literal(Integer.parseInt(previous().getLexeme()));
        } else if (match(Token.Type.IDENTIFIER)) {
            return new ASTNode.Variable(previous().getLexeme());
        }
        throw new RuntimeException("Unexpected token: " + peek());
    }

    private boolean match(String... lexemes) {
        for (String lexeme : lexemes) {
            if (check(lexeme)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean match(Token.Type... types) {
        for (Token.Type type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(String lexeme) {
        return !isAtEnd() && peek().getLexeme().equals(lexeme);
    }

    private boolean check(Token.Type type) {
        return !isAtEnd() && peek().getType() == type;
    }

    private Token advance() {
        if (!isAtEnd())
            current++;
        return previous();
    }

    private boolean isAtEnd() {
        return peek().getType() == Token.Type.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
}
