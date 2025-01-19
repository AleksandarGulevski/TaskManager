package com.denofdevelopers.taskmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denofdevelopers.taskmanager.data.Task
import com.denofdevelopers.taskmanager.data.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class TaskListViewModel(private val taskRepository: TaskRepository): ViewModel()  {

    val tasksStream: Flow<List<Task>> = taskRepository.tasksStream

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskRepository.deleteTask(task)
    }
}