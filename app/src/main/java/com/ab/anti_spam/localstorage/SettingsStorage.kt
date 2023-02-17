package com.ab.anti_spam.localstorage

import android.content.Context
import android.net.Uri
import com.ab.anti_spam.models.CallBlacklistModel

import com.ab.anti_spam.models.SettingsModel
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList


const val JSON_FILE_SETTINGS = "settings.json"

val gsonBuilderSettings: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java,ParseSettings())
    .create()
val listTypeSettings: Type = object : TypeToken<ArrayList<SettingsModel>>() {}.type

class SettingsStorage(private val context: Context) : SettingsInterface{

    var settings = mutableListOf<SettingsModel>()

    init {
        if (exists(context, JSON_FILE_SETTINGS)) {
            deserialize()
        }
    }


    override fun update(model: SettingsModel) {
        settings.clear()
        deserialize()
        val foundSettingsModel: SettingsModel? = settings.find { uid -> uid.uid == model.uid }
        //Create if exists
        //Create if it doesnt exist.
        if(foundSettingsModel == null){
            settings.clear()
            deserialize()
            settings.add(model)
            serialize(settings)
        }else {
            foundSettingsModel.database_block = model.database_block
            foundSettingsModel.community_block_num = model.community_block_num
            foundSettingsModel.local_store_block = model.local_store_block
            foundSettingsModel.theme = model.theme
            foundSettingsModel.unknown_block = model.unknown_block
            foundSettingsModel.scan_sms = model.scan_sms
            foundSettingsModel.personal_block = model.personal_block
            serialize(settings)
        }
    }


    override fun initialize(model: SettingsModel){
        if(!exists(context, JSON_FILE_SETTINGS)){
            if(model.uid.isNotEmpty()) {
                settings.clear()
                settings.add(model)
                serialize(settings)
            }
        }
    }

    override fun getAll(): MutableList<SettingsModel> {
        settings.clear()
        if(exists(context, JSON_FILE_SETTINGS)){
            deserialize()
        }
        return settings
    }

    override fun serialize(model: MutableList<SettingsModel>) {
        val jsonString = gsonBuilderSettings.toJson(model, listTypeSettings)
        write(context, JSON_FILE_SETTINGS, jsonString)
    }


    override fun deserialize() {
        if(exists(context, JSON_FILE_SETTINGS)) {
            val jsonString = read(context, JSON_FILE_SETTINGS)
            settings = gsonBuilderSettings.fromJson(jsonString, listTypeSettings)
        }else{
            println("Could not find call settings file.")
        }
    }

}


class ParseSettings : JsonDeserializer<Uri>, JsonSerializer<Uri> {
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