package encryption.encryptiontest

import android.os.Build
import android.support.annotation.RequiresApi
import com.google.gson.JsonObject
import encryption.encryptiontest.Model.ServerConnection
import encryption.encryptiontest.Model.UseCase

class MainPresenter(view: Contract.View) : Contract.Presenter {
    private val view: Contract.View = view
    private val model = UseCase(this)
    private val connector: ServerConnection = ServerConnection()


    override fun sendData(data: String) {
        val jsonObject = JsonObject()
        jsonObject.addProperty("data",data)
        connector.sendData(jsonObject.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun makeEncryption(data: String) {
        view.updateView()

        model.encryption(data)//model 로 전송하여 암호화
    }

    override fun receiveData() {
        val decodeData = connector.receiveData()
    }

    override fun settingApp() {

        connector.start()


    }
}