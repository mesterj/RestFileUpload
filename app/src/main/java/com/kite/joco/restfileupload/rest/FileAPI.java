package com.kite.joco.restfileupload.rest;


import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


/**
 * Created by Mester József on 2015.11.19..
 */

// root of web service
    // http://localhost:8080/NyomtServ1-1.0/webresources/com.mycompany.nyomtserv1.upload/upload
    public interface FileAPI {

    // Upload API service URL
    @Multipart
    @POST("com.mycompany.nyomtserv1.upload/upload")
    Call<String> getImage(
            @Part("myfile\";filename=\"image.png\"") RequestBody file,
            @Part("description") String description);

    @Multipart
    @POST("com.mycompany.nyomtserv1.upload/upload")
    Call<String> getImageOne(
            @Part("file")RequestBody file);

    @Multipart
    @POST("com.mycompany.nyomtserv1.upload/upload")
    Call<String> getImageTwo(
            @Part("file\";file=\"image.png\"")RequestBody file);

    @Multipart
    @POST("com.mycompany.nyomtserv1.upload/upload")
    //@POST ("/api/Events/editevent")
    Call<ResponseBody> uploadImageWork(@PartMap Map<String, RequestBody> params);

    @Multipart
    //@Headers(MediaType.class)
    //@POST("/API_PATH/upload_file")
    @POST("com.mycompany.nyomtserv1.upload/upload")
    Call<Response> uploadFile(@Part("file\"; filename=\"picture.jpg\" ") RequestBody file);

}
