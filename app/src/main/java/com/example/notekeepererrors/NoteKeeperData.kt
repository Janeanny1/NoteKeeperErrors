package com.example.notekeepererrors

 data class CourseInfo  (val courseId: String, val title: String) {
    override fun toString(): String {
        return title
    }
}

class NoteInfo(var course: CourseInfo? = null, var title: String? = null, var text: String? = null)
