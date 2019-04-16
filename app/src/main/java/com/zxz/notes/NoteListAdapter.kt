package com.zxz.notes

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_note.view.*

/**
 * @author : Zhan Xuzhao
 * e-mail : 649912323@qq.com
 * time   : 2019/4/16 16:20
 * desc   :
 * version: 1.0
 */
class NoteListAdapter(recyclerView: RecyclerView) : BaseAdapter<Note>(recyclerView, R.layout.list_item_note){
    private var mHost: Host? = null

    override fun onBindDataViewHolder(
        holder: BaseViewHolder,
        data: Note,
        position: Int
    ) {
        val itemView = holder.itemView
        itemView.tv_note.text = data.content
        itemView.setOnClickListener { mHost?.onItemClick(position, data) }
        itemView.iv_delete.setOnClickListener { mHost?.onDeleteClick(position, data) }
        itemView.iv_link.setOnClickListener { mHost?.onLinkBtnClick(position, data) }
    }

    fun setHost(host: Host) {
        mHost = host
    }

    interface Host {
        fun onDeleteClick(position: Int, data: Note)
        fun onItemClick(position: Int, data: Note)
        fun onLinkBtnClick(position: Int, data: Note)
    }
}