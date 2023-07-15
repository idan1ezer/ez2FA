package com.idan.ez2fa

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent

class VolumeKeyReceiver(private val context: Context) : BroadcastReceiver() {
    private val desiredPattern1 = listOf(
        KeyEvent.KEYCODE_VOLUME_UP,
        KeyEvent.KEYCODE_VOLUME_DOWN,
        KeyEvent.KEYCODE_VOLUME_UP,
        KeyEvent.KEYCODE_VOLUME_UP
    )
    private val desiredPattern2 = listOf(
        KeyEvent.KEYCODE_VOLUME_DOWN,
        KeyEvent.KEYCODE_VOLUME_UP,
        KeyEvent.KEYCODE_VOLUME_UP,
        KeyEvent.KEYCODE_VOLUME_UP
    )
    private val keyPresses = mutableListOf<Int>()

    override fun onReceive(context: Context?, intent: Intent?) {
        // This method is not needed as we'll capture key events in onKeyDown()
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("VolumeKeyReceiver", "pressed on key: $keyCode")
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            keyPresses.add(keyCode)
        }

        if (keyPresses.size == 4) {
            if (keyPresses == desiredPattern1 || keyPresses == desiredPattern2) {
                (context as? MainActivity)?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.fragmentContainer, ResultFragment.newInstance(true))
                    commit()
                }
            } else {
                (context as? MainActivity)?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(R.id.fragmentContainer, ResultFragment.newInstance(false))
                    commit()
                }
            }
            resetKeyPresses()
        }

        return true
    }

    private fun resetKeyPresses() {
        keyPresses.clear()
    }
}




