package com.mic.tasknotwo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Product {
    @PrimaryKey(autoGenerate = true)
    var pid:Int=0
    @ColumnInfo
    var productName:String=""
    @ColumnInfo
    var productPrice:Double=0.0
    @ColumnInfo
    var productQuality:String=""
    @ColumnInfo
    var productImg:String=""

}