package com.example.taskflow.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskflow.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Int,
    onNavigateBack: () -> Unit,
    onEditTask: (Int) -> Unit,
    viewModel: TaskViewModel
) {
    LaunchedEffect(taskId) {
        viewModel.getTaskById(taskId)
    }

    val task by viewModel.selectedTask.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Detail") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { onEditTask(taskId) }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit",
                            tint = Color(0xFF2B3DE7))
                    }
                    IconButton(onClick = {
                        task?.let { viewModel.deleteTask(it) }
                        onNavigateBack()
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete",
                            tint = Color(0xFFE53935))
                    }
                }
            )
        }
    ) { paddingValues ->
        task?.let { t ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Priority badge
                val priorityColor = when (t.priority) {
                    0 -> Color(0xFF4CAF50)
                    1 -> Color(0xFF2B3DE7)
                    else -> Color(0xFFE53935)
                }
                val priorityLabel = when (t.priority) {
                    0 -> "Low"
                    1 -> "Medium"
                    else -> "High"
                }

                Surface(
                    color = priorityColor.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = priorityLabel,
                        color = priorityColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Title
                Text(
                    text = t.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                if (!t.description.isNullOrBlank()) {
                    Text("Description", fontWeight = FontWeight.Medium,
                        fontSize = 13.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(t.description, fontSize = 15.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))

                // Due date
                if (t.dueDate != null) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Due Date", color = Color.Gray, fontSize = 14.sp)
                        Text(
                            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                                .format(Date(t.dueDate)),
                            fontSize = 14.sp, fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                // Status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Status", color = Color.Gray, fontSize = 14.sp)
                    Text(
                        if (t.isCompleted) "Completed" else "Active",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (t.isCompleted) Color(0xFF4CAF50) else Color(0xFF2B3DE7)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Created at
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Created", color = Color.Gray, fontSize = 14.sp)
                    Text(
                        SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                            .format(Date(t.createdAt)),
                        fontSize = 14.sp, fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                // Mark complete button
                Button(
                    onClick = {
                        viewModel.updateTask(t.copy(isCompleted = !t.isCompleted))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (t.isCompleted)
                            Color.Gray else Color(0xFF1A1A2E)
                    )
                ) {
                    Text(
                        if (t.isCompleted) "Mark as Active" else "Mark as Complete",
                        color = Color.White
                    )
                }
            }
        }
    }
}