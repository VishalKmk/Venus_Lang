public abstract class ASTNode {
    public static class BinaryOp extends ASTNode {
        public final String operator;
        public final ASTNode left, right;

        public BinaryOp(String operator, ASTNode left, ASTNode right) {
            this.operator = operator;
            this.left = left;
            this.right = right;
        }
    }

    public static class Literal extends ASTNode {
        public final Object value;

        public Literal(Object value) {
            this.value = value;
        }
    }

    public static class Variable extends ASTNode {
        public final String name;

        public Variable(String name) {
            this.name = name;
        }
    }

    public static class Assignment extends ASTNode {
        public final String name;
        public final ASTNode value;

        public Assignment(String name, ASTNode value) {
            this.name = name;
            this.value = value;
        }
    }
}
