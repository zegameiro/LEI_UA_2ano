import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class BaseCatalogAdmin implements CatalogAdmin { 

    private Map<String, Servico> servicos;

    public BaseCatalogAdmin() {
        this.servicos = new HashMap<String,Servico>();
    }

    @Override
    public boolean registarServico(String codigo, Servico servico) {
        boolean result = false;

        if(this.servicos.containsKey(codigo)) {
            this.servicos.put(codigo, servico);
            result = true;
        } else {
            this.servicos.put(codigo, servico);
            result = true;
        }

        return result;
    }

    @Override
    public boolean verificarServico(String codigo) {
        boolean checkServico = false;

        if(this.servicos.containsKey(codigo))
            checkServico = true;

        return checkServico;
    }

    @Override
    public Servico selecionarServico(String codigo) {
        Servico serv = null;

        if(this.servicos.containsKey(codigo)) 
            serv = this.servicos.get(codigo);

        return serv;
    }

    @Override
    public boolean removerServico(String codigo) {
        boolean checkDeletion = false;

        if(this.servicos.containsKey(codigo)) {
            this.servicos.remove(codigo);
            checkDeletion = true;
        }

        return checkDeletion;
    }

    @Override
    public Map<String, Servico> getServicos() {
        return this.servicos;
    }

    @Override
    public Iterator<String> iterator() {
        return new ServicoIterator();
    }

    @Override
    public String toString() {
        return "Admin Catalog\n" + 
                "   Serviços: " + servicos.toString() + "\n";
    }

    private class ServicoIterator implements Iterator<String> {
        private Iterator<Map.Entry<String,Servico>> iterator;

        public ServicoIterator() {
            iterator = servicos.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public String next() {
            Map.Entry<String, Servico> entry = iterator.next();
            return entry.getValue().toString();
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }
    
}
