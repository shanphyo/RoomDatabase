package ccom.mic.tasknotwo

import androidx.lifecycle.LiveData
import com.mic.tasknotwo.Product
import com.mic.tasknotwo.ProductDao


class ProductResposity (private val productDao: ProductDao){
    val allCard:LiveData<List<Product>> =productDao.getAllProduct()
    suspend fun  insert (product:Product){
        productDao.insert(product)
    }
    fun getAllProcuct(start:Int,end:Int):LiveData<List<Product>>{
        return productDao.getAllProductWithPagination(end)
    }

    fun getDelete(id:Int){
        productDao.getDelete(id)
    }
    fun getUpdate(product: Product,pid:Int){
       productDao.getUpdate(product.productName,product.productPrice,product.productQuality,product.productImg,pid)
    }
}