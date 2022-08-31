package ru.svatoy.android_app.app.main_window.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.svatoy.android_app.R
import ru.svatoy.android_app.app.main_window.MainActivity

class GotNewThemeDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listener = DialogInterface.OnClickListener{ _, which ->
            parentFragmentManager.setFragmentResult(REQUEST_KEY, bundleOf(KEY_REPONSE to which))
        }
        return MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_App_MaterialAlertDialog)
            .setCancelable(true)
            .setTitle("Тема опубликована!")
            .setMessage("Вы можете увидеть ее в разделе Мои темы. Все пользователи уже видят ее, она готова к обсуждению")
            .setPositiveButton("На главную") { _, _ ->
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("Мои темы", listener)
            .create()
    }

    companion object{
        @JvmStatic val TAG = GotNewThemeDialog::class.java.simpleName
        @JvmStatic val REQUEST_KEY = "$TAG:defaultRequestKey"
        @JvmStatic val KEY_REPONSE = "RESPONSE"
    }

}