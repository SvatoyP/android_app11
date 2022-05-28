package com.rsk.android.data.repository

import android.content.Context
import com.rsk.android.model.AppConfigurationModel
import com.squareup.moshi.Moshi
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ConfigRepository(
    context: Context,
    private val moshi: Moshi
) {

    private companion object {
        const val CACHE_FOLDER = "rsk_cache/"
        const val APP_CONFIG_FILE_NAME = "app_config.json"
    }

    private fun createFolder(path: String) {
        val folder = File(path)
        if (!folder.exists()) {
            folder.mkdir()
        }
    }
    private val applicationDirectory: String = context.filesDir.absolutePath

    @Throws(IOException::class)
    private fun writeFile(path: String, fileBytes: ByteArray) {
        val file = File(path)
        writeFile(file, fileBytes)
    }

    @Throws(IOException::class)
    private fun writeFile(file: File, fileBytes: ByteArray) {
        if (!file.exists())
            file.createNewFile()

        val fileOS = FileOutputStream(file)
        fileOS.write(fileBytes)
        fileOS.flush()
        fileOS.close()
    }

    suspend fun saveConfiguration(appConfigurationModel: AppConfigurationModel) {
        val folder = createFolder("$applicationDirectory$CACHE_FOLDER")
        val value = moshi.adapter(AppConfigurationModel::class.java).toJson(appConfigurationModel)
        val file = File("$applicationDirectory$CACHE_FOLDER$APP_CONFIG_FILE_NAME")
        writeFile(file, value.toByteArray())
    }

    fun getConfiguration(): AppConfigurationModel? {
        val file = File("$applicationDirectory$CACHE_FOLDER$APP_CONFIG_FILE_NAME")
        if (!file.exists()) {
            return null
        }
        val bufferedReader =
            File("$applicationDirectory$CACHE_FOLDER$APP_CONFIG_FILE_NAME").bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        return moshi.adapter(AppConfigurationModel::class.java).fromJson(inputString)
    }
}