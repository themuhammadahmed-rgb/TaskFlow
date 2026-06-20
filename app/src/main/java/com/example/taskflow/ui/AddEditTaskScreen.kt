package com.example.taskflow.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskflow.data.entity.TaskEntity
import com.example.taskflow.viewmodel.TaskViewModel
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    taskId: Int = 0,
    onNavigateBack: () -> Unit,
    viewModel: TaskViewModel
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(1) }
    var dueDate by remember { mutableStateOf<Long?>(null) }
    var category by remember { mutableStateOf("") }
    var titleError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (taskId == 0) " Add Task" else "Edit Task",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Title *", fontWeight = FontWeight.Medium, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = title,
                onValueChange = {
                    if (it.length <= 80) {
                        title = it
                        titleError = false
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("What needs to be done?") },
                isError = titleError,
                supportingText = {
                    if (titleError) {
                        Text("Title is required", color = Color.Red)
                    } else {
                        Text(
                            "${title.length}/50", modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    }
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text("Description", fontWeight = FontWeight.Medium, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { if (it.length <= 500) description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                placeholder = { Text("Add more details about this task...") },
                supportingText = {
                    Text(
                        "${description.length}/500",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Priority", fontWeight = FontWeight.Medium, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Low" to 0, "Medium" to 1, "High" to 2).forEach { (label, value) ->
                    val selected = priority == value
                    val color = when (value) {
                        0 -> Color(0xFF4CAF50)
                        1 -> Color(0xFF2B3DE7)
                        else -> Color(0xFFE53935)
                    }
                    FilterChip(
                        selected = selected,
                        onClick = { priority = value },
                        label = { Text(label) },
                        modifier = Modifier.weight(1f),
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = color.copy(alpha = 0.15f),
                            selectedLabelColor = color
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (title.isBlank()) {
                        titleError = true
                    } else {
                        viewModel.addTask(
                            TaskEntity(
                                title = title,
                                description = description.ifBlank { null },
                                priority = priority,
                                dueDate = dueDate,
                                category = category.ifBlank { null },
                                createdAt = System.currentTimeMillis()
                            )
                        )
                        onNavigateBack()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1A2E))
            ) {
                Text("Save Task", color = Color.White, fontSize = 16.sp)
            }
        }

    }
}