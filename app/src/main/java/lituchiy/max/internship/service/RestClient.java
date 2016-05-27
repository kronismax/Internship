package lituchiy.max.internship.service;

import lituchiy.max.internship.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static RestClient instance;
    private ApiService mApiService;

    private RestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();

        mApiService = retrofit.create(ApiService.class);

    }

    public synchronized static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;

    }

    public ApiService getmApiService() {
        return mApiService;
    }


}
