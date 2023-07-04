package lab07.Ex2;

public class Ex2 {
    
    public static void main(String[] args) {

        String file = "lab07/Ex2/file.txt";

        TextReader textReader = new TextReader(file);
        System.out.println("\nText Reader");
        while (textReader.hasNext()) {
            System.out.println(textReader.next());
        }

        TeamFilter teamFilter = new TeamFilter(new TextReader(file));
        System.out.println("\nTeam Filter");
        while (teamFilter.hasNext()) {
            System.out.println(teamFilter.next());
        }

        NormalizationFilter NormalizerReader =  new NormalizationFilter (new TextReader(file));
        System.out.println("\nNormalizer Filter");
        while (NormalizerReader.hasNext()) {
            System.out.println(NormalizerReader.next());
        }

        VowelFilter vowelFilter = new VowelFilter(new TextReader(file));
        System.out.println("\nVowel Filter");
        while (vowelFilter.hasNext()) {
            System.out.println(vowelFilter.next());
        }

        CapitalizationFilter capitalizationFilter = new CapitalizationFilter(new TextReader(file));
        System.out.println("\nCapitalization Filter");
        while (capitalizationFilter.hasNext()) {
            System.out.println(capitalizationFilter.next());
        }
        
    }

}


