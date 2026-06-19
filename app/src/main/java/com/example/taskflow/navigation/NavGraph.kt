package com.example.taskflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun TaskFlowNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.TaskList.route
    ) {
        composable(route = NavRoutes.TaskList.route) {

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

        }
    }
}