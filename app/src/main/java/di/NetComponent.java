package di;

import javax.inject.Singleton;

import dagger.Component;
import nomind.retroalbum.MainActivity;

/**
 * Created by sayoojvalsan on 1/11/17.
 */
@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(MainActivity mainActivity);

}
