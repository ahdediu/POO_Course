package lab05;



import java.util.ArrayList;
import java.util.List;

interface Playable { void play(); }

abstract class ArtWork {
    protected String title;
}
public class Movie extends ArtWork implements Playable{
    private Lab04.Director director;
    @Override
    final public void play() {
        System.out.println("Playing movie " + title);
    }
    private List<Lab04.Actor> actors = new ArrayList<>();
}

class Studio {
    private List<Movie> movies; // movies can exist outside the studio
}

class Review{
    void niceReview(Movie movie){
        System.out.println(movie);
    }
}