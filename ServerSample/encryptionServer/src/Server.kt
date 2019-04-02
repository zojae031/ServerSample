import java.io.IOException
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException

class Server {
    private val port: Int = 5050
    private val socket: ServerSocket = ServerSocket(port)
    private val address: String = InetAddress.getLocalHost().hostAddress
    private var clientList: MutableList<Client> = arrayListOf()


    fun openServer() {
        try {
            println("ServerIP : $address")
            while (true) {
                val client: Socket = socket.accept()
                println("클라이언트 접속 ${client.localAddress}")
                val activeClient = Client(client)
                activeClient.connect()
                clientList.add(activeClient)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
        catch (e:SocketException){
            println("클라이언트 접속 종료")
        }
        if (!clientList.isEmpty()){
            for(clients in clientList){
                clients.close()
            }
            clientList.clear()
        }
    }


}