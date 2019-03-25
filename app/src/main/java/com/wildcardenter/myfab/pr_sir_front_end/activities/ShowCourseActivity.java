package com.wildcardenter.myfab.pr_sir_front_end.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.CourseViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.CourseAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.models.Course;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowCourseActivity extends AppCompatActivity {

    private static final int COURSE_EDIT_RC = 333;
    private RecyclerView showCourseRecycler;
    private CourseViewModel courseViewModel;
    private CourseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_course);
        showCourseRecycler = findViewById(R.id.show_course_recycler);
        courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        showCourseRecycler.setLayoutManager(manager);
        adapter = new CourseAdapter(this);
        showCourseRecycler.setAdapter(adapter);
        courseViewModel.getAllCourseList().observe(this, list -> {
            adapter.setCourseList(list);
            adapter.notifyDataSetChanged();
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                courseViewModel.deleteCourse(adapter.getItemAt(viewHolder.getAdapterPosition()));
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(ShowCourseActivity.this, "deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(showCourseRecycler);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COURSE_EDIT_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String cname = data.getStringExtra("Cname");
                final String cDept = data.getStringExtra("Cdept");
                final int course = data.getIntExtra("Ccourse", 1);
                Course course1 = new Course(course, cname, cDept);
                courseViewModel.insertCourse(course1);
            }


            Toast.makeText(this, "Course Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Course Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public void openCourseEditActivity(View view) {
        Intent intent = new Intent(this, CourseEditActivity.class);
        startActivityForResult(intent, COURSE_EDIT_RC);
    }
}
