package ua.kpi.comsys.io8214.mobileapp;

//{"Title":"Star Wars: Episode IV - A New Hope Star Wars: Episode IV - A New Hope ",
//        "Year":"1977"
//        ,"imdbID":"tt0076759"
//        ,"Type":"movie"
//        ,"Poster":"Poster_01.jpg"}

public class Film {


    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;

    public Film(String title, String year, String imdbID, String type, String poster) {
        Title = title;
        Year = year;
        this.imdbID = imdbID;
        Type = type;
        Poster = poster;
    }


    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getPoster() {
        return Poster;
    }

    @Override
    public String toString() {
        return "Film{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + Type + '\'' +
                ", Poster='" + Poster + '\'' +
                '}';
    }

}

