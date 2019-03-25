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
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.EnrollViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.EnrollAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.models.Book_Adaptation;
import com.wildcardenter.myfab.pr_sir_front_end.models.Enroll;

public class ShowEnrollActivity extends AppCompatActivity {

    private static final int ENROLL_EDIT_RC = 1001;
    private RecyclerView enrollRecycler;
    private EnrollAdapter adapter;
    private EnrollViewModel enrollViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_enroll);
        enrollRecycler=findViewById(R.id.show_enroll_recycler);
        enrollViewModel= ViewModelProviders.of(this).get(EnrollViewModel.class);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        enrollRecycler.setLayoutManager(manager);
        adapter=new EnrollAdapter(this);
        enrollRecycler.setAdapter(adapter);
        enrollViewModel.getAllEnrollList().observe(this,enrolls->{
            adapter.setEnrolls(enrolls);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                enrollViewModel.deleteEnroll(adapter.getItemAt(viewHolder.getAdapterPosition()));
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(ShowEnrollActivity.this, "deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(enrollRecycler);
    }

    public void openEnrollEditActivity(View view) {
        Intent intent = new Intent(this, EditEnrollActivity.class);
        startActivityForResult(intent, ENROLL_EDIT_RC);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ENROLL_EDIT_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String enrollRegNo = data.getStringExtra("enrollRegNo");
                final int enrollCourse = data.getIntExtra("enrollCourse",1);
                final int enrollSem = data.getIntExtra("enrollSem", 1);
                final int enrollMarks = data.getIntExtra("enrollMarks", 1);
                Enroll enroll = new Enroll(enrollRegNo,enrollCourse,enrollSem,enrollMarks);
                enrollViewModel.insrtEnroll(enroll);


            }


            Toast.makeText(this, "Enroll Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enroll Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
