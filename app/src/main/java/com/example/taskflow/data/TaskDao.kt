package com.example.taskflow.data

import androidx.room.Dao
import androidx.room.Insert
import com.example.taskflow.data.entity.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: TaskEntity)
}