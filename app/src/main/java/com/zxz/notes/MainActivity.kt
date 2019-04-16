package com.zxz.notes

import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri
import com.blankj.utilcode.util.ToastUtils
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy { NoteListAdapter(rv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mAdapter
        mAdapter.setHost(mAdapterHost)
        refreshData()

        fb.setOnClickListener { addNote() }
    }

    private val mAdapterHost = object : NoteListAdapter.Host {
        override fun onDeleteClick(position: Int, data: Note) {
            NoteModel.deleteNode(data)
            mAdapter.removeData(data, mAdapter.getDataPosition(position))

            Snackbar.make(rv, R.string.note_deleted, Snackbar.LENGTH_SHORT)
                .setAction(R.string.undo) {
                    mAdapter.addData(mAdapter.getDataPosition(position), data)
                    NoteModel.saveList(mAdapter.getData())
                }
                .show()
        }

        override fun onItemClick(position: Int, data: Note) {
        }

        override fun onLinkBtnClick(position: Int, data: Note) {
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.content)))
            } catch (e: Exception) {
                ToastUtils.showShort(e.message)
            }
        }
    }

    private fun refreshData() {
        mAdapter.setData(NoteModel.getNoteList())
    }

    private fun addNote() {
        MaterialDialog(this)
            .show {
                message(R.string.input)
                input(
                    hint = "Input your note",
                    inputType = InputType.TYPE_CLASS_TEXT
                ) { _, text ->
                    val note = Note.createNote(text.toString())
                    mAdapter.addData(note)
                    NoteModel.saveList(mAdapter.getData())
                }
                positiveButton { R.string.confirm }
                negativeButton { R.string.cancel }
            }
    }
}
