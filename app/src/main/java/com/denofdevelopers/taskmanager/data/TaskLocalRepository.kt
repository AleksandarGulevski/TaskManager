package com.denofdevelopers.taskmanager.data

import kotlinx.coroutines.flow.Flow

class TaskLocalRepository(private val taskDao: TaskDao) : TaskRepository {
    override val tasksStream: Flow<List<Task>> = taskDao.getAll()

    override fun getTaskStream(id: Long): Flow<Task?> = taskDao.get(id)

    override suspend fun addTask(task: Task) = taskDao.insert(task)

    override suspend fun deleteTask(task: Task) = taskDao.delete(task)

    override suspend fun updateTask(task: Task) = taskDao.update(task)
}