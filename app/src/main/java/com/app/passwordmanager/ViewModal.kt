package com.app.passwordmanager


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ViewModal(application: Application) : AndroidViewModel(application) {

    private val repository: CourseRepository

    val allCourses: LiveData<List<CourseModal>>

    init {

        repository = CourseRepository(application)
        allCourses = repository.allCourses
    }

    fun insert(model: CourseModal?) {
        repository.insert(model)
    }

    fun update(model: CourseModal?) {
        repository.update(model)
    }

    fun delete(model: CourseModal?) {
        repository.delete(model)
    }

    fun deleteAllCourses() {
        repository.deleteAllCourses()
    }
}