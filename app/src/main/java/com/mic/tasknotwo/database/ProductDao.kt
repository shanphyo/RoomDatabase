package com.mic.tasknotwo

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun  insert(product: Product)

    @Query("select * from Product")
    fun getAllProduct():LiveData<List<Product>>

    @Query("delete from Product  where pid=:id")
    fun getDelete(id:Int)

    @Query("select * from Product  ORDER BY pid  LIMIT :end")
    fun getAllProductWithPagination(end:Int):LiveData<List<Product>>

    @Query("Update  Product SET productName=:pName,productPrice=:pPrice,productQuality=:pQty,productImg=:pImg where pid=:pid")
    fun getUpdate(pName:String,pPrice:Double,pQty:String,pImg:String,pid:Int)
}