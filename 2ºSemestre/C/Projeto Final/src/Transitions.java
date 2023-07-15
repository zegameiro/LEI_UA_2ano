import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Transitions {
    // Associa a cada estado os simbolos usados ('estadoA' : {'a', 'b'})
    private Set<TransitionInstance> stateSymbol;
    private boolean isNFAValid = true;  // se houver pelo menos uma transição duplicate, isto fica false
    Transitions() {
        stateSymbol = new HashSet<>();
    }

    public boolean addTransition(String from, String to, Character symbol) {
        boolean ret = true;
        TransitionInstance ti = new TransitionInstance(from, to, symbol);
        if (isDuplicate(ti))
            ret = false;
        else {
            stateSymbol.add(ti);
        }
        
        return ret;
    }

    // Verifica se já existe uma transição igual em stateSymbols
    public boolean isDuplicate(TransitionInstance ti) {

        for (TransitionInstance t : stateSymbol) {
            if (t.equals(ti)) {
                isNFAValid = false;
                return true;
            }
        }
        return false;
    }

    public boolean containsTransition(String from, String to) {
        boolean ret = false;
        for (TransitionInstance t : stateSymbol) {
            if (t.getFrom().equals(from) && t.getTo().equals(to)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    /*
     *     Condições de DFA valid:
     * não tem transições duplicadas (check, verificado na inserção)
     * apenas uma transição por símbolo para cada estado (check)
     */
    public boolean isDFAValid() {
        // Basta verificar se, para cada estado, apenas 
        // uma (ou 0) transition por simbolo
        boolean ret = true;
        Map <String, List<Character>> symbolsPerState = new HashMap<>();
        String current_from;
        Character current_symbol;
        List<Character> current_list;

        for (TransitionInstance ti : stateSymbol) {
            current_from = ti.getFrom();
            current_symbol = ti.getSymbol();
            if (!symbolsPerState.containsKey(current_from)) {
                symbolsPerState.put(current_from, new ArrayList<Character>());
                current_list = symbolsPerState.get(current_from);
                current_list.add(current_symbol);
            } else {
                current_list = symbolsPerState.get(current_from);
                // multiplas transições para um simbolo é invalido em DFA
                if (symbolsPerState.get(current_from).contains(current_symbol) || current_symbol == '\'')   // transição vazia é representada por \' internamente, mas é mesmo string vazia no codigo fonte
                {
                    ret = false;
                    break;
                } else {
                    current_list.add(current_symbol);
                }
            }
        }
        return ret;
    }
    /*
     *      Condições de NFA valid:
     * não tem transições duplicadas (check, verificado na inserção)
     */
    public boolean isNFAValid() {
        return isNFAValid;
    }

    /*
     *     Condições de Complete DFA valid:
     * não tem transições duplicadas (check, verificado na inserção)
     * apenas uma transição por símbolo para cada estado (check)
     * para cada estado, há uma transição por símbolo
     */
    public boolean isCompleteDFAValid(int NumAlphabetChars) {
        // Basta verificar se, para cada estado, apenas 
        // uma (ou 0) transition por simbolo
        boolean ret = true;
        Map <String, List<Character>> symbolsPerState = new HashMap<>();
        String current_from;
        Character current_symbol;
        List<Character> current_list;

        for (TransitionInstance ti : stateSymbol) {
            current_from = ti.getFrom();
            current_symbol = ti.getSymbol();
            if (!symbolsPerState.containsKey(current_from)) {
                symbolsPerState.put(current_from, new ArrayList<Character>());
                current_list = symbolsPerState.get(current_from);
                current_list.add(current_symbol);
            } else {
                current_list = symbolsPerState.get(current_from);
                // multiplas transições para um simbolo é invalido em DFA
                if (symbolsPerState.get(current_from).contains(current_symbol) || current_symbol == '\'')
                {
                    ret = false;
                    break;
                } else
                    current_list.add(current_symbol);
            }
        }
        // Condição de Complete DFA:
        for (List<Character> symbolList : symbolsPerState.values()) {
            if (symbolList.size() != NumAlphabetChars)
            {
                ret = false;
                break;
            }
        }

        return ret;
    }

    @Override
    public String toString() {
        String ret = "";
        for (TransitionInstance transitionInstance : stateSymbol) {
            ret += transitionInstance.toString() + "\n";
        }
        return ret;
    }
}
