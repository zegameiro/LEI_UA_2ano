package lab11.Ex3;

public class empre_livro implements State {

    @Override
    public void registar(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public void requisitar(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public void devolver(Livro livro) {
        System.out.println("A devolver livro " + livro.getTitulo() + "...");
        livro.setState(new disp_livro());
    }

    @Override
    public void cancelar(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public void reservar(Livro livro) {
        System.out.println("Operação não disponível");
    }

    @Override
    public String toString() {
        return "[Emprestado]";
    }
}
