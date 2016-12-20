package model;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public class External_urls {

    private String spotify;

    public String getSpotify ()
    {
        return spotify;
    }

    public void setSpotify (String spotify)
    {
        this.spotify = spotify;
    }

    @Override
    public String toString()
    {
        return "External_urls [spotify = "+spotify+"]";
    }


}
