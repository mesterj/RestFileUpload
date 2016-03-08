package com.kite.joco.restfileupload.rest;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.util.Map;

import retrofit.Call;
import retrofit.Response;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.PartMap;

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
            @Part("myfile\";filename=\"image.png\"")RequestBody file,
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
    Call<ResponseBody> uploadImageWork (@PartMap Map<String, RequestBody> params);

    @Multipart
    //@Headers(MediaType.class)
    //@POST("/API_PATH/upload_file")
    @POST("com.mycompany.nyomtserv1.upload/upload")
    Call<Response> uploadFile(@Part("file\"; filename=\"picture.jpg\" ") RequestBody file);

}
