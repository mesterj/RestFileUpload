package com.kite.joco.restfileupload.rest;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mester József on 2015.11.19..
 */
public class FileService{
    private static String ROOT_WORK = "http://192.168.86.2:8080/NyomtServ1-1.0/webresources/";
  //  private static FileAPI FILE_CLIENT;

    private static OkHttpClient httpClient = new OkHttpClient();
    private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(ROOT_WORK)
            .addConverterFactory(GsonConverterFactory.create(gson));

    public static <S> S createFileService(Class<S> fileService){
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(fileService);
    }

    /*static {
        setupFileClient();
    }

    public static FileAPI getNyomtClient() {
        return FILE_CLIENT;
    }

    private static void setupFileClient() {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_WORK)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FILE_CLIENT = retrofit.create(FileAPI.class);
    }*/



}
