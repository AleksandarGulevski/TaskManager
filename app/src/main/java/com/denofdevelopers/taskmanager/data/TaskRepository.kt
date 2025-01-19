package com.denofdevelopers.taskmanager.data

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    val tasksStream: Flow<List<Task>>

    fun getTaskStream(id: Long): Flow<Task?>
    suspend fun addTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTask(task: Task)
}