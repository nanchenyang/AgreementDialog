package io.github.nanchenyang.agreement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import io.github.nanchenyang.agreementdialog.ShowAgreementDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = findViewById<TextView>(R.id.hello)
        view.setOnClickListener {
            ShowAgreementDialog().show(supportFragmentManager,"ShowAgreementDialog")
        }
    }
}