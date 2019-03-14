package com.wildcardenter.myfab.pr_sir_front_end.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.wildcardenter.myfab.pr_sir_front_end.R;

public class EditEnrollActivity extends AppCompatActivity {
    private EditText editRegNo,editCourse,editSem,editMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_enroll);
        editCourse=findViewById(R.id.Edit_Enroll_CourseNo);
        editRegNo=findViewById(R.id.Edit_Enroll_Regno);
        editSem=findViewById(R.id.Edit_Enroll_Sem);
        editMarks=findViewById(R.id.Edit_Enroll_Marks);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(editRegNo.getText()) || TextUtils.isEmpty(editCourse.getText())
                    ||TextUtils.isEmpty(editSem.getText())||editRegNo.getText().toString().trim().contains(" ")
                    ||TextUtils.isEmpty(editMarks.getText()) ||editCourse.getText().toString().trim().contains(" ")||editSem.getText().toString().trim().contains(" ")
                    ||editMarks.getText().toString().trim().contains(" ")) {
                setResult(RESULT_CANCELED, intent);
                finish();
            } else {
                String regno=editRegNo.getText().toString();
                int course=Integer.parseInt(editCourse.getText().toString());
                int sem=Integer.parseInt(editSem.getText().toString());
                int marks=Integer.parseInt(editMarks.getText().toString());
                intent.putExtra("enrollRegNo",regno );
                intent.putExtra("enrollCourse", course);
                intent.putExtra("enrollSem",sem);
                intent.putExtra("enrollMarks",marks);
                setResult(RESULT_OK, intent);
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
