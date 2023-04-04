package edu.uncc.inclass09;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Grade.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GradeDao gradeDao();
}
