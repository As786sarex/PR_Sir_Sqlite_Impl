package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wildcardenter.myfab.pr_sir_front_end.R;
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.BookAdaptViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.BookAdaptAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.models.Book_Adaptation;
import com.wildcardenter.myfab.pr_sir_front_end.models.Course;

public class ShowAdaptionActivity extends AppCompatActivity {

    private static final int ADAPT_EDIT_RC =444 ;

    private RecyclerView adaptRecycler;
    private BookAdaptAdapter adapter;
    private BookAdaptViewModel adaptViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_adaption);
        adaptViewModel= ViewModelProviders.of(this).get(BookAdaptViewModel.class);
        adaptRecycler=findViewById(R.id.show_Adapt_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        adaptRecycler.setLayoutManager(manager);
        adapter=new BookAdaptAdapter(this);
        adaptRecycler.setAdapter(adapter);
        adaptViewModel.getAllAdaptList().observe(this,adapts->{
            adapter.setBook_adaptations(adapts);
            adapter.notifyDataSetChanged();
        });
    }

    public void openAdaptEditActivity(View view) {
        Intent intent = new Intent(this, EditAdaptActivity.class);
        startActivityForResult(intent, ADAPT_EDIT_RC);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADAPT_EDIT_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final int adaptCourse = data.getIntExtra("adaptCourse",1);
                final int adaptSem = data.getIntExtra("adaptSem",1);
                final int adaptIsbn = data.getIntExtra("adaptIsbn", 1);
                Book_Adaptation adaptation = new Book_Adaptation(adaptCourse,adaptSem,adaptIsbn);
                adaptViewModel.insertAdapt(adaptation);

            }


            Toast.makeText(this, "Adaptation Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Adaptation Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
