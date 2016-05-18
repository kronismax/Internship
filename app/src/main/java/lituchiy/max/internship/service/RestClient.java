package lituchiy.max.internship.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String URL= "http://dev-contact.yalantis.com/rest/v1/";

    private static RestClient instance;
    private ApiClient mApiClient;

    private RestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build();

        mApiClient = retrofit.create(ApiClient.class);

    }

    public synchronized static RestClient getInstance() {
        if (instance == null) {
            instance = new RestClient();
        }
        return instance;

    }

    public ApiClient getmApiClient() {
        return mApiClient;
    }


}
