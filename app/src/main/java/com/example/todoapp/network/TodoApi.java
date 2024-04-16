package com.example.todoapp.network;

import com.example.todoapp.model.TodoItem;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoApi {
    @GET("todo.php")
    Call<List<TodoItem>> getTodos();
}
