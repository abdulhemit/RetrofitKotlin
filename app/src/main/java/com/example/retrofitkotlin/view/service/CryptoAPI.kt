package com.example.retrofitkotlin.view.service

import com.example.retrofitkotlin.view.Model.cryptoModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {


    // GET, POST, UPDATE, DELETE
    //https://raw.githubusercontent.com/
    // atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData():Observable<List<cryptoModel>>

    //fun getData():Call<List<cryptoModel>>

}