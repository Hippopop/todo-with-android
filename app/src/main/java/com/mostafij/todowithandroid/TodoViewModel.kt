package com.mostafij.todowithandroid

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    var currentTodoList by mutableStateOf(
        arrayOf<TodoModel>(),
    )

    fun toggleTodoState(todoId: Int, currentState: Boolean): Unit {
        currentTodoList = currentTodoList.map {
            if (it.id == todoId) {
                val newTodo = it.copy(isDone = !currentState);
                println(newTodo)
                newTodo
            } else {
                it
            }
        }.toTypedArray()
        println("TODO($todoId) State($currentState)");
        println("${currentTodoList.map { it.toString() }}")
    }

    fun addNewTodo(title: String, description: String): Boolean {
        return try {
            val newIndex = currentTodoList.size + 1;
            val newTodo = TodoModel(
                id = newIndex,
                title = title,
                description = description,
                isDone = false,
            );
            currentTodoList = currentTodoList.plus(newTodo);

            true
        } catch (e: Exception) {
            false
        }
    }
}