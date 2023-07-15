import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    // apenas é relevante seguir o caminho dos parents para ver se o simbolo está definido
    private Map<String, Symbol> symbolTable;
    private SymbolTable parent; 

    public SymbolTable(SymbolTable parent) {
        symbolTable = new HashMap<>();
        this.parent = parent;
    }
    public SymbolTable getParent() {
        return parent;
    }
    public void putSymbol(String symbolName, Symbol symbol) {
        symbolTable.put(symbolName, symbol);
    }
    public Symbol getSymbol(String symbolName) {
        return symbolTable.get(symbolName);
    }
    public boolean containsSymbol(String symbolName) {
        return symbolTable.containsKey(symbolName);
    }

    public void removeSymbol(String symbolName) {
        symbolTable.remove(symbolName);
    }
 
    public Symbol findSymbol(String symbolName) {
        SymbolTable currentTable = this;
        while (currentTable != null)
        {

            if (currentTable.symbolTable.containsKey(symbolName))
            {
                return currentTable.symbolTable.get(symbolName);

            }
            currentTable = currentTable.parent;
        }
        return null;
    }

    // para debug
    @Override
    public String toString() {
        return symbolTable.keySet().toString();
    }
}
