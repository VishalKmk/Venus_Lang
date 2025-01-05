import java.util.*;
import java.util.regex.*;

public class Lexer {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int line = 1;

    private static final Map<String, Token.Type> KEYWORDS = Map.of(
            "int", Token.Type.KEYWORD,
            "float", Token.Type.KEYWORD,
            "string", Token.Type.KEYWORD,
            "if", Token.Type.KEYWORD,
            "else", Token.Type.KEYWORD,
            "return", Token.Type.KEYWORD);

    public Lexer(String source) {
        this.source = source;
    }

    public List<Token> tokenize() {
        Pattern pattern = Pattern.compile(
                "\\d+|\".*?\"|[a-zA-Z_][a-zA-Z0-9_]*|[+\\-*/=<>!]+|[;(){}]|\\s+");
        Matcher matcher = pattern.matcher(source);

        while (matcher.find()) {
            String lexeme = matcher.group();
            if (lexeme.matches("\\s+")) {
                if (lexeme.contains("\n"))
                    line++;
                continue;
            }

            Token.Type type = KEYWORDS.getOrDefault(lexeme, null);
            if (type == null) {
                if (lexeme.matches("\\d+"))
                    type = Token.Type.NUMBER;
                else if (lexeme.matches("\".*\""))
                    type = Token.Type.STRING;
                else if (lexeme.matches("[a-zA-Z_][a-zA-Z0-9_]*"))
                    type = Token.Type.IDENTIFIER;
                else
                    type = Token.Type.OPERATOR;
            }

            tokens.add(new Token(type, lexeme, line));
        }

        tokens.add(new Token(Token.Type.EOF, "", line));
        return tokens;
    }
}
