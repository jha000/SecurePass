package com.app.passwordmanager

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class edit : AppCompatActivity() {

    private var username: TextView? = null
    private var password: TextView? = null
    private var titlee: TextView? = null
    private var add: Button? = null
    private lateinit var viewmodal: ViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val myDialog = BottomSheetDialog((this))
        val bphoneDialog = BottomSheetDialog((this))
        val btitle = BottomSheetDialog((this))

        username = findViewById<View>(R.id.username) as TextView
        password = findViewById<View>(R.id.password) as TextView
        titlee = findViewById<View>(R.id.titlee) as TextView
        add = findViewById<View>(R.id.add) as Button
        val nameEdit = findViewById<View>(R.id.nameEdit) as ImageView
        val phoneEdit = findViewById<View>(R.id.phoneEdit) as ImageView
        val titleedit = findViewById<View>(R.id.titleedit) as ImageView

        val imageView = findViewById<View>(R.id.imageView) as ImageView

        imageView.setOnClickListener{
            onBackPressed()
        }

        val adapter = CourseRVAdapter()
        viewmodal = ViewModelProviders.of(this).get(ViewModal::class.java)

        viewmodal.allCourses.observe(this) { models ->
            adapter.submitList(models)
        }

        nameEdit.setOnClickListener {
            myDialog.setContentView(R.layout.bottom)
            val one = myDialog.findViewById<View>(R.id.one) as EditText
            val b1 = myDialog.findViewById<View>(R.id.b1) as Button
            one.requestFocus()
            val inputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            b1.setOnClickListener { view ->

                val x = one.text.toString()
                username!!.text = x

                val value = one.text.toString().trim { it <= ' ' }
                val sharedPref = this.getSharedPreferences("myKey", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("username", value)
                editor.apply()
                val imm =
                    this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                myDialog.dismiss()
            }
            myDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()
        }

        phoneEdit.setOnClickListener {
            bphoneDialog.setContentView(R.layout.bphone)
            val too = bphoneDialog.findViewById<View>(R.id.too) as EditText
            val b2 = bphoneDialog.findViewById<View>(R.id.b2) as Button
            too.requestFocus()
            val inputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            b2.setOnClickListener { view ->
                val x = too.text.toString()
                password!!.text = x
                val value = too.text.toString().trim { it <= ' ' }
                val sharedPref =
                    this.getSharedPreferences("myKey", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("x", value)
                editor.apply()
                val imm =
                    this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                bphoneDialog.dismiss()
            }
            bphoneDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            bphoneDialog.show()
        }

        titleedit.setOnClickListener {
            btitle.setContentView(R.layout.btitle)
            val too = btitle.findViewById<View>(R.id.tooo) as EditText
            val b2 = btitle.findViewById<View>(R.id.b22) as Button
            too.requestFocus()
            val inputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            b2.setOnClickListener { view ->
                val x = too.text.toString()
                titlee!!.text = x
                val value = too.text.toString().trim { it <= ' ' }
                val sharedPref =
                    this.getSharedPreferences("myKey", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("title", value)
                editor.apply()
                val imm =
                    this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                btitle.dismiss()
            }
            btitle.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            btitle.show()
        }

        val sharedPreferences =
            this.getSharedPreferences("myKey", Context.MODE_PRIVATE)
        val value = sharedPreferences.getString("username", "")
        username!!.text = value
        val value1 = sharedPreferences.getString("x", "")
        password!!.text = value1
        val value3 = sharedPreferences.getString("title", "")
        titlee!!.text = value3

        add!!.setOnClickListener{


            val courseName = titlee!!.text.toString()
            val courseDesc = username!!.text.toString()
            val courseDuration = password!!.text.toString()
            if (courseName.isEmpty() ||  courseDesc.isEmpty() || courseDuration.isEmpty()) {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            saveCourse(courseName, courseDesc, courseDuration)

            val sharedPref = this.getSharedPreferences("myKey", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("username", "")
            editor.putString("x", "")
            editor.putString("title", "")
            editor.apply()
            onBackPressed()

        }

    }

    private fun saveCourse(courseName: String,  courseDescription: String, courseDuration: String) {

        val data = Intent()
        data.putExtra(EXTRA_COURSE_NAME, courseName)
        data.putExtra(EXTRA_DESCRIPTION, courseDescription)
        data.putExtra(EXTRA_DURATION, courseDuration)
        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1) {
            data.putExtra(EXTRA_ID, id)
        }
        setResult(RESULT_OK, data)
        val model = CourseModal(courseName, courseDescription, courseDuration)
        viewmodal.insert(model)

    }

    companion object {
        const val EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID"
        const val EXTRA_COURSE_NAME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_NAME"
        const val EXTRA_DESCRIPTION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DESCRIPTION"
        const val EXTRA_DURATION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DURATION"
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

}