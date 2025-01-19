package com.denofdevelopers.taskmanager.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun get(id: Long): Flow<Task>

    @Insert
    suspend fun insert(juice: Task)

    @Delete
    suspend fun delete(juice: Task)

    @Update
    suspend fun update(juice: Task)
}