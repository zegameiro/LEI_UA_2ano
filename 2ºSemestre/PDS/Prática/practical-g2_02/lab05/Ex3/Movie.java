package lab05.Ex3;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private final String title;
    private final int year;
    private final Person director;
    private final Person writer;
    private final String series;
    private final List<Person> cast;
    private final List<Place> locations;
    private final List<String> genres;
    private final List<String> languages;
    private final boolean isTelevision;
    private final boolean isNetflix;
    private final boolean isIndependent;

    // Inner static class MovieBuilder
    public static class MovieBuilder {
        private final String title;
        private final int year;
        private final Person director;

        private Person writer = null;
        private String series = "";
        private List<Person> cast = new ArrayList<Person>();
        private List<Place> locations = new ArrayList<Place>();
        private List<String> languages = new ArrayList<String>();
        private List<String> genres = new ArrayList<String>();
        private boolean isTelevision = false;
        private boolean isNetflix = false;
        private boolean isIndependent = false;

        public MovieBuilder(String title, int year, Person director) {
            this.title = title;
            this.year = year;
            this.director = director;
        }

        public MovieBuilder writer(Person writer) {
            this.writer = writer;
            return this;
        }

        public MovieBuilder series(String series) {
            this.series = series;
            return this;
        }

        public MovieBuilder addCast(Person actor) {
            this.cast.add(actor);
            return this;
        }

        public MovieBuilder addLocation(Place location) {
            this.locations.add(location);
            return this;
        }

        public MovieBuilder addLanguage(String language) {
            this.languages.add(language);
            return this;
        }

        public MovieBuilder addGenre(String genre) {
            this.genres.add(genre);
            return this;
        }

        public MovieBuilder isTelevision(boolean isTelevision) {
            this.isTelevision = isTelevision;
            return this;
        }

        public MovieBuilder isNetflix(boolean isNetflix) {
            this.isNetflix = isNetflix;
            return this;
        }

        public MovieBuilder isIndependent(boolean isIndependent) {
            this.isIndependent = isIndependent;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }   
    }


    // Private constructor to avoid direct instantiation of the class without using the Builder
    private Movie(MovieBuilder builder) {
        title = builder.title;
        year = builder.year;
        director = builder.director;
        writer = builder.writer;
        series = builder.series;
        cast = builder.cast;
        locations = builder.locations;
        genres = builder.genres;
        languages = builder.languages;
        isTelevision = builder.isTelevision;
        isNetflix = builder.isNetflix;
        isIndependent = builder.isIndependent;
    }

    @Override
    public String toString() {
        return "Movie [" + this.title + ", " + this.year + "]: \n" +
               "      Director: "       +       this.director      + "\n" +
               "      Writer: "         +       this.writer        + "\n" +
               "      Series: "         +       this.series        + "\n" +
               "      Languages: "      +       this.languages     + "\n" +
               "      Cast: "           +       this.cast          + "\n" + 
               "      Locations: "      +       this.locations     + "\n" +
               "      Genres: "         +       this.genres        + "\n" +
               "      Is Television: "  +       this.isTelevision  + "\n" +
               "      Is Netflix: "     +       this.isNetflix     + "\n" +
               "      Is Independent: " +       this.isIndependent + "\n" ;
    }

}
