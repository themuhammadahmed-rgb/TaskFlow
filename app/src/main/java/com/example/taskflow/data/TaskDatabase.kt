package com.example.taskflow.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskflow.data.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {

    }
}