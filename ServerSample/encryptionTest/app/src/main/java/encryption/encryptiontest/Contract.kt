package encryption.encryptiontest

interface Contract{
    interface View{
        fun drawResult(data:String)
        fun updateView()
        fun socketTimeOut()
    }
    interface Presenter{

        fun sendData(data:String)
        fun receiveData()
        fun settingApp()
        fun makeEncryption(data:String)
    }
    interface Model{
        fun encryption(data:String)
        fun decryption(data:String)
    }
}