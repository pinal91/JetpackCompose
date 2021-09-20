package com.luxsh.myapplication.room


/**
 * Created by Pinal Patel on 11/08/2021.
 * LuxshTech
 */
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "content") val content: String
)