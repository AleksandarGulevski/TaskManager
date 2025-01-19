package com.denofdevelopers.taskmanager

import android.app.Application
import com.denofdevelopers.taskmanager.data.AppContainer
import com.denofdevelopers.taskmanager.data.AppDataContainer

class TaskManagerApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}