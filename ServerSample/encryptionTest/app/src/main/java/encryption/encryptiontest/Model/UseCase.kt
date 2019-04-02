package encryption.encryptiontest.Model

import RSA
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import encryption.encryptiontest.Contract

class UseCase(presenter: Contract.Presenter) : Contract.Model {
    private val mainPresenter = presenter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun encryption(data: String) {

        var keyPair = RSA.makeKeyPair()

        //TODO String 으로 바꾸었으니
        /*
        byte[] raw = Base64.decode(decKey);  //Base64 디코딩
        skeySpec = new SecretKeySpec(raw,"AES");
        cipher = Cipher.getInstance(key);
        형태로 서버에 받아서 KeyPa
         */


        val AESencodedData = AES.encode(data)
        Log.e("암호 AES : ", AESencodedData)


        val RSAencodedData = RSA.encode(AESencodedData, keyPair.public)
        Log.e("암호 RSA : ", RSAencodedData)

        mainPresenter.sendData(RSAencodedData)
    }

    override fun decryption(data: String) {

    }
}