package com.mic.tasknotwo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mic.tasknotwo.Product
import com.mic.tasknotwo.ProductViewModel
import com.mic.tasknotwo.R
import com.mic.tasknotwo.SwipeToDeleteCallback
import com.mic.tasknotwo.adapter.MenusAdapter
import com.mic.tasknotwo.adapter.menuSwipClick
import kotlinx.android.synthetic.main.fragment_view.view.*

class viewFragment : Fragment(),SwipeRefreshLayout.OnRefreshListener, menuSwipClick {
    private  lateinit var productViewModel: ProductViewModel
    var swipeLayout: SwipeRefreshLayout? = null
    val menuAdapter=MenusAdapter(this)
    var count=1;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root=inflater.inflate(R.layout.fragment_view, container, false)



        productViewModel= ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.getLimitProduct(0,2)
        productViewModel.allProduct.observe(
            viewLifecycleOwner, Observer {
                    cdlist->cdlist?.let {
                if(it.isEmpty()){
                    productViewModel.addData()
                    productViewModel.getLimitProduct(0,2)
                }else{
                    count++
                    menuAdapter.updateMenuslist(it)
                }


            }


            }
        )
        root.recycler_menu.layoutManager= LinearLayoutManager(root.context)
        root.recycler_menu.adapter=menuAdapter

        val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = root.recycler_menu.adapter
                menuAdapter.removeAt(viewHolder.adapterPosition)
               // productViewModel.getDelete(viewHolder.adapterPosition)
            }


        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(root.recycler_menu)

        root.btn_float.setOnClickListener {
            findNavController().navigate(viewFragmentDirections.actionViewFragmentToSaveFragment("","","","",0))
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeLayout = view.findViewById(R.id.swipe_data) as SwipeRefreshLayout
        swipeLayout!!.setOnRefreshListener(this);
        swipeLayout!!.setColorScheme(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light);
    }

    override fun onRefresh() {
        readData( 0,2*count)
    }
    fun readData(start:Int,end:Int){
        productViewModel.getLimitProduct(start,end)
        productViewModel.allProduct.observe(
            viewLifecycleOwner, Observer {
                    cdlist->cdlist?.let {
                if(!it.isEmpty()){
                    count++
                    swipeLayout?.isRefreshing =   false
                }
                menuAdapter.updateMenuslist(it)

            }


            }
        )
    }

    override fun onFunClick(attlist: Product) {
        System.out.println("The delete button>>"+attlist.pid.toString())
        productViewModel.getDelete(attlist.pid)
    }

    override fun onUpdateClick(attlist: Product) {
        findNavController().navigate(viewFragmentDirections.actionViewFragmentToSaveFragment(attlist.productName,attlist.productPrice.toString(),attlist.productQuality,attlist.productImg,attlist.pid))
    }

}