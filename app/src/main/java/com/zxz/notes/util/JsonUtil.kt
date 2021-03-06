package com.zxz.notes.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

/**
 * <pre>
 *     @author : Zhan Xuzhao
 *     e-mail : xuzhao.z@sumian.com
 *     time   : 2018/6/14 10:31
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class JsonUtil {

    companion object {

        private val gson = Gson()

        fun toJson(any: Any?): String {
            return gson.toJson(any)
        }

        fun <T> fromJson(json: String?, clazz: Class<T>): T? {
            return try {
                gson.fromJson(json, clazz)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                null
            }
        }

        /**
         * @param type Such as new TypeToken<Map></Map><String></String>, Integer>>() {}.getType(); new TypeToken<List></List><String>>(){}.getType();
        </String> */
        fun <T> fromJson(json: String?, type: Type): T? {
            return try {
                gson.fromJson(json, type)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        fun getJsonObject(json: String?): JsonObject? {
            return if (json == null) {
                null
            } else {
                JsonParser().parse(json).asJsonObject
            }
        }
    }
}