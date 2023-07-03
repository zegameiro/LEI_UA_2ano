public class CompleteDFAType extends Type {
    public CompleteDFAType() {
        super("CompleteDFA");
    }
    @Override public boolean subtype(Type other) {
        return super.subtype(other) || other.name().equals("Automaton");
     }
}
