package com.uzlahpri.movieapp.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.uzlahpri.movieapp.MovieAdapter
import com.uzlahpri.movieapp.R
import com.uzlahpri.movieapp.model.ResponseMovies
import com.uzlahpri.movieapp.model.ResultsItem
import com.uzlahpri.movieapp.service.RetrofitConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var refUsers: DatabaseReference? = null
    var firebaseUser: FirebaseUser? = null

    companion object {
        fun getLaunchService(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        ib_profile.setOnClickListener(this)
        getMovie()
    }

    private fun getMovie() {
        val lang = "en-US"
        val apiKey = "cf89cd1661c9c6323f2246725c779332"

        val loading = ProgressDialog.show(this, "Request Data", "Loading...")
        RetrofitConfig.getInstance().getMoviesData(apiKey, lang).enqueue(
            object : retrofit2.Callback<ResponseMovies> {
                override fun onResponse(
                    call: Call<ResponseMovies>,
                    response: Response<ResponseMovies>
                ) {
                    Log.d("Response", "Success" + response.body()?.results)
                    loading.dismiss()
                    if (response.isSuccessful) {
                        val totalResults = response.body()?.totalResults.toString()
                        if (totalResults.equals("ok")) {
                            Toast.makeText(this@MainActivity, "Data Success !", Toast.LENGTH_SHORT)
                                .show()
//                        Log.e("TAG", "onResponse: ${response.body()?.results?.get(0)?.title}")
//                        Toast.makeText(this@MainActivity, "Data Success !", Toast.LENGTH_SHORT)
//                            .show()
                            val movieData = response.body()?.results
                            val movieAdapter = MovieAdapter(
                                this@MainActivity,
                                movieData as List<ResultsItem>?
                            )
                            rv_main.adapter = movieAdapter
                            rv_main.layoutManager = LinearLayoutManager(this@MainActivity)
                        } else {
                            Toast.makeText(this@MainActivity, "Data Failed !", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }
                }

                override fun onFailure(call: Call<ResponseMovies>, t: Throwable) {
                    Log.d("Response", "Failed : " + t.localizedMessage)
                    loading.dismiss()
                }
            }
        )
    }

    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.ib_profile -> startActivity(Intent(ProfileActivity.getLaunchService(this)))
        }
    }
}
