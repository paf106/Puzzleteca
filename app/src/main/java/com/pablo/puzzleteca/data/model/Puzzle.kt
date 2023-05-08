package com.pablo.puzzleteca.data.model

import android.net.Uri
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "puzzle_table")
data class Puzzle(

    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "photo")
    val photo: String,
    @ColumnInfo(name = "brand")
    val brand: String,
    @ColumnInfo(name = "doneTime")
    val doneTime: String,
    @ColumnInfo(name = "comment")
    val comment: String,
    @ColumnInfo(name = "lentTo")
    val lentTo: String,
    @ColumnInfo(name = "isLent")
    val islent: Boolean,
    @ColumnInfo(name = "pieces")
    val pieces: Int,
    @ColumnInfo(name = "buyDay")
    val buyDay: String,
    @ColumnInfo(name = "referenceNumber")
    val referenceNumber: Int,
    @ColumnInfo(name = "collection")
    val collection: String,
    @ColumnInfo(name = "buyPlace")
    val buyPlace: String,
    @ColumnInfo(name = "isFavourite")
    val isFavourite: Boolean,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idPuzzle")
    val idPuzzle: Int = 0,
) : Parcelable