package com.mic.tasknotwo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.mic.tasknotwo.Product
import com.mic.tasknotwo.ProductViewModel
import com.mic.tasknotwo.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_menu.view.*


class MenusAdapter(var rd:menuSwipClick) :RecyclerView.Adapter<MenusAdapter.MenusViewHolder>(){
    var count:Int=0

    var resultlist:List<Product> = listOf()
    inner class MenusViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindMenus(result:Product){
            itemView.txt_menuname.text=result.productName
            itemView.txt_menuprice.text=result.productPrice.toString()+" MMK for each"
            Picasso.get()
                .load(result.productImg)
                .placeholder(R.drawable.food)
                .into(itemView.img_menupic)

            itemView.item_card.setOnClickListener {
                rd.onUpdateClick(result)
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenusViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
        return MenusViewHolder(view)
    }

    override fun getItemCount(): Int {
       return resultlist.size
    }

    override fun onBindViewHolder(holder: MenusViewHolder, position: Int) {
       holder.bindMenus(resultlist[position])
    }
    fun updateMenuslist(menuslist:List<Product>){
        this.resultlist=menuslist
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        this.resultlist.drop(position)
        rd.onFunClick(this.resultlist[position])
        notifyItemRemoved(position)
    }
}