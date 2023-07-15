package com.idan.ez2fa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {

    private var isSuccess: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resultTextView: TextView = view.findViewById(R.id.resultTextView)
        resultTextView.text = if (isSuccess) "Authentication Successful" else "Authentication Failed"
    }

    companion object {
        private const val ARG_SUCCESS = "success"

        fun newInstance(isSuccess: Boolean): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putBoolean(ARG_SUCCESS, isSuccess)
            fragment.arguments = args
            return fragment
        }
    }
}
