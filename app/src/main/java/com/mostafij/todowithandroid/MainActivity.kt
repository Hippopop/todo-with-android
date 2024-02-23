package com.mostafij.todowithandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
                    MyApp();
                }
            }
        }
    }
}


@Composable
fun MyApp() {
    val todoViewModel: TodoViewModel = viewModel();

    Column(modifier = Modifier.padding(16.dp)) {
        TodoAddSection(addFunction = todoViewModel::addNewTodo);
        CurrentTodoSection(todoViewModel = todoViewModel);
    }
}

@Composable
fun CurrentTodoSection(todoViewModel: TodoViewModel) {
    Column {
        Text(
            text = "Current TODOs :",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 12.dp),
        );
        Card(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 12.dp),
            ) {
                items(todoViewModel.currentTodoList.size) { index ->
                    TodoListItem(
                        todoModel = todoViewModel.currentTodoList[index],
                        toggleFunction = todoViewModel::toggleTodoState,
                    );
                };
            }
        }
    }
}

@Composable
fun TodoAddSection(
    addFunction: (String, String) -> Boolean
) {
    var titleState by remember {
        mutableStateOf("")
    };
    var descriptionState by remember {
        mutableStateOf("")
    };


    Column {
        Text(text = "Create New TODO :", style = MaterialTheme.typography.headlineMedium);
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                OutlinedTextField(
                    value = titleState,
                    label = { Text(text = "TODO Title") },
                    shape = RoundedCornerShape(8.dp),
                    onValueChange = { title -> titleState = title },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                );
                OutlinedTextField(
                    value = descriptionState,
                    label = { Text(text = "TODO Description") },
                    shape = RoundedCornerShape(8.dp),
                    onValueChange = { desc -> descriptionState = desc },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                );
                ElevatedButton(
                    onClick = {
                        val res = addFunction(titleState, descriptionState);
                        if (res) {
                            titleState = "";
                            descriptionState = "";
                        } else {
                            println("Add Todo Error! State : ($titleState || $descriptionState");
                        };
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                ) {
                    Text(text = "Add TODO");
                }
            }
        }
    }
}

@Composable
fun TodoListItem(todoModel: TodoModel, toggleFunction: (Int, Boolean) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = todoModel.title,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            );
            Switch(
                checked = todoModel.isDone,
                onCheckedChange = { currentState -> toggleFunction(todoModel.id, !currentState) },
            );
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = todoModel.description)
            if (todoModel.isDone)
                Text(text = "Complete")
            else
                Text(text = "Pending")
        }
    }
}


