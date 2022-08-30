package es.tipolisto.msxquiz.model.extensions

import android.R
import android.app.Activity
import android.app.ActivityOptions
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


fun Activity.showToast(text : String, duration : Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, text, duration).show()
}

fun Activity.changeActivity(activity : Activity){
    val intent = Intent(this, activity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
}

fun Activity.changeActivity(activity : Activity, totalPoints : Int){
    val intent = Intent(this, activity::class.java)
    intent.putExtra("points",totalPoints)
    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
}

fun Activity.linkTo(uri : Int){
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(uri))))
}

fun Activity.showDialogProblemGetDataInternet(activity: Activity) {
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
fun Activity.createDialog(activity: Activity,message:String) {
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