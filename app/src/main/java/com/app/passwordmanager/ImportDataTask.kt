package com.app.passwordmanager

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStreamReader

class ImportDataTask(private val context: Context, private val uri: Uri) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void?): Void? {
        val inputStream = context.contentResolver.openInputStream(uri)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line = reader.readLine()
        val courseList = ArrayList<CourseModal>()
        while (line != null) {
            val fields = line.split(",").toTypedArray()
            val courseName = fields[1]
            val courseDescription = fields[3]
            val courseDuration = fields[2]
            val course = CourseModal(courseName, courseDescription, courseDuration)
            courseList.add(course)
            line = reader.readLine()
        }
        val dao = CourseDatabase.getInstance(context).Dao()
        for (course in courseList) {
            dao.insert(course)
        }
        return null
    }
}
