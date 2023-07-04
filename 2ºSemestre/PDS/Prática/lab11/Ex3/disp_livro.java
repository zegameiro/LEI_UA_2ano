package lab11.Ex3;

public class disp_livro implements State {

    @Override
    public void registar(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public void requisitar(Livro livro) {
        System.out.println("A requisitar livro " + livro.getTitulo() + "...");
        livro.setState(new empre_livro());
    }

    @Override
    public void devolver(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public void cancelar(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public void reservar(Livro livro) {
        System.out.println("A reservar livro " + livro.getTitulo() + "...");
        livro.setState(new reser_livro());
    }

    @Override
    public String toString() {
        return "[Disponível]";
    }

    
}
