package com.ce.android.retrofitdemo;


import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;
import rx.schedulers.Schedulers;

public class RetrofitHelper {
    public  static final String BASE_URL = "https://api.github.com";
    public static class Factory{


// private static OkHttpClient  authorizedClient(){
//     return new OkHttpClient().Builder().addInterceptor(new Interceptor(){
//         @Override
//         public Response intercept((Chain chain)) throws IOException{
//             return null;
//         }
//     });
// }

        public static Retrofit create(){
            return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(
                            RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }
        public static Observable<List<ResultAPI>> create(String user){
            Retrofit retrofit = create();
            GitHubService service = retrofit.create(GitHubService.class);
            return service.getRepos(user);
        }
    }

    public interface GitHubService{

        @GET("/users/{user}/repos")
     //   Call<List<ResultAPI>> getRepos(@Path("user" ) String user);
        Observable<List<ResultAPI>> getRepos(@Path("user" ) String user);

        @GET("/user/{user}/profile")
        Call<List<ResultAPI>> getProfile(@Path("user") String user);

    }
}
