package com.example.kelas5_belajar_api.service;

import com.example.kelas5_belajar_api.model.ModelPost;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("/posts")
    Call<List<ModelPost>> getPost();

    @FormUrlEncoded
    @POST("/posts")
    Call<ResponseBody> postData(
            @Field("title") String title,
            @Field("body") String body,
            @Field("userId") int userId
    );

    @DELETE("/posts/{id}")
    Call<ResponseBody> deleteData(@Path("id") int id);

    @FormUrlEncoded
    @PUT("/posts/{id}")
    Call<ResponseBody> editData(

            @Path("id") int id,
            @Field("title")String title,
            @Field("body")String body,
            @Field("userId") int userId

    );



}
