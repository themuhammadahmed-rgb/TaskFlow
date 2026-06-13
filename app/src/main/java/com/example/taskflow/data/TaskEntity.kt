package com.example.taskflow.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val priority: Int,
    val dueDate: Long? = null,
    val isCompleted: Boolean = false,
    val category: String? = null,
    val createdAt: Long
)
