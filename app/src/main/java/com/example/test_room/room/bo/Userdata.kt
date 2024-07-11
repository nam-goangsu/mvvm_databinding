package com.example.test_room.room.bo

import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity(
    tableName = "Userdata"
/*, indices = arrayOf(Index(value = ["number"])) */
)
data class Userdata(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val number: String,
    val bikename: String,
    val stkilro: Long,
    val stday: String
){}