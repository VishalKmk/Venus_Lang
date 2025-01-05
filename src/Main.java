import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <source file>");
            System.exit(1);
        }

        String sourceFile = args[0];
        if (!sourceFile.endsWith(".vns")) {
            System.err.println("Error: Source file must have .vns extension");
            System.exit(1);
        }

        String outputFile = args.length > 2 && args[1].equals("-o") ? args[2] : sourceFile.replace(".vns", ".vmc");

        try {
            Path sourcePath = Path.of(sourceFile);
            if (!Files.exists(sourcePath)) {
                System.err.println("Error: Source file does not exist: " + sourceFile);
                System.exit(1);
            }

            String sourceCode = Files.readString(sourcePath);
            System.out.println("Compiling " + sourceFile + "...");

            Lexer lexer = new Lexer(sourceCode);
            var tokens = lexer.tokenize();
            System.out.println("Lexical analysis complete.");

            Parser parser = new Parser(tokens);
            ASTNode ast = parser.parse();
            System.out.println("Parsing complete.");

            SymbolTable symbolTable = new SymbolTable();
            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer(symbolTable);
            semanticAnalyzer.analyze(ast);
            System.out.println("Semantic analysis complete.");

            CodeGenerator codeGenerator = new CodeGenerator();
            String machineCode = codeGenerator.generate(ast);
            System.out.println("Code generation complete.");

            Files.writeString(Path.of(outputFile), machineCode);
            System.out.println("Compilation successful. Output written to " + outputFile);

        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
            System.exit(1);
        } catch (LexerException | ParserException | SemanticException e) {
            System.err.println("Compilation Error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}