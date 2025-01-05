import java.util.*;

public class SymbolTable {
    private final Deque<Map<String, Symbol>> scopeStack = new ArrayDeque<>();

    public SymbolTable() {
        enterScope();
    }

    public void enterScope() {
        scopeStack.push(new HashMap<>());
    }

    public void exitScope() {
        if (scopeStack.isEmpty()) {
            throw new IllegalStateException("No scope to exit.");
        }
        scopeStack.pop();
    }

    public void addSymbol(String name, Symbol symbol) {
        if (scopeStack.isEmpty()) {
            throw new IllegalStateException("No active scope to add a symbol.");
        }

        Map<String, Symbol> currentScope = scopeStack.peek();
        if (currentScope.containsKey(name)) {
            throw new IllegalArgumentException("Symbol '" + name + "' already defined in the current scope.");
        }

        currentScope.put(name, symbol);
    }

    public Symbol lookup(String name) {
        for (Map<String, Symbol> scope : scopeStack) {
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null;
    }

    public void printSymbolTable() {
        System.out.println("=== Symbol Table ===");
        int scopeLevel = scopeStack.size();

        for (Map<String, Symbol> scope : scopeStack) {
            System.out.println("Scope Level: " + scopeLevel--);
            scope.forEach((name, symbol) -> {
                System.out.println("  " + name + " -> " + symbol);
            });
        }
        System.out.println("====================");
    }
}