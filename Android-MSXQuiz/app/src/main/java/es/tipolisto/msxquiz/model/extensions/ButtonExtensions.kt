package es.tipolisto.msxquiz.model.extensions

import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat

import es.tipolisto.msxquiz.R

fun Button.correct(){
    this.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.green_color,null))
    this.setTextColor(ResourcesCompat.getColor(resources,R.color.white,null))
}

fun Button.incorrect(arrayAnswerButtons : Array<Button>, correctAnswer : Int){
    //Incorrect to RED
    this.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.red_color,null))
    this.setTextColor(ResourcesCompat.getColor(resources,R.color.white,null))

    //Correct to GREEN
    arrayAnswerButtons[correctAnswer-1].setBackgroundColor(ResourcesCompat.getColor(resources,R.color.green_color,null))
    arrayAnswerButtons[correctAnswer-1].setTextColor(ResourcesCompat.getColor(resources,R.color.white,null))
}

fun Button.clear(){
    //this.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.background_main,null))
    if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
        this.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.black,null))
    }else{
        this.setBackgroundColor(ResourcesCompat.getColor(resources,R.color.background_main,null))
    }
    this.setTextColor(ResourcesCompat.getColor(resources,R.color.white,null))
}