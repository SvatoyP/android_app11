package ru.svatoy.android_app.app.main_window.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.main_window.CreateThemeActivity
import ru.svatoy.android_app.app.main_window.MainActivity
import java.util.*

class ExitThemeDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = DialogInterface.OnClickListener{_, which ->
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_REPONSE to which))
        }
        return MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_App_MaterialAlertDialog)
            .setCancelable(true)
            .setTitle("Закрыть редактор?")
            .setMessage("Неопубликованная тема не будет сохранена")
            .setPositiveButton("Да") { _, _ ->
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("Нет", listener)
            .create()
    }

    companion object{
        @JvmStatic val TAG = ExitThemeDialogFragment::class.java.simpleName
        @JvmStatic val REQUEST_KEY = "$TAG:defaultRequestKey"
        @JvmStatic val KEY_REPONSE = "RESPONSE"
    }

}