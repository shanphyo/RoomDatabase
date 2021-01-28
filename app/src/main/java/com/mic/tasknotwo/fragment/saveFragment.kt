package com.mic.tasknotwo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mic.tasknotwo.Product
import com.mic.tasknotwo.ProductViewModel
import com.mic.tasknotwo.R
import kotlinx.android.synthetic.main.fragment_save.*
import kotlinx.android.synthetic.main.fragment_save.view.*

class saveFragment : Fragment() {
    private lateinit var productViewModel: ProductViewModel
    private lateinit var dbReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase
    private var pId: String = ""
    var productId=0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment_save, container, false)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

            findNavController().navigate(saveFragmentDirections.actionSaveFragmentToViewFragment())
        }
        var messageArgs = arguments?.let {
            saveFragmentArgs.fromBundle(it)
        }
        if(messageArgs!=null){
            if(messageArgs.pName!=""){
                root.txt_pName.setText(messageArgs.pName)
            }
            if(messageArgs.pPrice!=""){
                root.txt_pPrice.setText(messageArgs.pPrice)
            }
            if(messageArgs.pQty!=""){
                root.txt_pQuality.setText(messageArgs.pQty)
            }
            if(messageArgs.pImg!=""){
                root.txt_pImg.setText(messageArgs.pImg)
            }
            if(messageArgs.pid!=0){
                productId=messageArgs.pid
                root.btn_submit.text="Update"
            }else{
                root.btn_submit.text="Save"
            }





        }
        productViewModel= ViewModelProvider(this).get(ProductViewModel::class.java)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseDatabase = FirebaseDatabase.getInstance()
        dbReference = firebaseDatabase.getReference("product")
        pId = dbReference.push().key.toString()


      btn_submit.setOnClickListener {
          val p=Product()

          p.productName=txt_pName.text.toString()
          p.productPrice=txt_pPrice.text.toString().toDouble()
          p.productQuality=txt_pQuality.text.toString()
          p.productImg=txt_pImg.text.toString()
          if(productId!=0){
              productViewModel.update(p,productId)
          }else{
              productViewModel.insert(p)
              dbReference.child(pId).setValue(p)
          }
          findNavController().navigate(saveFragmentDirections.actionSaveFragmentToViewFragment())

      }
        btn_cancel.setOnClickListener {
            findNavController().navigate(saveFragmentDirections.actionSaveFragmentToViewFragment())
        }

    }

}