package com.example.taskflow.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskflow.ui.AddEditTaskScreen
import com.example.taskflow.ui.TaskDetailScreen
import com.example.taskflow.ui.TaskListScreen
import com.example.taskflow.viewmodel.TaskViewModel
import com.example.taskflow.viewmodel.TaskViewModelFactory

@Composable
fun TaskFlowNavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val viewModel: TaskViewModel = viewModel(
        factory = TaskViewModelFactory(context.applicationContext as Application)
    )
    NavHost(
        navController = navController,
        startDestination = NavRoutes.TaskList.route
    ) {
        composable(route = NavRoutes.TaskList.route) {
            TaskListScreen(
                onAddTask = {
                    navController.navigate(NavRoutes.AddEditTask.createRoute())
                },
                onTaskClick = { taskId ->
                    navController.navigate(NavRoutes.TaskDetail.createRoute(taskId))
                },
                viewModel = viewModel
            )

        }
        composable(
            route = NavRoutes.AddEditTask.route,
            arguments = listOf(
                navArgument(NavRoutes.TASK_ID_ARG) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt(NavRoutes.TASK_ID_ARG) ?: 0
            AddEditTaskScreen(
                taskId = taskId,
                onNavigateBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
        composable(
            route = NavRoutes.TaskDetail.route,
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskDetailScreen(
                taskId = taskId,
                onNavigateBack = { navController.popBackStack() },
                onEditTask = { id ->
                    navController.navigate(NavRoutes.AddEditTask.createRoute(id))
                },
                viewModel = viewModel
            )
        }
    }
}