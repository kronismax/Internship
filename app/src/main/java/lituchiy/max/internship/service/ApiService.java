package lituchiy.max.internship.service;

import java.util.List;

import lituchiy.max.internship.data.model.Appeal;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {
    @GET("tickets")
    Observable<List<Appeal>> getAppeal(@Query("state") String appeal);
    @GET("tickets")
    Observable<List<Appeal>> getAppeal(@Query("state") String appeal,
                                       @Query("amount") String amount);
    @GET("tickets")
    Observable<List<Appeal>> getAppeal(@Query("state") String appeal,
                                       @Query("amount") String amount,
                                       @Query("offset") String offset);
}
