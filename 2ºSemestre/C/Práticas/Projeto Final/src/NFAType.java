public class NFAType extends Type {
    public NFAType() {
        super("NFA");
    }
    @Override public boolean subtype(Type other) {
        return super.subtype(other) || other.name().equals("Automaton");
     }
}
