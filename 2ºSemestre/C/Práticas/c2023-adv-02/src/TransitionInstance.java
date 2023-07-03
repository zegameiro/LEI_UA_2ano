public class TransitionInstance {
    private String from;
    private String to;
    private Character symbol;
    TransitionInstance(String from, String to, Character symbol) {
        this.from = from;
        this.to = to;
        this.symbol = symbol;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public Character getSymbol() {
        return symbol;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TransitionInstance))
            return false;
        TransitionInstance o = (TransitionInstance) obj;
        return from.equals(o.getFrom()) && to.equals(o.getTo()) && symbol.equals(o.getSymbol());
    }
    // Para debugging
    @Override
    public String toString() {
        return String.format("%s -> %c -> %s", from, symbol, to);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }
}
