public class BasicICamper implements ICamper {

    private int numSeats;
    private String description;
    private Estado state;

    protected BasicICamper(int numSeats, String description) {
        this.numSeats = numSeats;
        this.description = description;
        this.state = Estado.DISPONIVEL;
    }

    @Override
    public void setEstado(Estado estado) {
        state = estado;
    }

    @Override
    public Estado getEstado() {
        return state;
    }

    @Override
    public int getLugares() {
        return numSeats;
    }

    @Override
    public String getDescricao() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
    
}
