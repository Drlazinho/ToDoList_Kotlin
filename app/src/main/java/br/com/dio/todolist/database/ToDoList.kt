package br.com.dio.todolist.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoList(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "Task") val taskDo : String,
    @NonNull @ColumnInfo(name = "Data") val dataDo : Int,
    @NonNull @ColumnInfo(name = "Time") val timeDo : Int

    )