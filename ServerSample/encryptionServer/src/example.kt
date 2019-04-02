import encryption.encryptiontest.Model.AES
import java.security.KeyPairGenerator
//암호화 시험을 위한 TEST_MAIN
object exampleMain{
    @JvmStatic
    fun main(args: Array<String>){
        exam()
    }
}

fun exam(){
    val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    keyPairGenerator.initialize(1024)
    val keyPair = RSA.makeKeyPair()
    val data = "이거슨 원본데이터"

    println("원본데이터 : $data")

    val AESencode = AES.encode(data) //AES 암호화
    println("AES 암호화 : $AESencode")

    val RSAencode = RSA.encode(AESencode,keyPair.public) //RSA 암호화
    println("RSA 암호화 : $RSAencode")

    val RSAdecode = RSA.decode(RSAencode,keyPair.private)
    println("RSA 복호화 : $RSAdecode")

    val AESdecode = AES.decode(RSAdecode)
    println("AES 복호화 : $AESdecode")
}