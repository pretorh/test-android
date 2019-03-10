package com.pretorh.myapplication.persistence

import android.util.Log
import com.google.gson.Gson
import java.io.File
import java.util.concurrent.ExecutorService

class DbBackupHelper(private val database: MyDatabase, private val executorService: ExecutorService, private val root: File) {
    private val logTag by lazy { this.javaClass.simpleName }
    private val gson = Gson()
    private val backupFile = File(root, "backup.json")

    fun backup() {
        executorService.submit {
            val json = serializeAll()
            writeToFile(json)
        }
    }

    private fun writeToFile(json: String) {
        try {
            backupFile.parentFile.mkdirs()
            backupFile.writeText(json)
            Log.d(logTag, "wrote ${json.length} chars to backup file ${backupFile.path}")
        } catch (exception: Exception) {
            Log.w(logTag, "failed to write backup", exception)
        }
    }

    private fun serializeAll(): String {
        val users = database.user().getAll()
        return gson.toJson(users.map { BackupUser(it.id, it.firstName) })
    }
}

data class BackupUser(val id: Int, val name: String)
