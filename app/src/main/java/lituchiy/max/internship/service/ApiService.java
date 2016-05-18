package lituchiy.max.internship.service;

import java.util.List;

import lituchiy.max.internship.data.AppealNew;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    @GET("tickets")
    Observable<List<AppealNew>> getAppeal(@Query("state") String appeal);
}
