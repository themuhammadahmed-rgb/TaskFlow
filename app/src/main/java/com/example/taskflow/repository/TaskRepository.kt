package com.example.taskflow.repository

import com.example.taskflow.data.TaskDao
import com.example.taskflow.data.entity.TaskEntity

class TaskRepository(private val taskDao: TaskDao) {
    suspend fun insertTask(task: TaskEntity) {
        taskDao.insert(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.update(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.delete(task)
    }

    fun getAllTasks() = taskDao.getAllTasks()

    fun searchTasks(query: String) = taskDao.searchTasks(query)

    suspend fun getTaskById(id: Int) = taskDao.getTaskById(id)

}