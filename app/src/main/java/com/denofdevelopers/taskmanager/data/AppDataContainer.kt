package com.denofdevelopers.taskmanager.data

import android.content.Context

class AppDataContainer(private val context: Context) : AppContainer {

    override val taskRepository: TaskRepository by lazy {
        TaskLocalRepository(AppDatabase.getDatabase(context).taskDao())
    }
}