package io.github.nanchenyang.agreementdialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.KeyEvent
import android.view.View
import androidx.core.content.ContextCompat
import io.github.nanchenyang.agreementdialog.databinding.DialogShowAgreementBinding

class ShowAgreementDialog : BaseDialogFragment<DialogShowAgreementBinding>() {

    companion object {
        val TAG = ShowAgreementDialog::class.java.name
    }

    var confirmClick: (() -> Unit)? = null
    var cancelClick: (() -> Unit)? = null
    var jumpClick: (() -> Unit)? = null

    override val layoutId: Int
        get() = R.layout.dialog_show_agreement

    private val clickSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            jumpClick?.invoke()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = ContextCompat.getColor(
                requireContext(),
                R.color.common_sheet_btn_primary
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val onCreateDialog = super.onCreateDialog(savedInstanceState)
        //设置外部点击不消失
        onCreateDialog.setCanceledOnTouchOutside(false)
        //设置返回键不消失
        onCreateDialog.setCancelable(false)
        onCreateDialog.setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_BACK
        }
        onCreateDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return onCreateDialog
    }

    override fun initView() {
        super.initView()
        val content = SpannableStringBuilder()
        content.append(getString(R.string.agreement_content_pre))
        val preSize = content.length
        content.append(getString(R.string.agreement_content_protocol))


        content.setSpan(clickSpan, preSize, content.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        content.append(getString(R.string.agreement_content_suffix))

        dataBinding.tvContent.text = content
        dataBinding.tvContent.movementMethod = LinkMovementMethod.getInstance()

        dataBinding.btnCancel.setOnClickListener {
            cancelClick?.invoke()
            dismiss()
        }
        dataBinding.btnConfirm.setOnClickListener {
            confirmClick?.invoke()
            dismiss()
        }
    }

}