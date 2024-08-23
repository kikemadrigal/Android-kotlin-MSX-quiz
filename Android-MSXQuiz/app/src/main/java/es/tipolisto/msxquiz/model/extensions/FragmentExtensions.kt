package es.tipolisto.msxquiz.model.extensions

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment


fun Fragment.showToast(context: Context, text : String, duration : Int = Toast.LENGTH_LONG){
    Toast.makeText(context, text, duration).show()
}

fun Fragment.createDialog(activity: Activity, message:String) {
    activity.let {
        val builder= AlertDialog.Builder (it)
        builder.setTitle("Quiz says")
        builder.setMessage(message)
        builder.setPositiveButton("Ok") { dialog, id ->
            dialog.cancel()
        }
        val dialog: Dialog = builder.create()
        dialog.show()
    }
}
fun Fragment.showDialogProblemGetDataInternet(activity: Activity) {
    activity.let {
        val builder= AlertDialog.Builder (it)
        builder.setTitle("Quiz says")
        builder.setMessage("No se obTuvo acceso a internet")
        builder.setPositiveButton("Ok") { dialog, id ->
            dialog.cancel()
            activity.finish()
            System.exit(0)
        }
        val dialog: Dialog = builder.create()
        dialog.show()
    }
}
