package com.vr.myspeaker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vr.myspeaker.adapter.SpeakerAdapter
import com.vr.myspeaker.model.SpeakerModel
import java.io.File
import java.io.FileReader
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    lateinit var lyKosong : LinearLayout
    lateinit var rcSpeaker : RecyclerView
    lateinit var adapter : SpeakerAdapter
    var listSpeaker : ArrayList<SpeakerModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initRc()

    }
    fun initView(){
        lyKosong = findViewById(R.id.lyKosong)
        rcSpeaker = findViewById(R.id.rcSpeaker)
    }
    fun initRc(){
        listSpeaker.clear()
        listSpeaker.addAll(readDataFromFile(this))
        val sortedSpeaker = listSpeaker.sortedBy { it.tipe }
        //ifjadwal kosong
        if (sortedSpeaker.isEmpty()){
            lyKosong.visibility = LinearLayout.VISIBLE
            rcSpeaker.visibility = RecyclerView.GONE
        }else {
            rcSpeaker.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                // set the custom adapter to the RecyclerView
                adapter = SpeakerAdapter(
                    sortedSpeaker,
                    this@MainActivity
                )
                //tambah klik
                { speaker -> clickCard(speaker) }
            }
        }
    }
    private fun readDataFromFile(context: Context): ArrayList<SpeakerModel> {
        // Split data JSON menjadi array string
        val jsonArray = jsonStr.split("},\n  {")

        // Format ulang setiap string JSON untuk memasukkan ke dalam model
        val formattedJsonArray = jsonArray.map { json ->
            if (json.endsWith("},\n  {")) {
                json + "}"
            } else {
                json
            }
        }
        // Gabungkan kembali string JSON yang telah diformat
        val formattedJsonStr = formattedJsonArray.joinToString(",\n")
        // Buat Gson dan definisikan tipe data yang akan dihasilkan
        val gson = Gson()
        val listSpeakerType: Type = object : TypeToken<ArrayList<SpeakerModel>>() {}.type
        // Buat model dari data JSON
        lyKosong.visibility = View.GONE
        return gson.fromJson(formattedJsonStr, listSpeakerType)
    }
    private fun clickCard(speaker: SpeakerModel) {
        //intent ke AddJadwalActivity
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("tipe", speaker.tipe)
        intent.putExtra("series", speaker.series)
        intent.putExtra("deskripsi", speaker.deskripsi)
        intent.putExtra("gambar", speaker.gambar)
        intent.putExtra("diameter", speaker.diameter)
        intent.putExtra("impedansi", speaker.impedansi)
        intent.putExtra("nominal", speaker.nominal)
        intent.putExtra("program", speaker.program)
        intent.putExtra("sensitiv", speaker.sensitiv)
        intent.putExtra("frekuensi", speaker.frekuensi)
        intent.putExtra("voiceDiameter", speaker.voiceDiameter)
        intent.putExtra("voiceMaterial", speaker.voiceMaterial)
        intent.putExtra("voiceWinding", speaker.voiceWinding)
        intent.putExtra("magnet", speaker.magnet)
        intent.putExtra("cone", speaker.cone)
        intent.putExtra("sorround", speaker.sorround)
        intent.putExtra("basket", speaker.basket)
        intent.putExtra("magnetMaterial", speaker.magnetMaterial)
        startActivity(intent)
    }
    val jsonStr = """
        [
          {
            "tipe": "LB 04025",
            "series": "Value Series - 25 mm",
            "deskripsi": "120 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/07/LB-04025-template-foto-utama-1-300x300.jpg",
            "diameter": "100/4 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "60 Watts",
            "program": "120 Watts",
            "sensitiv": "90 dB",
            "frekuensi": "80-17k Hz",
            "voiceDiameter": "25/1 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "7 mm",
            "magnet": "4 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 10.8 OZ"
          },
          {
            "tipe": "LB 08038",
            "series": "Value Series - 38 mm",
            "deskripsi": "200 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/07/LB-08038-template-foto-utama-300x300.jpg",
            "diameter": "200/8 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "100 Watts",
            "program": "200 Watts",
            "sensitiv": "95.63 dB",
            "frekuensi": "70-8k Hz",
            "voiceDiameter": "38/1.5 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "11 mm",
            "magnet": "6 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 29.6 OZ"
          },
          {
            "tipe": "LB 10038",
            "series": "Value Series - 38 mm",
            "deskripsi": "240 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/07/LB-10038-template-foto-utama-300x300.jpg",
            "diameter": "250/10 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "120 Watts",
            "program": "240 Watts",
            "sensitiv": "95.16 dB",
            "frekuensi": "71-7.5k Hz",
            "voiceDiameter": "38/1.5 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "11 mm",
            "magnet": "6 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 29.6 OZ"
          },
          {
            "tipe": "LB 12038",
            "series": "Value Series - 38 mm",
            "deskripsi": "280 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/08/LB-12038-template-foto-utama-NEW-300x300.jpg",
            "diameter": "300/12 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "140 Watts",
            "program": "280 Watts",
            "sensitiv": "94.65 dB",
            "frekuensi": "58-7k Hz",
            "voiceDiameter": "38/1.5 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "12 mm",
            "magnet": "6 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 33.9 OZ"
          },
          {
            "tipe": "LB 12050",
            "series": "Value Series - 50 mm",
            "deskripsi": "320 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/08/LB-12050-template-foto-utama-300x300.jpg",
            "diameter": "300/12 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "160 Watts",
            "program": "320 Watts",
            "sensitiv": "95.27 dB",
            "frekuensi": "58-5k Hz",
            "voiceDiameter": "50/2 mm/inch",
            "voiceMaterial": "Copper",
            "voiceWinding": "10.5 mm",
            "magnet": "6 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 40.03 OZ"
          },
          {
            "tipe": "LB 12060",
            "series": "Value Series - 60 mm",
            "deskripsi": "500 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/08/LB-12060-template-foto-utama-2-300x300.jpg",
            "diameter": "300/12 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "250 Watts",
            "program": "500 Watts",
            "sensitiv": "97.69 dB",
            "frekuensi": "52.9-5k Hz",
            "voiceDiameter": "60.5/2.4 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "13.5 mm",
            "magnet": "8 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 52.5 OZ"
          },
          {
            "tipe": "LB 15050",
            "series": "Value Series - 50 mm",
            "deskripsi": "500 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/08/LB-15050-template-foto-utama-3-300x300.jpg",
            "diameter": "380/15 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "250 Watts",
            "program": "500 Watts",
            "sensitiv": "97.46 dB",
            "frekuensi": "47-3.5k Hz",
            "voiceDiameter": "50.8/2 mm/inch",
            "voiceMaterial": "Copper",
            "voiceWinding": "14 mm",
            "magnet": "5 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 58.2 OZ"
          },
          {
            "tipe": "LB 15060",
            "series": "Value Series - 60 mm",
            "deskripsi": "550 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/08/LB-15060-template-foto-utama-4-300x300.jpg",
            "diameter": "385/15 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "275 Watts",
            "program": "550 Watts",
            "sensitiv": "97.43 dB",
            "frekuensi": "42-4.8k Hz",
            "voiceDiameter": "60.5/2.4 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "13.5 mm",
            "magnet": "8 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 52.5 OZ"
          },
          {
            "tipe": "LB 15075",
            "series": "Value Series - 75 mm",
            "deskripsi": "800 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/11/LB-15075-template-foto-utama-3-300x300.jpg",
            "diameter": "385/15 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "400 Watts",
            "program": "800 Watts",
            "sensitiv": "94 dB",
            "frekuensi": "34.5-3k Hz",
            "voiceDiameter": "75.5/3 mm/inch",
            "voiceMaterial": "Copper",
            "voiceWinding": "17.5 mm",
            "magnet": "8 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 58.13 OZ"
          },
          {
            "tipe": "LB 18075",
            "series": "Value Series - 75 mm",
            "deskripsi": "1200 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2022/06/LB-18075-template-foto-utama-1-300x300.jpg",
            "diameter": "460/18 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "600 Watts",
            "program": "1200 Watts",
            "sensitiv": "95 dB",
            "frekuensi": "30-2.9k Hz",
            "voiceDiameter": "75.5/3 mm/inch",
            "voiceMaterial": "Copper",
            "voiceWinding": "21 mm",
            "magnet": "10 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 77.6 OZ"
          },
          {
            "tipe": "LA 04025",
            "series": "Pro Series - 25 mm",
            "deskripsi": "120 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/07/LA-04025-template-foto-utama-4-300x300.jpg",
            "diameter": "100/4 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "60 Watts",
            "program": "120 Watts",
            "sensitiv": "92 dB",
            "frekuensi": "120-16.5k Hz",
            "voiceDiameter": "25/1 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "7 mm",
            "magnet": "4 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Pressed Steel",
            "magnetMaterial": "Ferrite 10.8 OZ"
          },
          {
            "tipe": "LA 06038",
            "series": "Pro Series - 38 mm",
            "deskripsi": "200 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/08/LA-06038-template-foto-utama-1-300x300.jpg",
            "diameter": "166/6.5 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "100 Watts",
            "program": "200 Watts",
            "sensitiv": "95 dB",
            "frekuensi": "110-10.2k Hz",
            "voiceDiameter": "38/1.5 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "9 mm",
            "magnet": "6 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Cast Aluminium",
            "magnetMaterial": "Ferrite 33.5 Oz"
          },
          {
            "tipe": "LA 08050",
            "series": "Pro Series- 50 mm",
            "deskripsi": "400 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/08/LA-08050-template-foto-utama-1-300x300.jpg",
            "diameter": "200/8 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "200 Watts",
            "program": "400 Watts",
            "sensitiv": "97.1 dB",
            "frekuensi": "74-4.5k Hz",
            "voiceDiameter": "50/2 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "12 mm",
            "magnet": "7 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Cast Aluminium",
            "magnetMaterial": "Ferrite 52.9 Oz"
          },
          {
            "tipe": "LA 10050",
            "series": "Pro Series - 50 mm",
            "deskripsi": "600 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/08/LA-10050-template-foto-utama-1-300x300.jpg",
            "diameter": "250/10 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "300 Watts",
            "program": "600 Watts",
            "sensitiv": "98.2 dB",
            "frekuensi": "60-3k Hz",
            "voiceDiameter": "50/2 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "13 mm",
            "magnet": "7 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Cast Aluminium",
            "magnetMaterial": "Ferrite 54.3 Oz"
          },
          {
            "tipe": "LA 12060",
            "series": "Pro Series - 60 mm",
            "deskripsi": "800 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/08/LA-12060-template-foto-utama-1-300x300.jpg",
            "diameter": "300/12 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "400 Watts",
            "program": "800 Watts",
            "sensitiv": "98.6 dB",
            "frekuensi": "57-2k Hz",
            "voiceDiameter": "60/2.4 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "13 mm",
            "magnet": "7 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Cast Aluminium",
            "magnetMaterial": "Ferrite 56.9 Oz"
          },
          {
            "tipe": "LA 15060",
            "series": "Pro Series - 60 mm",
            "deskripsi": "1000 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2021/08/LA-15060-template-foto-utama-1-300x300.jpg",
            "diameter": "380/15 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "500 Watts",
            "program": "1000 Watts",
            "sensitiv": "98.6 dB",
            "frekuensi": "49-2.5k Hz",
            "voiceDiameter": "60/2.4 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "14 mm",
            "magnet": "7 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Cast Aluminium",
            "magnetMaterial": "Ferrite 58.8 Oz"
          },
          {
            "tipe": "LA 18075",
            "series": "Pro Series - 75 mm",
            "deskripsi": "1500 watts | 8 Ohm",
            "gambar": "https://jicspeaker.com/wp-content/uploads/2022/06/LA-18075-template-foto-utama-1-300x300.jpg",
            "diameter": "460/18 mm/inch",
            "impedansi": "8 ohm",
            "nominal": "750 Watts",
            "program": "1500 Watts",
            "sensitiv": "98 dB",
            "frekuensi": "35-1.8k Hz",
            "voiceDiameter": "75/3 mm/inch",
            "voiceMaterial": "CCAW",
            "voiceWinding": "19 mm",
            "magnet": "7 mm",
            "cone": "Curved T",
            "sorround": "Cloth",
            "basket": "Cast Aluminium",
            "magnetMaterial": "Ferrite 61.5 Oz"
          }
        ]"""
}