import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AutomatonContainer {
    private List<String> states;
    // neste mapa, guardo <estado, [propriedades a true]>
    // por exemplo, se definir o estado A como [accepting = true], [initial = false], statesProperties irá conter <A, [accepting]>
    // será usado para dar o numero de estados de aceitação e iniciais, para garantir que não há demasiados inicials nem demasiado poucos aceitação 
    private Map<String, Set<String>> statesProperties;

    AutomatonContainer() {
        states = new ArrayList<>();
        statesProperties = new HashMap<>();
    }

    public List<String> getStates() {
        return states;
    }

    public void addState(String state) {
        states.add(state);
        statesProperties.put(state, new HashSet<>());
    }

    // para quando se faz por exemplo A [initial = true]
    public void addProperty(String state, String property) {
        if (statesProperties.containsKey(state))
            statesProperties.get(state).add(property);
    }

    // para quando se faz por exemplo A [initial = false]
    public void removeProperty(String state, String property) {
        if (statesProperties.containsKey(state))
            statesProperties.get(state).remove(property);
    }

    public int InitialStatesCount() {
        int ret = 0;
        Set<String> currentProperties;
        for (String state : statesProperties.keySet()) {
            currentProperties = statesProperties.get(state);
            if (currentProperties.contains("initial")) {
                ret++;
            }
        }
        return ret;
    }

    public int AcceptingStatesCount() {
        int ret = 0;
        Set<String> currentProperties;
        for (String state : statesProperties.keySet()) {
            currentProperties = statesProperties.get(state);
            if (currentProperties.contains("accepting")) {
                ret++;
            }
        }
        return ret;
    }
}
