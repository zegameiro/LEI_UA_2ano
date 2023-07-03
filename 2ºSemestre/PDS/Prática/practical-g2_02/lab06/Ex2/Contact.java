package lab06.Ex2;

//Definir a classe Contact com os campos necessários, como nome, telefone, email, etc.

//Criar uma classe que implemente a interface ContactsInterface. Essa classe deve ter uma lista de contatos e implementar os métodos definidos na interface. Os métodos podem simplesmente manipular a lista de contatos.

//Criar uma classe que implemente a interface ContactsStorageInterface para cada formato de armazenamento que você deseja suportar. Essa classe deve implementar os métodos definidos na interface e lidar com o armazenamento de dados no formato correspondente.

//Na classe ContactsInterface, usar a instância de ContactsStorageInterface passada como parâmetro para realizar a leitura ou gravação de dados, conforme necessário. Isso permitirá que a classe trabalhe com qualquer formato de armazenamento que implemente a interface ContactsStorageInterface.

//Na função main, criar alguns objetos de contato e usar a classe ContactsInterface para manipulá-los. Isso inclui abrir o armazenamento, adicionar contatos, remover contatos, pesquisar contatos e fechar o armazenamento.

public class Contact {

    private String nome;
    private int telefone;
    private String email;

    public Contact(String nome, int telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" + "nome=" + nome + ", telefone=" + telefone + ", email=" + email + '}';
    }

}
