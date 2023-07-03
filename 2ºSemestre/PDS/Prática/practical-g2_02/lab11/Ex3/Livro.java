package lab11.Ex3;

public class Livro {

    private String titulo;
    private String autor;
    private String isbn;
    private int ano;
    private State state;

    public Livro(String titulo, String autor, String isbn, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.state = new inv_livro();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAno() {
        return ano;
    }

    ////////////////////////////////////////////////////////////////////////////
    // SET STATE ///////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public void setState(State state) {
        this.state = state;
    }    

    @Override
    public String toString() {
        return String.format("%-20s %-20s %-15s", titulo, autor, state.toString());
    }

    public void registar() {
        state.registar(this);
    }

    public void requisitar() {
        state.requisitar(this);
    }

    public void devolver() {
        state.devolver(this);
    }

    public void reservar() {
        state.reservar(this);
    }

    public void cancelar() {
        state.cancelar(this);
    }

}
