package com.luxsh.myapplication.room


/**
 * Created by Pinal Patel on 11/08/2021.
 * LuxshTech
 */
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Note::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notes(): NotesDao

}