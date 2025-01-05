public class SemanticAnalyzer {
    private final SymbolTable symbolTable;

    public SemanticAnalyzer(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void analyze(ASTNode node) {
        if (node instanceof ASTNode.Assignment) {
            ASTNode.Assignment assignment = (ASTNode.Assignment) node;
            Symbol symbol = symbolTable.lookup(assignment.name);
            if (symbol == null) {
                throw new RuntimeException("Undefined variable: " + assignment.name);
            }
        } else if (node instanceof ASTNode.BinaryOp) {
            analyze(((ASTNode.BinaryOp) node).left);
            analyze(((ASTNode.BinaryOp) node).right);
        }
    }
}