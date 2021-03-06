package model;

/**
 * Created by sayoojvalsan on 12/19/16.
 */

public class Albums {

    private String total;

    private String limit;


    private Items[] items;

    private String next;

    private String offset;

    private String href;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getLimit ()
    {
        return limit;
    }

    public void setLimit (String limit)
    {
        this.limit = limit;
    }


    public Items[] getItems ()
    {
        return items;
    }

    public void setItems (Items[] items)
    {
        this.items = items;
    }

    public String getNext ()
    {
        return next;
    }

    public void setNext (String next)
    {
        this.next = next;
    }

    public String getOffset ()
    {
        return offset;
    }

    public void setOffset (String offset)
    {
        this.offset = offset;
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
        return "Albums [total = "+total+", limit = "+limit+", items = "+items+", next = "+next+", offset = "+offset+", href = "+href+"]";
    }
}
