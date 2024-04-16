package com.example.todoapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todoapp.adapter.TodoAdapter;
import com.example.todoapp.model.TodoItem;
import com.example.todoapp.network.RetrofitClientInstance;
import com.example.todoapp.network.TodoApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchTodos();
    }

    private void fetchTodos() {
        TodoApi service = RetrofitClientInstance.getRetrofitInstance().create(TodoApi.class);
        Call<List<TodoItem>> call = service.getTodos();

        call.enqueue(new Callback<List<TodoItem>>() {
            @Override
            public void onResponse(Call<List<TodoItem>> call, Response<List<TodoItem>> response) {
                adapter = new TodoAdapter(response.body());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<TodoItem>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
