package com.base.luculent;

import com.luculent.base.http.TestResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author byz
 * @CreateData 2020/10/30 13:42
 * @blame Android Team
 */
public interface TestApi {
    @GET("mobile/get")
    Observable<TestResult> getMobile(@Query("phone") String phone, @Query("dtype") String type, @Query("key") String key);
}
