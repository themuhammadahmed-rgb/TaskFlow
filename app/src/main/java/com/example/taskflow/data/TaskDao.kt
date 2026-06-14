    package com.example.taskflow.data

    import androidx.room.Dao
    import androidx.room.Delete
    import androidx.room.Insert
    import androidx.room.OnConflictStrategy
    import androidx.room.Query
    import androidx.room.Update
    import com.example.taskflow.data.entity.TaskEntity
    import kotlinx.coroutines.flow.Flow

    @Dao
    interface TaskDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(task: TaskEntity)

        @Update
        suspend fun update(task: TaskEntity)

        @Delete
        suspend fun delete(task: TaskEntity)

        @Query("SELECT * FROM task_table")
        fun getAllTasks(): Flow<List<TaskEntity>>

        @Query("SELECT * FROM task_table WHERE title LIKE :query OR description LIKE :query")
        fun searchTasks(query: String): Flow<List<TaskEntity>>

        @Query("SELECT * FROM task_table WHERE id = :id")
        suspend fun getTaskById(id: Int): TaskEntity?
    }