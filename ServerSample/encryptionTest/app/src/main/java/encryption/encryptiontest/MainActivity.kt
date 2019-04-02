package encryption.encryptiontest

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), Contract.View {


    private val presenter = MainPresenter(this)
    private lateinit var sendButton: Button
    private lateinit var resultText: TextView
    private lateinit var sendText: EditText

    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
        presenter.settingApp()
        sendButton.setOnClickListener {
            presenter.makeEncryption(sendText.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun updateView() {
        sendText.setText("")
    }

    override fun drawResult(data: String) {
        resultText.setText(data)
    }

    fun setView() {
        sendButton = findViewById(R.id.button)
        sendText = findViewById(R.id.send)
        resultText = findViewById(R.id.result)
    }
    override fun socketTimeOut() {
        Toast.makeText(applicationContext,"소켓 연결 실패",Toast.LENGTH_SHORT).show()
    }

}
