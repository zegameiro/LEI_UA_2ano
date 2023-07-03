import java.util.Map;

public class CatalogStats {
    private CatalogAdmin catAd;

    public CatalogStats(CatalogAdmin catAd) {
        this.catAd = catAd;
    }

    public double getAveragePerType() {
        int count = 0;
        double total = 0;

        Map<String, Servico> servicos = catAd.getServicos();

        for (Servico s : servicos.values()) {
            if(!(s.type() == TipoServico.ALOJAMENTO) || !(s instanceof PacoteServicos)) {
                total += s.price();
                count++;
            }
        }

        if(count > 0) 
            return total / count;
        else
            return 0;
    }

    public double getMinimumPrice(TipoServico ts) {
        double min = Double.MAX_VALUE;

        Map<String, Servico> servicos = catAd.getServicos();

        for (Servico s : servicos.values()) {
            if(s.type() == ts && s.price() < min)
                min = s.price();
        }

        return min;
    }
}
