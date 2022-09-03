package com.example.retrofitkotlin.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.databinding.ActivityMainBinding
import com.example.retrofitkotlin.view.Adapter.RecyclerAdapter
import com.example.retrofitkotlin.view.Model.cryptoModel
import com.example.retrofitkotlin.view.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),RecyclerAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var cryprtoMOdels : ArrayList<cryptoModel>
    private lateinit var recyclerAdapter : RecyclerAdapter
    private lateinit var compositeDisposable: CompositeDisposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

        compositeDisposable = CompositeDisposable()
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
     loadData()
    }

    private fun loadData() {
        val retfofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)


        compositeDisposable.add(retfofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handlerResponse))




        /*
        val service = retfofit.create(CryptoAPI::class.java)
        val call = service.getData()


        call.enqueue(object : Callback<List<cryptoModel>>{
            override fun onResponse(
                call: Call<List<cryptoModel>>,
                response: Response<List<cryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryprtoMOdels = ArrayList(it)

                        recyclerAdapter = RecyclerAdapter(cryprtoMOdels,this@MainActivity)
                        binding.recyclerview.adapter = recyclerAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<cryptoModel>>, t: Throwable) {

                t.printStackTrace()
            }
        })

         */

    }

    private fun handlerResponse(cryptoModel: List<cryptoModel>){

        cryprtoMOdels = ArrayList(cryptoModel)
        cryprtoMOdels.let {

            recyclerAdapter = RecyclerAdapter(cryprtoMOdels,this@MainActivity)
            binding.recyclerview.adapter = recyclerAdapter
        }
    }

    override fun onItemClick(cryptoModel: cryptoModel) {
        Toast.makeText(this,"clicked: ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
    }

}