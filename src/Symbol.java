public class Symbol {
    private final String type; // Data type (e.g., int, float, string)
    private final String category; // Variable, function, etc.
    private final String scope; // Global, local, etc.

    public Symbol(String type, String category, String scope) {
        this.type = type;
        this.category = category;
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Symbol[type=" + type + ", category=" + category + ", scope=" + scope + "]";
    }
}