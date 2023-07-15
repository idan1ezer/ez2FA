//package com.idan.ez2fa
//
//import android.content.Context
//import android.database.ContentObserver
//import android.media.AudioManager
//import android.os.Handler
//import android.view.KeyEvent
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//
//class SettingsContentObserver(private val context: Context, handler: Handler) : ContentObserver(handler) {
//    private val audioManager: AudioManager =
//        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
//
//    private var previousVolume: Int =
//        audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
//
//    private val desiredPattern = listOf(
//        KeyEvent.KEYCODE_VOLUME_UP,
//        KeyEvent.KEYCODE_VOLUME_DOWN,
//        KeyEvent.KEYCODE_VOLUME_UP,
//        KeyEvent.KEYCODE_VOLUME_UP
//    )
//
//    private val keyPresses = mutableListOf<Int>()
//
//    override fun onChange(selfChange: Boolean) {
//        super.onChange(selfChange)
//
//        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
//        val keyCode = if (currentVolume > previousVolume) KeyEvent.KEYCODE_VOLUME_UP else KeyEvent.KEYCODE_VOLUME_DOWN
//        previousVolume = currentVolume
//
//        keyPresses.add(keyCode)
//
//        if (keyPresses.size == 4) {
//            if (keyPresses == desiredPattern) {
//                // Volume keys pressed in the right order
//                moveToResultFragment(true)
//            } else {
//                // Volume keys pressed in the wrong order
//                moveToResultFragment(false)
//            }
//            keyPresses.clear()
//        }
//    }
//
//    private fun moveToResultFragment(isSuccess: Boolean) {
//        val resultFragment = ResultFragment.newInstance(isSuccess)
//        (context as? AppCompatActivity)?.supportFragmentManager?.beginTransaction()?.apply {
//            replace(R.id.fragmentContainer, resultFragment)
//            commit()
//        }
//    }
//}
