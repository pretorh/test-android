package com.pretorh.myapplication

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.pretorh.myapplication.persistence.buildDbVersion2Upgrader
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
@RunWith(AndroidJUnit4::class)
class MigrationTest {
    private val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        com.pretorh.myapplication.persistence.MyDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun test1to2() {
        var db = helper.createDatabase(TEST_DB_NAME, 1).apply {
            // insert using plain sql
            execSQL("insert into users(id, first_name) values (1, 'test')")

            close()
        }

        // re open and run migration 1->2
        db = helper.runMigrationsAndValidate(TEST_DB_NAME, 2, false, buildDbVersion2Upgrader())

        // check the data
        db.apply {
            val cursor = query("select * from users;")
            val rows = cursor.count
            Assert.assertEquals(1, rows)

            val columnIndex = cursor.getColumnIndex("first_name")
            cursor.moveToNext()
            val firstName = cursor.getString(columnIndex)
            Assert.assertEquals("test", firstName)
        }
    }

    companion object {
        private const val TEST_DB_NAME = "TEST_DB"
    }
}
