package com.ab.anti_spam.localstorage

import android.content.Context
import android.net.Uri
import com.ab.anti_spam.models.SMSBlacklistModel
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

const val JSON_FILE_SMS = "personalsmsblacklist.json"

val gsonBuilderSMS: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java,Parse_SMS())
    .create()
val listTypeSMS: Type = object : TypeToken<ArrayList<SMSBlacklistModel>>() {}.type

class SMSBlacklistStorage(private val context: Context) : SMSBlacklistInterface {

    var blacklist = mutableListOf<SMSBlacklistModel>()

    init {
        if (exists(context, JSON_FILE_SMS)) {
            deserialize()
        }
    }

    override fun getAll(): MutableList<SMSBlacklistModel> {
        blacklist.clear()
        if(exists(context, JSON_FILE_SMS)){
            deserialize()
        }else{
            println("Could not find call blacklist file.")
        }

        return blacklist
    }

    override fun serialize(model: MutableList<SMSBlacklistModel>) {
        val jsonString = gsonBuilderSMS.toJson(model, listTypeSMS)
        write(context, JSON_FILE_SMS, jsonString)
    }

    override fun deserialize() {
        if(exists(context, JSON_FILE_SMS)) {
            val jsonString = read(context, JSON_FILE_SMS)
            blacklist = gsonBuilderSMS.fromJson(jsonString, listTypeSMS)
        }else{
            println("Could not find call blacklist file.")
        }
    }

    override fun update(model: SMSBlacklistModel) {
        deserialize()
        val foundUserBlacklist: SMSBlacklistModel? = blacklist.find { id -> id.id == model.id }
        if (foundUserBlacklist != null) {
            foundUserBlacklist.by_keyword = model.by_keyword
            foundUserBlacklist.by_regex = model.by_regex
            serialize(blacklist)
        }
    }

    override fun add(model: SMSBlacklistModel){
        blacklist.clear()
        deserialize()
        blacklist.add(model)
        serialize(blacklist)
    }

    override fun delete(model: SMSBlacklistModel){
        blacklist.clear()
        deserialize()
        val foundUserBlacklist: SMSBlacklistModel? = blacklist.find { id -> id.id == model.id }
        blacklist.remove(foundUserBlacklist)
        serialize(blacklist)
    }
}

class Parse_SMS : JsonDeserializer<Uri>, JsonSerializer<Uri> {
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