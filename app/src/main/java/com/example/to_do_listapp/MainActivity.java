package com.example.to_do_listapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText e1;
    TextView t1;
    Button b1, b2;
    DatabaseHelper d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = findViewById(R.id.e1);
        t1 = findViewById(R.id.t1);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);

        d = new DatabaseHelper(this);
    }

    public void addme(View view) {

        String task = e1.getText().toString().trim();

        if (task.isEmpty()) {
            e1.setError("Enter a task");
            return;
        }

        if (d.addTask(task)) {
            Toast.makeText(this, "Task Added Successfully",
                    Toast.LENGTH_SHORT).show();

            e1.setText("");
        } else {
            Toast.makeText(this, "Failed to Add Task",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void show(View view) {
        String tasks = d.getAllTasks();
        t1.setText(tasks);
    }

    @Override
    protected void onDestroy() {
        if (d != null) {
            d.close();
        }
        super.onDestroy();
    }
}
