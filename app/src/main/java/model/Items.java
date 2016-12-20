package model;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public class Items {
    private String id;

    private Artists[] artists;

    private External_urls external_urls;

    private String[] available_markets;

    private String name;

    private Images[] images;

    private String type;

    private String album_type;

    private String uri;

    private String href;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Artists[] getArtists ()
    {
        return artists;
    }

    public void setArtists (Artists[] artists)
    {
        this.artists = artists;
    }

    public External_urls getExternal_urls ()
    {
        return external_urls;
    }

    public void setExternal_urls (External_urls external_urls)
    {
        this.external_urls = external_urls;
    }

    public String[] getAvailable_markets ()
    {
        return available_markets;
    }

    public void setAvailable_markets (String[] available_markets)
    {
        this.available_markets = available_markets;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Images[] getImages ()
    {
        return images;
    }

    public void setImages (Images[] images)
    {
        this.images = images;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getAlbum_type ()
    {
        return album_type;
    }

    public void setAlbum_type (String album_type)
    {
        this.album_type = album_type;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    public String getHref ()
    {
        return href;
    }

    public void setHref (String href)
    {
        this.href = href;
    }

    @Override
    public String toString()
    {
        return "Items [id = "+id+", artists = "+artists+", external_urls = "+external_urls+", available_markets = "+available_markets+", name = "+name+", images = "+images+", type = "+type+", album_type = "+album_type+", uri = "+uri+", href = "+href+"]";
    }
}
