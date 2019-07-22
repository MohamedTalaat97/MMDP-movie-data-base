package com.example.mmdp;

public class Media {


    private String name;

    public String getYear() {
        return year;
    }

    private String year;
    private String type;

    public String getPoster_link() {
        return poster_link;
    }

    private String poster_link;



    private String genre;



    private String imdb_rating;


    private String rotten_rating;

    public String getRated() {
        return rated;
    }

    private String rated;
    private String release;
    private String actors;
    private String director;
    private String writers;
    private String language;
    private String country;
    private String awards;
    private String boxoffice;

    public String getRuntime() {
        return runtime;
    }

    private String runtime;


    public Media(String name, String year, String type, String poster_link) {

        this.name = name;
        this.year = year;
        this.type = type;
        this.poster_link = poster_link;
    }


    public Media(String name, String year, String type, String poster_link, String genre, String imdb_rating,
                 String rotten_rating, String rated, String director, String actors, String writers, String language
            , String country, String awards, String boxoffice, String runtime) {

        this.name = name;
        this.year = year;
        this.type = type;
        this.poster_link = poster_link;
        this.genre = genre;
        this.imdb_rating = imdb_rating;
        this.rotten_rating = rotten_rating;
        this.rated = rated;
        this.director = director;
        this.actors = actors;
        this.writers = writers;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.boxoffice = boxoffice;
        this.runtime = runtime;


    }

    public String getTitle() {

        return name;
    }

    public String getRotten_rating() {
        return rotten_rating;
    }

    public String getImdb_rating() {
        return imdb_rating;
    }


    public String getGenre() {
        return genre;
    }
}
