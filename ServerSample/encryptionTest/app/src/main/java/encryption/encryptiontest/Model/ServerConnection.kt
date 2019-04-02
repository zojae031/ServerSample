package encryption.encryptiontest.Model

import java.io.*
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketTimeoutException

class ServerConnection : Thread() {
    private val ip: String = "210.205.46.5"
    private val port: Int = 5050

    private val socket: Socket = Socket()
    private lateinit var reader: BufferedReader
    private lateinit var writer: BufferedWriter

    @Throws(
        SocketTimeoutException::class
    )
    override fun run() {
        socket.connect(InetSocketAddress(ip,port),2000)
        writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))
        reader = BufferedReader(InputStreamReader(socket.getInputStream()))
    }

    fun sendData(data: String){
        Thread{
            val out = PrintWriter(writer,true)
            out.println(data)
        }.start()
    }
    fun receiveData():String?{
        var result :String?=null
        Thread{
            result = reader.readLine()
        }.start()
        this.join()
        return result
    }
    fun closeClient(){
        reader.close()
        writer.close()
        socket.close()
    }
}