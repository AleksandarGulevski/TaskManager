package com.denofdevelopers.taskmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denofdevelopers.taskmanager.data.Task
import com.denofdevelopers.taskmanager.data.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class CreateTaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    fun getTaskStream(id: Long): Flow<Task?> = taskRepository.getTaskStream(id)

    fun saveTask(
        id: Long,
        title: String,
        description: String,
    ) {
        val task = Task(id, title, description)
        viewModelScope.launch {
            if (id > 0) {
                taskRepository.updateTask(task)
            } else {
                taskRepository.addTask(task)
            }
        }
    }
}