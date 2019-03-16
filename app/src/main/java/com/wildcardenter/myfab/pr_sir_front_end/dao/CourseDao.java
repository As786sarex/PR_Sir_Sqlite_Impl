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

    @Query("select dept from course natural join book_adaptation natural join text where publisher like :pub" +
            " EXCEPT " +
            "select dept from course natural join book_adaptation natural join text where publisher not like :pub")
    LiveData<List<String>> getDeptBySpecificPub(String pub);

}
