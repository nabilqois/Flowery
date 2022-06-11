package com.nabil.flowery.retrofit

import com.nabil.flowery.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<LoginResponse>

    @FormUrlEncoded
    @POST("users")
    fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<RegisterResponse>

    @GET("/flowers")
    fun getListFlower(
        @Header("Authorization") auth: String,
        @Query("kueri") kueri: String
    ) : Call<SearchResponse>

    @GET("/trivias")
    fun getTrivia(
        @Header("Authorization") auth: String
    ) : Call<TriviaResponse>

    @GET("/flowers")
    fun getFlowerOfTheDay(
        @Header("Authorization") auth: String
    ) : Call<FlowerDayResponse>

    @GET("tutorials/6294c09ed2ebd7dc3c0a4e4d")
    fun getTutorial(
        @Header("Authorization") auth: String
    ) : Call<TutorialResponse>
}