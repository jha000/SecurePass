package com.app.passwordmanager

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import kotlin.math.exp

class backup : AppCompatActivity() {

    private lateinit var export01: TextView
    private var import01: TextView? = null
    private var coursesRV: RecyclerView? = null
    private var viewmodal: ViewModal? = null
    val adapter = CourseRVAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_backup)

        coursesRV = findViewById<View>(R.id.recyclerView) as RecyclerView

        val imageView = findViewById<View>(R.id.get) as ImageView
        val message1 = findViewById<View>(R.id.message) as TextView

        val file = File(getExternalFilesDir(null), "")
        message1.text = "File Location: ${file.absolutePath}"

        imageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        coursesRV!!.layoutManager = LinearLayoutManager(this)
        coursesRV!!.setHasFixedSize(true)
        coursesRV!!.adapter = adapter

        viewmodal = ViewModelProviders.of(this)[ViewModal::class.java]
        viewmodal!!.allCourses.observe(this) { models ->
            adapter.submitList(models)
        }

        export01 = findViewById<View>(R.id.export01) as TextView
        import01 = findViewById<View>(R.id.import01) as TextView

        import01!!.setOnClickListener {

            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type =
                "*/*"
            startActivityForResult(intent, backup.REQUEST_CODE_IMPORT_BACKUP)
        }

        export01.setOnClickListener {
            val viewModel = ViewModelProvider(this).get(ViewModal::class.java)
            val backupData = viewModel.allCourses.value?.joinToString("\n") { courseModal ->
                "${courseModal.id},${courseModal.courseName},${courseModal.courseDuration},${courseModal.courseDescription}"
            }

            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setTitle("Backup File Name")

            val input = EditText(this)
            input.hint = "Enter file name here"
            dialogBuilder.setView(input)

            dialogBuilder.setPositiveButton("Save") { _, _ ->

                val fileName = input.text.toString()
                val file = File(getExternalFilesDir(null), fileName)

                OutputStreamWriter(FileOutputStream(file)).use { writer ->
                    writer.write(backupData)
                }

                val message = "Backup saved to: ${file.absolutePath}"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }

            dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }

            val dialog = dialogBuilder.create()
            dialog.show()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_IMPORT_BACKUP && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                importData(uri)
            }
        }
    }

    fun importData(uri: Uri) {
        ImportDataTask(this, uri).execute()
        Toast.makeText(this, "Data restored successfully", Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val REQUEST_CODE_IMPORT_BACKUP = 1001
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}