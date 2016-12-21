package presenter;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.net.UnknownHostException;

import interfaces.AlbumViewInterface;
import model.AlbumResponse;
import model.Albums;
import retrofit.Callback;
import services.FetchAlbumService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * Created by sayoojvalsan on 12/19/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AlbumPresenterTest {
    @Mock
    AlbumViewInterface albumViewInterface;

    @Mock
    FetchAlbumService fetchAlbumService;

    @Mock
    Callback<AlbumResponse> mCallBack;
    @Test
    public void fetchAlbum() throws Exception {
        AlbumPresenter presenter = new AlbumPresenter(albumViewInterface, fetchAlbumService);
        //Test Success scenario


        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                ((AlbumPresenter)invocation.getArguments()[1]).onResponse(new AlbumResponse());
                return null;
            }
        }).when(fetchAlbumService).fetchAlbum("Cold", presenter);



        presenter.fetchAlbum("Cold");
        verify(albumViewInterface, times(1)).showProgress();
        verify(fetchAlbumService, times(1)).fetchAlbum("Cold", presenter);
        verify(albumViewInterface, times(1)).hideProgress();
        verify(albumViewInterface, times(1)).onAlbumLoaded(any(Albums.class));


        //Test Failure scenario

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                ((AlbumPresenter)invocation.getArguments()[1]).onFailure(new UnknownHostException());
                return null;
            }
        }).when(fetchAlbumService).fetchAlbum("NoCold", presenter);
        presenter.fetchAlbum("NoCold");

        verify(albumViewInterface, times(2)).showProgress();
        verify(fetchAlbumService, times(1)).fetchAlbum("NoCold", presenter);
        verify(albumViewInterface, times(2)).hideProgress();
        verify(albumViewInterface, times(1)).onFailure(any(Throwable.class));




    }

}