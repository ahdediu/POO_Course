package lab05;

import lab04.Actor;
import lab04.Director;

import java.util.ArrayList;
import java.util.List;

interface Playable { void play(); }

abstract class ArtWork {
    protected String title;
}
public class Movie extends ArtWork implements Playable{
    private Director director;
    @Override
    public void play() {
        System.out.println("Playing movie " + title);
    }
    private List<Actor> scenes = new ArrayList<>();
}

class Studio {
    private List<Movie> movies; // movies can exist outside the studio
}

class Review{
    void niceReview(Movie movie){
        System.out.println(movie);
    }
}