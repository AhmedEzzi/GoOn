package com.example.ongo

import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mumayank.com.airlocationlibrary.AirLocation

class MainActivity : AppCompatActivity(), AirLocation.Callback {
    lateinit var locaTxt1:EditText
    lateinit var locaTxt2:EditText
    lateinit var locaTxt3:EditText
    lateinit var moneyTxt:TextView
    lateinit var desTxt:TextView
    lateinit var location:AirLocation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
locaTxt1=findViewById(R.id.locaTxt1)
        locaTxt2=findViewById(R.id.locaTxt2)
        locaTxt3=findViewById(R.id.locaTxt3)
        moneyTxt=findViewById(R.id.moneyTxt)
        desTxt=findViewById(R.id.desTxt)
        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        location.onActivityResult(requestCode, resultCode, data) // ADD THIS LINE INSIDE onActivityResult
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        location.onRequestPermissionsResult(requestCode, permissions, grantResults) // ADD THIS LINE INSIDE onRequestPermissionResult
    }
    fun Calc(view: View) {

        val location=AirLocation(this,this,true,0,"")
        location.start()


    }

    override fun onFailure(locationFailedEnum: AirLocation.LocationFailedEnum) {
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(locations: ArrayList<Location>) {
        val coder = Geocoder(this)
        val loc1:Location
        if (locaTxt1.text.isNotEmpty()) {
            val address1 = "${locaTxt1.text} egypt"
            val result = coder.getFromLocationName(address1, 1)
            loc1 = Location("")
            loc1.latitude = result?.get(0)?.latitude ?: 0.0
            loc1.longitude = result?.get(0)?.longitude ?: 0.0
        }else{
            loc1 = Location("")
            loc1.latitude = 0.0
            loc1.longitude = 0.0
        }
        val loc2:Location
        if (locaTxt2.text.isNotEmpty()) {
            val address2 = "${locaTxt2.text} egypt"
            val result2 = coder.getFromLocationName(address2, 1)
            loc2 = Location("")
            loc2.latitude = result2?.get(0)?.latitude ?: 0.0
            loc2.longitude = result2?.get(0)?.longitude ?: 0.0
        }else{

            loc2 = Location("")
            loc2.latitude = 0.0
            loc2.longitude = 0.0
        }
        val loc3: Location
        if (locaTxt3.text.isNotEmpty()) {
            val address3 = "${locaTxt3.text} egypt"
            val result3 = coder.getFromLocationName(address3, 1)
            loc3 = Location("")
            loc3.latitude = result3?.get(0)?.latitude ?: 0.0
            loc3.longitude = result3?.get(0)?.longitude ?: 0.0
        }
        else{
            loc3 = Location("")
            loc3.latitude = 0.0
            loc3.longitude = 0.0
        }
        var d = 0
        if (loc3.latitude!=0.0&&loc3.longitude!=0.0)
            d = loc2.distanceTo(loc3).toInt()
        else
            d=0


        var a = loc1.distanceTo(loc2)
        val distance =a+d
        desTxt.text="${(distance / 1000).toInt()} KM"
        moneyTxt.text="${((distance / 1000) *0.75).toInt()} egp"
        }
    }
