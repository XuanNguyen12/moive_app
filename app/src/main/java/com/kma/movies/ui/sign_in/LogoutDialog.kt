package com.kma.movies.ui.sign_in

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.kma.movies.data.sources.remote.CommonSharedPreferences
import com.kma.movies.databinding.DialogLogoutBinding


class LogoutDialog : DialogFragment() {
    private var action : (() -> Unit)?= null
    private var _binding: DialogLogoutBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogLogoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setWidthPercent(90)
        binding.textCancel.setOnClickListener {
            dismissAllowingStateLoss()
        }
        binding.textSignOutAction.setOnClickListener {
            dismissAllowingStateLoss()
            CommonSharedPreferences.getInstance().removeToken()
            action?.invoke()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.setCanceledOnTouchOutside(false)
    }

    companion object {
        fun newInstance(action : (() -> Unit)): LogoutDialog{
            val args = Bundle()
            val fragment = LogoutDialog()
            fragment.arguments = args
            fragment.action = action
            return fragment
        }
    }

}