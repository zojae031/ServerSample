
import android.os.Build
import android.support.annotation.RequiresApi
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.util.*
import javax.crypto.Cipher

object RSA {
    // Generate key pair for 1024-bit RSA encryption and decryption

    @RequiresApi(Build.VERSION_CODES.O)
    fun encode(str: String, publicKey: PublicKey): String {
        // Encode the original data with RSA private key


        val encodedBytes =
            try {
                with(Cipher.getInstance("RSA")) {
                    init(Cipher.ENCRYPT_MODE, publicKey)
                    return@with doFinal(str.toByteArray())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                ByteArray(0)
            }

        return Base64.getEncoder().encodeToString(encodedBytes)

    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun decode(encodedData: String, privateKey: PrivateKey): String {


        // Decode the encoded data with RSA public key
        val decodedBytes =
            try {
                with(Cipher.getInstance("RSA")) {
                    init(Cipher.DECRYPT_MODE, privateKey)
                    return@with doFinal(Base64.getMimeDecoder().decode(encodedData))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                ByteArray(0)
            }
        return String(decodedBytes)
    }

    fun makeKeyPair(): KeyPair =
        with(KeyPairGenerator.getInstance("RSA")){
            initialize(1024)
            return@with genKeyPair()
        }
}


