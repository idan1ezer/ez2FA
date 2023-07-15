package com.idan.ez2fa

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.ContentObserver
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    private lateinit var settingsContentObserver: ContentObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        settingsContentObserver = object : ContentObserver(Handler()) {
            private val audioManager: AudioManager =
                getSystemService(Context.AUDIO_SERVICE) as AudioManager

            private var previousVolume: Int =
                audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

            private val desiredPattern = listOf(
                KeyEvent.KEYCODE_VOLUME_UP,
                KeyEvent.KEYCODE_VOLUME_DOWN,
                KeyEvent.KEYCODE_VOLUME_UP,
                KeyEvent.KEYCODE_VOLUME_UP
            )

            private val keyPresses = mutableListOf<Int>()

            override fun onChange(selfChange: Boolean) {
                super.onChange(selfChange)

                val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
                val delta = previousVolume - currentVolume
                previousVolume = currentVolume

                val volumeChangeThreshold = 1 // Adjust this threshold as needed

                if (delta >= volumeChangeThreshold) {
                    keyPresses.add(KeyEvent.KEYCODE_VOLUME_DOWN)
                } else if (delta <= -volumeChangeThreshold) {
                    keyPresses.add(KeyEvent.KEYCODE_VOLUME_UP)
                }

                Log.d("VolumeKeyIdan","desiredPattern: $desiredPattern")
                Log.d("VolumeKeyIdan","keyPresses: $keyPresses")

                if (keyPresses.size == 4) {
                    Log.d("VolumeKeyIdan","Got 4 keys")
                    if (keyPresses == desiredPattern) {
                        // Volume keys pressed in the right order
                        keyPresses.clear()
                        moveToResultFragment(true)
                    } else {
                        // Volume keys pressed in the wrong order
                        keyPresses.clear()
                        moveToResultFragment(false)
                    }
                }
            }

            private fun moveToResultFragment(isSuccess: Boolean) {
                Log.d("VolumeKeyIdan","result: $$isSuccess")
                val resultFragment = ResultFragment.newInstance(isSuccess)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, resultFragment)
                    .commit()
            }
        }

        applicationContext.contentResolver.registerContentObserver(
            android.provider.Settings.System.CONTENT_URI,
            true,
            settingsContentObserver
        )

        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val username: String = usernameEditText.text.toString()
            val password: String = passwordEditText.text.toString()

            if (validateCredentials(username, password)) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, InstructionFragment())
                    .commit()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        applicationContext.contentResolver.unregisterContentObserver(settingsContentObserver)
        super.onDestroy()
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        return username == "admin" && password == "password"
    }
}
