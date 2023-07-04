package lab11.Ex3;

public class Context {

    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void registar(Livro livro) {
        state.registar(livro);
    }

    public void requisitar(Livro livro) {
        state.requisitar(livro);
    }

    public void devolver(Livro livro) {
        state.devolver(livro);
    }

    public void cancelar(Livro livro) {
        state.cancelar(livro);
    }

    public void reservar(Livro livro) {
        state.reservar(livro);
    }
    
}
