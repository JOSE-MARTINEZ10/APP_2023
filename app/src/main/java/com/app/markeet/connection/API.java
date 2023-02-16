package com.app.markeet.connection;

import com.app.markeet.connection.callbacks.RespCategory;
import com.app.markeet.connection.callbacks.RespDevice;
import com.app.markeet.connection.callbacks.RespFeaturedNews;
import com.app.markeet.connection.callbacks.RespInfo;
import com.app.markeet.connection.callbacks.RespNewsInfo;
import com.app.markeet.connection.callbacks.RespNewsInfoDetails;
import com.app.markeet.connection.callbacks.RespOrder;
import com.app.markeet.connection.callbacks.RespOrderHistory;
import com.app.markeet.connection.callbacks.RespProduct;
import com.app.markeet.connection.callbacks.RespProductDetails;
import com.app.markeet.connection.callbacks.RespShipping;
import com.app.markeet.data.Constant;
import com.app.markeet.model.Checkout;
import com.app.markeet.model.DeviceInfo;
import com.app.markeet.model.Order;
import com.app.markeet.model.OrderIds;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {

    String CACHE = "Cache-Control: max-age=0";
    String AGENT = "User-Agent: Markeet";
    String SECURITY = "Security: " + Constant.SECURITY_CODE;

    /* API transaction ------------------------------- */

    @Headers({CACHE, AGENT})
    @GET("services/info")
    Call<RespInfo> getInfo(
            @Query("version") int version
    );

    /* Fcm API ----------------------------------------------------------- */
    @Headers({CACHE, AGENT, SECURITY})
    @POST("services/insertOneFcm")
    Call<RespDevice> registerDevice(
            @Body DeviceInfo deviceInfo
    );

    /* News Info API ---------------------------------------------------- */

    @Headers({CACHE, AGENT})
    @GET("services/listFeaturedNews")
    Call<RespFeaturedNews> getFeaturedNews();

    @Headers({CACHE, AGENT})
    @GET("services/listNews")
    Call<RespNewsInfo> getListNewsInfo(
            @Query("page") int page,
            @Query("count") int count,
            @Query("q") String query
    );

    @Headers({CACHE, AGENT})
    @GET("services/getNewsDetails")
    Call<RespNewsInfoDetails> getNewsDetails(
            @Query("id") long id
    );

    /* Category API ---------------------------------------------------  */
    @Headers({CACHE, AGENT})
    @GET("services/listCategory")
    Call<RespCategory> getListCategory();


    /* Product Order API ---------------------------------------------------  */
    @Headers({CACHE, AGENT, SECURITY})
    @POST("services/listProductOrder")
    Call<List<Order>> getListProductOrder(@Body OrderIds ids);

    @Headers({CACHE, AGENT})
    @GET("services/getProductOrder")
    Call<RespOrderHistory> getProductOrder(
            @Query("code") String code
    );


    /* Product API ---------------------------------------------------- */

    @Headers({CACHE, AGENT})
    @GET("services/listProduct")
    Call<RespProduct> getListProduct(
            @Query("page") int page,
            @Query("count") int count,
            @Query("q") String query,
            @Query("category_id") long category_id,
            @Query("col") String column,
            @Query("ord") String order
    );

    @Headers({CACHE, AGENT})
    @GET("services/getProductDetails")
    Call<RespProductDetails> getProductDetails(
            @Query("id") long id
    );

    /* Checkout API ---------------------------------------------------- */
    @Headers({CACHE, AGENT, SECURITY})
    @POST("services/submitProductOrder")
    Call<RespOrder> submitProductOrder(
            @Body Checkout checkout
    );

    /* Shipping API ---------------------------------------------------- */
    @Headers({CACHE, AGENT})
    @GET("services/listShipping")
    Call<RespShipping> getShipping(
            @Query("q") String query
    );

}
