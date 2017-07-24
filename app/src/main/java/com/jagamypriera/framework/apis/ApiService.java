package com.jagamypriera.framework.apis;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

/**
 * Created by Taufik Akbar on 19/12/2016.
 */

public interface ApiService {
    @GET Observable<ResponseBody> get(@Url String url);
}
