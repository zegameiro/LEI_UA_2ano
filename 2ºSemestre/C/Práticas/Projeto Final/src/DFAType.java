public class DFAType extends Type {
    public DFAType() {
        super("DFA");
    }
    @Override public boolean subtype(Type other) {
        return super.subtype(other) || other.name().equals("Automaton");
     }
}
