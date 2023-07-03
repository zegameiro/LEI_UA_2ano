package lab11.Ex3;

public interface State {

    void registar(Livro livro);
    void requisitar(Livro livro);
    void devolver(Livro livro);
    void cancelar(Livro livro);
    void reservar(Livro livro);
    
}
