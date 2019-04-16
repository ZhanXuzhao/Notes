package com.zxz.notes

/**
 * @author : Zhan Xuzhao
 * e-mail : 649912323@qq.com
 * time   : 2019/4/16 16:18
 * desc   :
 * version: 1.0
 */
data class Note(var id: Long, var content: String, var time: Long) {
    override fun equals(other: Any?): Boolean {
        return other != null && other is Note && id == other.id
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + time.hashCode()
        return result
    }

    companion object {
        fun createNote(content: String): Note {
            val time = System.currentTimeMillis()
            return Note(time, content, time)
        }
    }
}