package com.mostafij.todowithandroid

data class TodoModel(
    val id: Int,
    val title: String,
    val description: String,
    val isDone: Boolean,
) {}
