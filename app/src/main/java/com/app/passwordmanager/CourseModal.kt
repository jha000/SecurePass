package com.app.passwordmanager


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
class CourseModal
    (
    @ColumnInfo
    var courseName: String?,

    @ColumnInfo
    var courseDescription: String?,

    @ColumnInfo
    var courseDuration: String?,
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

}