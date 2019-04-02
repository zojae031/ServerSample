package encryption.encryptiontest.Model

import android.os.Build
import android.support.annotation.RequiresApi
import java.security.GeneralSecurityException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object AES {
    private const val SECRET_KEY = "CapstoneDesign@@"
    private const val CIPHER_TRANSFORMATION = "AES/CBC/PKCS5PADDING"
    private val ivBytes = byteArrayOf(
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00,
        0x00
    ) //암호화 복호화 동일해야함.


    //암호화
    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidAlgorithmParameterException::class,
        InvalidKeyException::class,
        BadPaddingException::class,
        IllegalBlockSizeException::class
    )

    fun encode(str: String): String {
        val cipherText = try {
            with(Cipher.getInstance(CIPHER_TRANSFORMATION)) {
                init(
                    Cipher.ENCRYPT_MODE,
                    SecretKeySpec(SECRET_KEY.toByteArray(), "AES"),
                    IvParameterSpec(ivBytes)
                )
                return@with doFinal(str.toByteArray())
            }
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
            ByteArray(0, { i -> 0 })
        }

        return Base64.getEncoder().encodeToString(cipherText)
    }


    //복호화
    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(
        NoSuchPaddingException::class,
        NoSuchAlgorithmException::class,
        InvalidAlgorithmParameterException::class,
        InvalidKeyException::class,
        BadPaddingException::class,
        IllegalBlockSizeException::class
    )
    fun decode(encrypted: String): String {
        val plainTextBytes = try {
            with(Cipher.getInstance(CIPHER_TRANSFORMATION)) {
                init(
                    Cipher.DECRYPT_MODE,
                    SecretKeySpec(SECRET_KEY.toByteArray(), "AES"),
                    IvParameterSpec(ivBytes)
                )
                val cipherText = Base64.getMimeDecoder().decode(encrypted)
                return@with doFinal(cipherText)
            }
        } catch (e: GeneralSecurityException) {
            // 특정 국가 혹은 저사양 기기에서는 알고리즘 지원하지 않을 수 있음. 특히 중국/인도 대상 기기
            e.printStackTrace()
            ByteArray(0, { i -> 0 })
        }

        return String(plainTextBytes)
    }


}
