package com.mic.tasknotwo

class Constant {

   fun addData(productViewModel: ProductViewModel) {

       for (i in 1..20) {
           var p=Product()
           p.productName=i.toString()
           p.productPrice=9.9
           p.productQuality="Good"
           p.productImg="https://uploads-ssl.webflow.com/5d556af3fe21d65f602dca94/5de6b00d4d802a000e49b537_eOD15DbonZj4.jpeg"
           productViewModel.insert(p)
       }
   }
}