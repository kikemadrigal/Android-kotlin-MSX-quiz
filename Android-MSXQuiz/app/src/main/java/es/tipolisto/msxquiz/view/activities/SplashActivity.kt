package es.tipolisto.msxquiz.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.tipolisto.msxquiz.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent=Intent(this, ContainerActivity::class.java)
        startActivity(intent)
        finish()
    }
}