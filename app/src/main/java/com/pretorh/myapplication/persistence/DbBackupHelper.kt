package com.pretorh.myapplication.persistence

import java.util.concurrent.ExecutorService

class DbBackupHelper(private val database: MyDatabase, private val executorService: ExecutorService) {
}
