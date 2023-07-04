package lab05.Ex3;

public class Ex3 {

    public static void main(String[] args) {
        Person director = new Person("Christopher Nolan", 46);
        Person writer = new Person("Seth Macfarlane", 50);
        String title = "The Dark Night Awakes";
        int year = 2023;

        Person cast1 = new Person("Leonardo DiCaprio", 55);
        Person cast2 = new Person("Dua Lipa", 43);
        Person cast3 = new Person("Tom Cruise", 53);
        Person cast4 = new Person("Brad Pitt", 49);
        Person cast5 = new Person("Amy Adams",39);

        Place city1 = new Place("New York");
        Place city2 = new Place("Washingtion DC");
        Place city3 = new Place("Florida");
        Place city4 = new Place("California");
        Place city5 = new Place("Los Angeles");

        String genre1 = "Drama";
        String genre2 = "Crime";
        String genre3 = "Action";
        String genre4 = "Suspense";

        String lang1 = "English";
        String lang2 = "Portuguese";
        String lang3 = "French";


        Movie.MovieBuilder movie = new Movie.MovieBuilder(title, year, director);

        movie.addCast(cast1);
        movie.addCast(cast2);
        movie.addCast(cast3);
        movie.addCast(cast4);
        movie.addCast(cast5);

        movie.addLocation(city1);
        movie.addLocation(city2);
        movie.addLocation(city3);
        movie.addLocation(city4);
        movie.addLocation(city5);

        movie.writer(writer);

        movie.addGenre(genre1);
        movie.addGenre(genre2);
        movie.addGenre(genre3);
        movie.addGenre(genre4);

        movie.addLanguage(lang1);
        movie.addLanguage(lang2);
        movie.addLanguage(lang3);

        movie.series("Batman");
        
        movie.isIndependent(true);
        movie.isNetflix(false);
        movie.isTelevision(true);

        Movie m = movie.build();

        System.out.println(m);        

    }
    
}
