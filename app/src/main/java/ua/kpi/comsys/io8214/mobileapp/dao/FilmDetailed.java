package ua.kpi.comsys.io8214.mobileapp.dao;

//        "Title":"Rogue One: A Star Wars Story",
//        "Year":"2016","" +
//        "Rated":"PG-13",
//        "Released":"16 Dec 2016"
//        ,"Runtime":"133 min",
//        "Genre":"Action, Adventure, Sci-Fi",
//        "Director":"Gareth Edwards",
//        "Writer":"Chris Weitz (screenplay by), Tony Gilroy (screenplay by), John Knoll (story by), Gary Whitta (story by), George Lucas (based on characters created by)",
//        "Actors":"Felicity Jones, Diego Luna, Alan Tudyk, Donnie Yen",
//        "Plot":"The daughter of an Imperial scientist joins the Rebel Alliance in a risky move to steal the plans for the Death Star.",
//        "Language":"English",
//        "Country":"USA",
//        "Awards":"Nominated for 2 Oscars. Another 24 wins & 80 nominations.",
//        "Poster":"Poster_10.jpg",
//        "imdbRating":"7.8",
//        "imdbVotes":"540,185",
//        "imdbID":"tt3748528",
//        "Type":"movie",
//        "Production":"Lucasfilm Ltd."

public class FilmDetailed {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String County;
    private String Awards;
    private String Poster;
    private String imdbRating;
    private String imdbID;
    private String Type;
    private String Production;

    public FilmDetailed(String title, String year,
                        String rated, String released,
                        String runtime, String genre,
                        String director, String writer,
                        String actors, String plot,
                        String language, String county,
                        String awards, String poster,
                        String imdbRating, String imdbID,
                        String type, String production) {

        Title = title;
        Year = year;
        Rated = rated;
        Released = released;
        Runtime = runtime;
        Genre = genre;
        Director = director;
        Writer = writer;
        Actors = actors;
        Plot = plot;
        Language = language;
        County = county;
        Awards = awards;
        Poster = poster;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
        Type = type;
        Production = production;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCounty() {
        return County;
    }

    public String getAwards() {
        return Awards;
    }

    public String getPoster() {
        return Poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getProduction() {
        return Production;
    }

    @Override
    public String toString() {
        return "FilmDetailed{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Rated='" + Rated + '\'' +
                ", Released='" + Released + '\'' +
                ", Runtime='" + Runtime + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Director='" + Director + '\'' +
                ", Writer='" + Writer + '\'' +
                ", Actors='" + Actors + '\'' +
                ", Plot='" + Plot + '\'' +
                ", Language='" + Language + '\'' +
                ", County='" + County + '\'' +
                ", Awards='" + Awards + '\'' +
                ", Poster='" + Poster + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + Type + '\'' +
                ", Production='" + Production + '\'' +
                '}';
    }
}
