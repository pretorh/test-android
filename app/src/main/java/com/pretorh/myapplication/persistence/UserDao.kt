package com.pretorh.myapplication.persistence

import androidx.room.*

@Dao
interface UserDao {
    @Query("select * from users")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User)
}

@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String
)
