package com.denofdevelopers.taskmanager.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.denofdevelopers.taskmanager.TaskManagerApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            CreateTaskViewModel(taskManagerApplication().container.taskRepository)
        }

        initializer {
            TaskListViewModel(taskManagerApplication().container.taskRepository)
        }
    }
}

fun CreationExtras.taskManagerApplication(): TaskManagerApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TaskManagerApplication)