package com.ce.android.retrofitdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.util.List;


import okhttp3.Interceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.SafeSubscriber;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    EditText edt;
    private List<ResultAPI> resultAPIsList;
    private static final String TAG = "MainActivity_TAG_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      edt = (EditText) findViewById(R.id.edt);


        //Retrofit version
//        Retrofit retrofit = RetrofitHelper.Factory.create();
//        RetrofitHelper.GitHubService service = retrofit.create(RetrofitHelper.GitHubService.class);
//
//        Call<List<ResultAPI>> call = service.getRepos("pepe-romeros");
//        call.enqueue(new Callback<List<ResultAPI>>() {
//            @Override
//            public void onResponse(Call<List<ResultAPI>> call, Response<List<ResultAPI>> response) {
//
//                if(response.isSuccessful()){
//                    List<ResultAPI> myRepoList = response.body();
//                    for(ResultAPI repo: myRepoList){
//                        Log.d(TAG, "onResponse: " + repo.toString());
//                    }
//                    // do work here
//                }else {
//
//                    //code 400 500
//                    switch (response.code()){
//                        //
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ResultAPI>> call, Throwable t) {
//                Log.d(TAG, "onFailure: Faild");
//                t.printStackTrace();
//            }
//        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unsubscribe happens here.  But you do not need
        // to do that because RX will take care of that.
    }

    public void getData(View view) {
        final Observable<List<ResultAPI>> resultObservable = RetrofitHelper.Factory.create(edt.getText().toString());
        resultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ResultAPI>>() {
                    @Override
                    public void onCompleted() {
                        //this callback is called whenever the Observable has emitted all of its items
                        start();
                    }

                    @Override
                    public void onError(Throwable e) {
                        // this call back is call when and error occurs
                    }

                    @Override
                    public void onNext(List<ResultAPI> resultAPIs) {
                        // This callback is called per item emitted by the Observable
//                        for(ResultAPI res: resultAPIs)
//                            Log.d(TAG, "onNext: " + res.toString());
                        resultAPIsList = resultAPIs;



                    }
                });



    }
    public void start(){
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        intent.putExtra("list", (Serializable) resultAPIsList);
        startActivity(intent);
    }
}
