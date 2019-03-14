package com.wildcardenter.myfab.pr_sir_front_end.dao;

import com.wildcardenter.myfab.pr_sir_front_end.models.Student;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface StudentDao {
    @Insert(onConflict = REPLACE)
    void insertStudent(Student student);

    @Query("select * from STUDENT order by name")
    LiveData<List<Student>> getAllStudents();

    @Query("select * from STUDENT where name=:name")
    LiveData<List<Student>> getStudentByCondition(String name);

}
