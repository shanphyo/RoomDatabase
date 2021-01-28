package com.mic.tasknotwo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import ccom.mic.tasknotwo.ProductResposity
import kotlinx.coroutines.launch


class ProductViewModel (application: Application):AndroidViewModel(application){
    private  val resposity: ProductResposity

    lateinit var allProduct:LiveData<List<Product>>
   // var limitProduct: <List<Product>>
    init {
        val cardDao = ProductDatabase.getDatabase(application).productDao()
        resposity= ProductResposity(cardDao)
      //  allProduct =resposity.allCard


    }



    fun insert(product:Product) =viewModelScope.launch {
        resposity.insert(product)
    }
    fun getDelete(id:Int){
        resposity.getDelete(id)
    }
    fun getLimitProduct(start:Int,end:Int){
        allProduct=resposity.getAllProcuct(start,end)
    }
    fun update(product: Product,pid:Int){
        resposity.getUpdate(product,pid)
    }

    fun addData() {

        for (i in 1..20) {
            var p=Product()
            p.productName=i.toString()
            p.productPrice=9.9
            p.productQuality="Good"
            p.productImg="https://yourmileagemayvary.net/wp-content/uploads/2019/04/Screen-Shot-2019-04-10-at-5.44.04-PM.png"
            insert(p)
        }
    }
}
