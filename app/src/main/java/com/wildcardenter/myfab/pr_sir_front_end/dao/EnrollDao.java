package com.wildcardenter.myfab.pr_sir_front_end.dao;

import com.wildcardenter.myfab.pr_sir_front_end.models.Enroll;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface EnrollDao {
    @Insert(onConflict = REPLACE)
    void insertEnoll(Enroll enroll);

    @Delete
    void deleteEnroll(Enroll enroll);

    @Query("select * from enroll order by regno")
    LiveData<List<Enroll>> getAllEnrolls();
}
