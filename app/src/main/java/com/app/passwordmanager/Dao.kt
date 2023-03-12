package com.app.passwordmanager

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert
    fun insert(model: CourseModal?)

    @Update
    fun update(model: CourseModal?)

    @Delete
    fun delete(model: CourseModal?)

    @Query("DELETE FROM course_table")
    fun deleteAllCourses()

    @get:Query("SELECT * FROM course_table ORDER BY courseName ASC")
    val allCourses: LiveData<List<CourseModal?>?>?
}