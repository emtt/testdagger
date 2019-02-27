package mobilize.mx.core.rest;

import java.util.List;

import io.reactivex.Single;
import mobilize.mx.core.Constants;
import mobilize.mx.core.model.Posts;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET(Constants.POSTS)
    Single<List<Posts>> getPosts();

}
