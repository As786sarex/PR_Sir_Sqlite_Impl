package com.wildcardenter.myfab.pr_sir_front_end.dao;

import com.wildcardenter.myfab.pr_sir_front_end.models.Text;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TextDao {
    @Insert(onConflict = REPLACE)
    void insertText(Text text);

    @Query("select * from TEXT order by book_isbn")
    LiveData<List<Text>> getAllText();
}
