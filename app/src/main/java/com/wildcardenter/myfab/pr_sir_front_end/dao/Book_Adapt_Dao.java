package com.wildcardenter.myfab.pr_sir_front_end.dao;

import com.wildcardenter.myfab.pr_sir_front_end.models.Book_Adaptation;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface Book_Adapt_Dao {

    @Insert(onConflict = REPLACE)
    void insertAdaptation(Book_Adaptation adaptation);

    @Delete
    void deleteAdaptation(Book_Adaptation book_adaptation);

    @Query("select * from BOOK_ADAPTATION order by book_isbn")
    LiveData<List<Book_Adaptation>> getAllAdaptation();
}
