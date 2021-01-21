package com.example.roomexam
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*


//CoroutineScope'ı MainActivity'e ekliyoruz.
class MainActivity : AppCompatActivity() {

    var dao: CostumerDao? = null //  MainActivity'in her yerinden erişmek için burada değişkeni oluşturup OnCreate'te inşae diyoruz.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //CosturmerDatabase üzerinden dao'ya erişiyoruz ve bir değişkene atıyoruz.
        dao = CostumerDatabase(applicationContext).costumerDao()
        AddCostumer()
        ShowAllCostumer()
    }

    private fun AddCostumer() {
        // costemer modelimizden (tablomuzdan ) bir  veri oluşturuyoruz member değişkenine atıyoruz.
        val member = costumer("hasan", "balaban", "silopi")
        // Coroutine Default(varsayılan) scope üzerinde çalışmasını istedik
        //  Böylelikle Ana ekran (thread) kilitlenmeden işlemler yapılmış olur.
        CoroutineScope(Dispatchers.Default).launch {
            //oluşturduğumuz dao (interface) ile addCostumer fonksiyonuna veri gönderiyoruz.
            //* Room sql kodu (insert into vs ) yazmadan bizim yerimize veriyi veritabanına kaydediyor.
            dao!!.addCostumer(member)
        }
    }

    private fun ShowAllCostumer() {
        // Coroutine Default(varsayılan) scope üzerinde çalışmasını istedik
        //  Böylelikle Ana ekran (thread) kilitlenmeden işlemler yapılmış olur.
        CoroutineScope(Dispatchers.Default).launch {
            // buradan dao ile veritabanımızdan verilerimizi çekip AllMembers değişkenine atıyoruz.
            val AllMembers = dao!!.getAllCostumers()
            // Burada for ile tüm verilerimizi teker teker $i değişkenine atıp Logcat'e yazdırıyoz.
            for (i in AllMembers) {
                Log.i("logloglog", i.costurmerID.toString())
                Log.i("logloglog", i.name)
                Log.i("logloglog", i.lastName)
                Log.i("logloglog", i.birthplace)
                Log.i("logloglog", "")
            }
        }
    }

}