public class CodeGenerator {
    public String generate(ASTNode node) {
        if (node instanceof ASTNode.Literal) {
            return "PUSH " + ((ASTNode.Literal) node).value;
        } else if (node instanceof ASTNode.Variable) {
            return "LOAD " + ((ASTNode.Variable) node).name;
        } else if (node instanceof ASTNode.BinaryOp) {
            ASTNode.BinaryOp binOp = (ASTNode.BinaryOp) node;
            return generate(binOp.left) + "\n" +
                    generate(binOp.right) + "\n" +
                    "OPER " + binOp.operator;
        } else if (node instanceof ASTNode.Assignment) {
            ASTNode.Assignment assignment = (ASTNode.Assignment) node;
            return generate(assignment.value) + "\nSTORE " + assignment.name;
        }
        return "";
    }
}