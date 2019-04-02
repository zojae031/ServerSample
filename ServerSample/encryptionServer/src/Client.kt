import com.google.gson.JsonObject
import com.google.gson.JsonParser
import encryption.encryptiontest.Model.AES
import java.io.*
import java.net.Socket
import java.net.SocketException

class Client(client: Socket) {
    private var client: Socket = client
    private var reader: BufferedReader
    private var writer: PrintWriter

    init {
        reader = BufferedReader(InputStreamReader(client.getInputStream(), "UTF-8"))
        writer = PrintWriter(BufferedWriter(OutputStreamWriter(client.getOutputStream(), "UTF-8")), true)
    }

    @Throws(
        SocketException::class
    )
    fun connect() {
        val parser = JsonParser()
        val privateKey = RSA.makeKeyPair()
        val data = parser.parse(reader.readLine()) as JsonObject
        println("받은 데이터 : $data")

        val RSADecode = RSA.decode(data.get("data").toString(),privateKey.private)
        println("RSA 디코드 : $RSADecode")
        val result = AES.decode(RSADecode)
        println("AES 디코드 : $result")

    }


    fun close() {
        writer.close()
        reader.close()
        client.close()
    }

}