package util;

import android.content.Context;

import java.net.UnknownHostException;

import nomind.retroalbum.R;

/**
 * Created by sayoojvalsan on 12/20/16.
 */

public class ErrorUtil {

    public static String getErrorMessage(Context context, Throwable t){

        if(t == null){
            return context.getResources().getString(R.string.error_generic);
        }

        if(t instanceof UnknownHostException){
            return context.getResources().getString(R.string.error_cannot_reach_server);

        }
        return context.getResources().getString(R.string.error_generic);
    }
}
