package com.ab.anti_spam.localstorage

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*
import android.content.Context
import android.net.Uri
import com.ab.anti_spam.models.LocalBlockModel

const val JSON_FILE_LOCAL_DB = "localblocklist.json"

val gsonBuilderLocalBlock: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java,ParseLocalBlockStore())
    .create()
val listTypeLocalBlock: Type = object : TypeToken<ArrayList<LocalBlockModel>>() {}.type

class LocalBlockStorage(private val context: Context) : LocalBlockInterface {
    var localBlacklist = mutableListOf<LocalBlockModel>()

    override fun serialize(model: MutableList<LocalBlockModel>) {
        val jsonString = gsonBuilderLocalBlock.toJson(model, listTypeLocalBlock)
        write(context, JSON_FILE_LOCAL_DB, jsonString)
    }

    override fun deserialize() {
        if(exists(context, JSON_FILE_LOCAL_DB)) {
            val jsonString = read(context, JSON_FILE_LOCAL_DB)
            localBlacklist = gsonBuilderLocalBlock.fromJson(jsonString, listTypeLocalBlock)
        }else{
            println("Could not find call local blacklist file.")
        }
    }

    override fun getAll(): MutableList<LocalBlockModel> {
        localBlacklist.clear()
        if(exists(context, JSON_FILE_LOCAL_DB)){
            deserialize()
        }else{
            println("Could not find call local blacklist file.")
        }

        return localBlacklist
    }

    override fun checkIfExists(): Boolean {
        if(exists(context, JSON_FILE_LOCAL_DB)) {
            return true
        }else{
            return false
        }
    }

}

class ParseLocalBlockStore : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement? {
        return JsonPrimitive(src.toString())
    }
}