package com.wildcardenter.myfab.pr_sir_front_end.dao;

import com.wildcardenter.myfab.pr_sir_front_end.models.Text;
import com.wildcardenter.myfab.pr_sir_front_end.models.TextByCs;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
public interface TextDao {
    @Insert(onConflict = REPLACE)
    void insertText(Text text);

    @Query("select * from TEXT order by book_isbn")
    LiveData<List<Text>> getAllText();

    @Query("select course,book_isbn,book_title from TEXT natural join COURSE natural join" +
            " BOOK_ADAPTATION where dept like 'CSE' group by course having count(course)>=2 order by book_title")
    LiveData<List<TextByCs>> getAllBookOfferedByCs();

}
