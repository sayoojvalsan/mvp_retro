package interfaces;


/**
 * Created by sayoojvalsan on 12/20/16.
 */

public interface Callback<T> {
    void onResponse(T response);

    void onFailure(Throwable t);
}
