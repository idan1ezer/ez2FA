package com.idan.ez2fa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView


class InstructionFragment : Fragment() {

    private lateinit var instructionAnimationView: LottieAnimationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_instruction, container, false)
        instructionAnimationView = view.findViewById(R.id.instructionAnimationView)
        instructionAnimationView.setAnimation(R.raw.dancing_lottie)
        instructionAnimationView.playAnimation()
        return view
    }
}