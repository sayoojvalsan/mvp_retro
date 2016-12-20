package presenter;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import interfaces.AlbumViewInterface;
import model.AlbumResponse;
import model.Albums;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import services.FetchAlbumService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by sayoojvalsan on 12/19/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AlbumPresenterTest {
    @Mock
    AlbumViewInterface albumViewInterface;

    @Mock
    FetchAlbumService fetchAlbumService;

    @Test
    public void fetchAlbum() throws Exception {
        AlbumPresenter presenter = new AlbumPresenter(albumViewInterface, fetchAlbumService);


        //Test success scenario
        final retrofit.Response<AlbumResponse> response = Response.success(new AlbumResponse());

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                ((AlbumPresenter)invocation.getArguments()[1]).onResponse(response, null);
                return null;
            }
        }).when(fetchAlbumService).fetchAlbum("Cold", presenter);



        presenter.fetchAlbum("Cold");
        verify(albumViewInterface, times(1)).showProgress();
        verify(fetchAlbumService, times(1)).fetchAlbum("Cold", presenter);
        verify(albumViewInterface, times(1)).hideProgress();
        verify(albumViewInterface, times(1)).onAlbumLoaded(any(Albums.class));


        //Test Failure scenario


        final retrofit.Response<AlbumResponse> responseError = Response.error(
                403,
                ResponseBody.create(
                        MediaType.parse("application/json"),
                        "{\"key\":[\"somestuff\"]}"
                )
        );


        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                ((AlbumPresenter)invocation.getArguments()[1]).onResponse(responseError, null);
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