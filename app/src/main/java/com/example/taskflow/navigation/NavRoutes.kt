package com.example.taskflow.navigation

sealed class NavRoutes(val route: String) {
    object TaskList : NavRoutes("task_list")

    object AddEditTask : NavRoutes("add_edit_task?taskId={taskId}") {
        fun createRoute(taskId: Int = 0) = "add_edit_task?taskId=$taskId"
    }

    companion object {
        const val TASK_ID_ARG = "taskId"
    }
}