package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.StudentViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.StudentAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.models.Student;

public class MainActivity extends AppCompatActivity {
    private static final int STUDENT_EDIT_RC =313 ;
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private StudentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.StudentRecycler);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        adapter=new StudentAdapter(this);
        recyclerView.setAdapter(adapter);
        viewModel= ViewModelProviders.of(this).get(StudentViewModel.class);
        viewModel.getAllStudentList().observe(this,list->{
            adapter.setlist(list);
            adapter.notifyDataSetChanged();
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteStudent(adapter.getItemAt(viewHolder.getAdapterPosition()));
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void openStudentEdit(View view) {
        Intent intent = new Intent(this, StudentEditActivity.class);
        startActivityForResult(intent,STUDENT_EDIT_RC );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STUDENT_EDIT_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String name = data.getStringExtra("name");
                final String regNo = data.getStringExtra("regNo");
                final int bdate = data.getIntExtra("bdate",00000000);
                final String major=data.getStringExtra("major");
                Student model = new Student(regNo,name,major,bdate);
                viewModel.insertStudent(model);
            }


            Toast.makeText(this, "Student Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
