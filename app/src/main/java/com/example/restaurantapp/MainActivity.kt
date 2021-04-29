
package com.example.restaurantapp


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.data.Result

class MainActivity : AppCompatActivity(),LocationListener {

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2
    var location: String = "";
    val key: String = "AIzaSyDkGIvqAXuuOE5TUoDedazelbPdKtQxb1E";
    val type: String = "restaurant";
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RestaurantAdapter
    var result = arrayListOf<Result>()
    lateinit var mLocationManager: LocationManager
    lateinit var progressBar: ProgressBar

    var resulta: MutableLiveData<List<Result>>? = null
    lateinit var restaurantViewModel: RestaurantViewModel

    private val radius = 2500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.visibility = View.VISIBLE
        getCurrentLocation()
        recyclerView = findViewById<RecyclerView>(R.id.rv_restaurants_link)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RestaurantAdapter(result, applicationContext)
        recyclerView.adapter = adapter

        initViewModel()

        val editText = findViewById<View>(R.id.et_place) as EditText
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getRestaurants(s.toString())

            }

        })


    }

    private fun initViewModel() {
        restaurantViewModel = ViewModelProvider(this)[RestaurantViewModel::class.java]
        restaurantViewModel.setContext(location, radius, type, key )
        resulta = restaurantViewModel.results
        resulta?.observe(this,  object : Observer<List<Result>> {
            override fun onChanged(t: List<Result>?) {
                result = t as ArrayList<Result>
                adapter.updateRestaurants(result)
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }

        })
    }

    private fun getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) !==
            PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
                )
            }
        } else {
            getLocation()
        }
    }

    fun getRestaurants(place: String): Unit {
        restaurantViewModel.getRestaurants(location, radius,type,place,key)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    if ((ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) ===
                                PackageManager.PERMISSION_GRANTED)
                    ) {
                       getLocation()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    override fun onLocationChanged(location: Location) {
        val currentLatitude = location.latitude
        val currentLongitude = location.longitude
        this.location = "$currentLatitude,$currentLongitude"
        restaurantViewModel.setContext(this.location, radius,type, key)
    }

    @SuppressLint("MissingPermission")
    fun getLocation() : Unit {
        mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000,
            100f, this);
    }
}