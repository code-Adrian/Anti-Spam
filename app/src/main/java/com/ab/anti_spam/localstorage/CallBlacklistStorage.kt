package com.ab.anti_spam.localstorage

import android.content.Context
import android.net.Uri
import com.ab.anti_spam.models.CallBlacklistModel
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "personalcallblacklist.json"

val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java,Parse())
    .create()
val listType: Type = object : TypeToken<ArrayList<CallBlacklistModel>>() {}.type

class CallBlacklistStorage(private val context: Context) : CallBlacklistInterface {

    var blacklist = mutableListOf<CallBlacklistModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun getAll(): MutableList<CallBlacklistModel> {
        blacklist.clear()
            if(exists(context, JSON_FILE)){
                deserialize()
            }else{
                println("Could not find call blacklist file.")
            }

        return blacklist
    }

    override fun serialize(model: MutableList<CallBlacklistModel>) {
        val jsonString = gsonBuilder.toJson(model, listType)
        write(context, JSON_FILE, jsonString)
    }

    override fun deserialize() {
            if(exists(context, JSON_FILE)) {
                val jsonString = read(context, JSON_FILE)
                blacklist = gsonBuilder.fromJson(jsonString, listType)
            }else{
                println("Could not find call blacklist file.")
            }
    }

    override fun update(model: CallBlacklistModel) {
        deserialize()
        val foundUserBlacklist: CallBlacklistModel? = blacklist.find { id -> id.id == model.id }
        if (foundUserBlacklist != null) {
            foundUserBlacklist.by_country = model.by_country
            foundUserBlacklist.by_number = model.by_number
            foundUserBlacklist.by_regex = model.by_regex
            serialize(blacklist)
        }
    }

    override fun add(model: CallBlacklistModel){
        blacklist.clear()
        deserialize()
        blacklist.add(model)
        serialize(blacklist)
    }

    override fun delete(model: CallBlacklistModel){
        blacklist.clear()
        deserialize()
        val foundUserBlacklist: CallBlacklistModel? = blacklist.find { id -> id.id == model.id }
       blacklist.remove(foundUserBlacklist)
        serialize(blacklist)
    }
}

class Parse : JsonDeserializer<Uri>,JsonSerializer<Uri> {
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