package com.app.passwordmanager

import android.app.Activity
import android.app.AlertDialog
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.hardware.biometrics.BiometricPrompt
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.util.Base64
import com.github.dhaval2404.imagepicker.ImagePicker
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.io.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var coursesRV: RecyclerView? = null
    private var test: LinearLayout? = null
    private var viewmodal: ViewModal? = null
    private var button: ImageView? = null
    val adapter = CourseRVAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coursesRV = findViewById<View>(R.id.recyclerView) as RecyclerView
        test = findViewById<View>(R.id.layout) as LinearLayout

        button = findViewById<View>(R.id.floatingActionButton) as ImageView


        val backup1 = findViewById<ImageView>(R.id.get)
        val profile = findViewById<ImageView>(R.id.profile)

        profile.setOnClickListener {
            ImagePicker.with(this@MainActivity)
                .crop()
                .compress(1024)
                .maxResultSize(
                    1080,
                    1080
                )
                .start()

        }


        val sharedPreferences =
            this.getSharedPreferences("myKey", Context.MODE_PRIVATE)
        val base64: String? = sharedPreferences.getString("image", null)
        if (base64 != null && base64.isNotEmpty()) {
            val byteArray = Base64.decode(base64, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            profile.setImageBitmap(bitmap)
        }

        backup1.setOnClickListener {

            val i = Intent(this@MainActivity, backup::class.java)
            startActivity(i)
            finish()

        }

        coursesRV!!.layoutManager = LinearLayoutManager(this)
        coursesRV!!.setHasFixedSize(true)
        coursesRV!!.adapter = adapter

        viewmodal = ViewModelProviders.of(this)[ViewModal::class.java]
        viewmodal!!.allCourses.observe(this) { models ->
            adapter.submitList(models)
            if (adapter.currentList.isEmpty()) {
                test!!.visibility = View.VISIBLE
                coursesRV!!.visibility = View.GONE
            } else {
                test!!.visibility = View.GONE
                coursesRV!!.visibility = View.VISIBLE

            }
        }

        button!!.setOnClickListener {
            val i = Intent(this@MainActivity, edit::class.java)
            startActivity(i)
            finish()
        }

    }

    override fun onBackPressed() {
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val selectedImg = data!!.data
        val cover = findViewById<View>(R.id.profile) as ImageView
        cover.setImageURI(selectedImg)

        val bitmap = (cover.drawable as BitmapDrawable).bitmap
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        val base64 = Base64.encodeToString(byteArray, Base64.DEFAULT)

        val sharedPreferences = this.getSharedPreferences("myKey", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("image", base64)
        editor.apply()
    }

}
