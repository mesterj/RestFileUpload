package com.kite.joco.restfileupload.rest;

import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

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

}
