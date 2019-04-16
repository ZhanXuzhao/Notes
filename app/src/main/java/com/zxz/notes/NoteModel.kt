package com.zxz.notes

import com.blankj.utilcode.util.SPUtils
import com.zxz.notes.util.JsonUtil
import com.google.gson.reflect.TypeToken

/**
 * @author : Zhan Xuzhao
 * e-mail : 649912323@qq.com
 * time   : 2019/4/16 16:49
 * desc   :
 * version: 1.0
 */
object NoteModel {

    private const val SP_KEY_NOTES = "SP_KEY_NOTES"
    private val sp = SPUtils.getInstance(javaClass.simpleName)

    fun addNote(note: Note) {
        val noteList = getNoteList()
        noteList.add(note)
        saveList(noteList)
    }

    fun deleteNode(note: Note) {
        val noteList = getNoteList()
        noteList.remove(note)
        saveList(noteList)
    }

    fun getNoteList(): MutableList<Note> {
        val json = sp.getString(SP_KEY_NOTES)
        return JsonUtil.fromJson<MutableList<Note>>(json, object : TypeToken<MutableList<Note>>() {}.type)
            ?: ArrayList<Note>()
    }

    fun saveList(list: List<Note>?) {
        return sp.put(SP_KEY_NOTES, JsonUtil.toJson(list))
    }

}