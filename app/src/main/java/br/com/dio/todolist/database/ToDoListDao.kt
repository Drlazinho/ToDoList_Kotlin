package br.com.dio.todolist.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ToDoListDao {
    @Query("SELECT * FROM todolist ORDER BY dataDo ASC")
    fun getAll(): List<ToDoList>
}