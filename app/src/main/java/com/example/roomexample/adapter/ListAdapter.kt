package com.example.roomexample.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.AddEditNoteActivity
import com.example.roomexample.UpdateActivity
import com.example.roomexample.UsersModel
import com.example.roomexample.databinding.ItemDataBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ItemHolder>() {
    var baseList = emptyList<UsersModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onItemClickListener: ((UsersModel) -> Unit)? = null
    fun setOnItemClickListener(listener: ((UsersModel) -> Unit)? = null) {
        onItemClickListener = listener
    }

    inner class ItemHolder(val b: ItemDataBinding): RecyclerView.ViewHolder(b.root){
        fun bind(itemData: UsersModel){
            b.tvId.text = "${itemData.Id}"
            b.idTVNote.text = itemData.username
            b.idTVDesc.text = itemData.description

            b.idIVDelete.setOnClickListener {
                onItemClickListener?.invoke(itemData)
            }
            itemView.setOnClickListener {
                val context = it.context
                val i = Intent(context, UpdateActivity::class.java)
                i.putExtra("update_key", itemData.username)
                context.startActivity(i)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
       holder.bind(baseList[position])
    }

    override fun getItemCount(): Int  = baseList.size
}