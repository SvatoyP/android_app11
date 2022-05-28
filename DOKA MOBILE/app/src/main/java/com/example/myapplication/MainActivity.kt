package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.TintableBackgroundView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)





    }
    fun uripudge(view: android.view.View) {
        MediaPlayer.create(this,R.raw.padge).start()
        val Padge = Intent(this, Pudge::class.java)
        startActivity(Padge)
    }

    fun uriinvoker(view: android.view.View) {
        MediaPlayer.create(this,R.raw.sun_strike).start()
        val Invuk = Intent(this, Invoker::class.java)
        startActivity(Invuk)
    }
    fun urimeepo(view: android.view.View) {
        MediaPlayer.create(this,R.raw.poof).start()
        val Mepo = Intent(this, Meepo::class.java)
        startActivity(Mepo)
    }
    fun uritinker(view: android.view.View) {
        MediaPlayer.create(this,R.raw.tinker).start()
        val Tink = Intent(this, Tinker::class.java)
        startActivity(Tink)
    }
    fun uritechies(view: android.view.View) {
        MediaPlayer.create(this,R.raw.techies).start()
        val Tech = Intent(this, Techies::class.java)
        startActivity(Tech)
    }
    fun uristray(view: android.view.View) {
        MediaPlayer.create(this,R.raw.stray).start()
        val Stra = Intent(this, Stray::class.java)
        startActivity(Stra)
    }
    fun uriwraith(view: android.view.View) {
        MediaPlayer.create(this,R.raw.papich).start()
        val papa = Intent(this, Papich::class.java)
        startActivity(papa)
    }
    fun urisf(view: android.view.View) {
        MediaPlayer.create(this,R.raw.sf).start()
        val sf = Intent(this, sf::class.java)
        startActivity(sf)
    }
}