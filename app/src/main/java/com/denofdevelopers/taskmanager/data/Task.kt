package com.denofdevelopers.taskmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String
)