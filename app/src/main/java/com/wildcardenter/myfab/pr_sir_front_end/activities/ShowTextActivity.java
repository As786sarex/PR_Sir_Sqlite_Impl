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
import com.wildcardenter.myfab.pr_sir_front_end.ViewModels.TextViewModel;
import com.wildcardenter.myfab.pr_sir_front_end.adapters.TextAdapter;
import com.wildcardenter.myfab.pr_sir_front_end.models.Text;

public class ShowTextActivity extends AppCompatActivity {

    private static final int TEXT_EDIT_RC =334 ;

    private RecyclerView showTextRecycler;
    private TextAdapter adapter;
    private TextViewModel textViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);
        showTextRecycler=findViewById(R.id.show_text_recycler);
        LinearLayoutManager manager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        showTextRecycler.setLayoutManager(manager);
        adapter=new TextAdapter(this);
        textViewModel= ViewModelProviders.of(this).get(TextViewModel.class);
        showTextRecycler.setAdapter(adapter);
        textViewModel.getAllTextList().observe(this,list->{
            adapter.setTextList(list);
            adapter.notifyDataSetChanged();
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                textViewModel.deleteText(adapter.getItemAt(viewHolder.getAdapterPosition()));
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                Toast.makeText(ShowTextActivity.this, "deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(showTextRecycler);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_EDIT_RC && resultCode == RESULT_OK) {
            if (data != null) {
                final String title = data.getStringExtra("title");
                final String publisher = data.getStringExtra("publisher");
                final String author = data.getStringExtra("author");
                final int isbn = data.getIntExtra("isbn", 1);
                Text text = new Text(isbn, title, publisher,author);
                textViewModel.insertText(text);
            }


            Toast.makeText(this, "Course Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Course Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    public void openTextEditActivity(View view) {
        Intent intent = new Intent(this, EditTextActivity.class);
        startActivityForResult(intent, TEXT_EDIT_RC);
    }
}
