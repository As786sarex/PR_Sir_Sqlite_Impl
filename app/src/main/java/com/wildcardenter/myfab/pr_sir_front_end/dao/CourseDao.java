package com.wildcardenter.myfab.pr_sir_front_end.dao;

import com.wildcardenter.myfab.pr_sir_front_end.models.Course;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CourseDao {

    @Insert(onConflict = REPLACE)
    void insertCourse(Course course);

    @Query("select * from COURSE order by course")
    LiveData<List<Course>> getAllCourses();

    @Query("select distinct dept from COURSE natural join BOOK_ADAPTATION natural join TEXT where publisher like :pub")
    LiveData<List<String>> getDeptBySpecificPub(String pub);

}
