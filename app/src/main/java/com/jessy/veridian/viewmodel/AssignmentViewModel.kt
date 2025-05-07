package com.jessy.veridian.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jessy.veridian.database.AppDatabase
import com.jessy.veridian.database.entities.Assignment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class AssignmentViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val assignmentDao = AppDatabase.getDatabase(app).assignmentDao()

    val allAssignments: LiveData<List<Assignment>> = assignmentDao.getAllAssignments()

    fun addAssignment(title: String, description: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newAssignment = Assignment(
                title = title,
                description = description,
                imagePath = savedImagePath // Use saved image path
            )
            assignmentDao.insertAssignment(newAssignment)
        }
    }

    fun updateAssignment(updatedAssignment: Assignment) {
        viewModelScope.launch(Dispatchers.IO) {
            assignmentDao.updateAssignment(updatedAssignment)
        }
    }

    fun deleteAssignment(assignment: Assignment) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete image from storage
            deleteImageFromInternalStorage(assignment.imagePath)
            assignmentDao.deleteAssignment(assignment)
        }
    }

    // Save image permanently to internal storage
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }

    private fun deleteImageFromInternalStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
