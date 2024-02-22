package com.mostafij.todowithandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mostafij.todowithandroid.ui.theme.TODOWithAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODOWithAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val todoList = arrayListOf<TodoModel>(
                        TodoModel(
                            id = 1,
                            title = "Testing TODO!",
                            description = "Description for Testing TODO!",
                            isDone = false,
                        ),
                        TodoModel(
                            id = 2,
                            title = "Testing TODO! 2",
                            description = "Description for Testing TODO!",
                            isDone = false,
                        ),
                        TodoModel(
                            id = 3,
                            title = "Testing TODO! 3",
                            description = "Description for Testing TODO!",
                            isDone = false,
                        ),
                        TodoModel(
                            id = 4,
                            title = "Testing TODO! 4",
                            description = "Description for Testing TODO!",
                            isDone = false,
                        ),
                        TodoModel(
                            id = 5,
                            title = "Testing TODO! 5",
                            description = "Description for Testing TODO!",
                            isDone = true,
                        ),
                    );
                    LazyColumn() {
                        items(todoList.size) { index -> TodoListItem(todoModel = todoList[index]); };
                    }
                }
            }
        }
    }
}

@Composable
fun TodoListItem(todoModel: TodoModel) {
    Column {
        Text(
            text = todoModel.title,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleMedium,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
        Row {
            Text(text = todoModel.description)
            if (todoModel.isDone) {
                Text(text = "Complete")
            } else {
                Text(text = "Pending")
            }
        }
    }
}

data class TodoModel(
    val id: Int,
    val title: String,
    val description: String,
    val isDone: Boolean,
) {
    fun toggleState(state: Boolean) {}
}
