package com.vr.myspeaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    private lateinit var tvTipe: TextView
    private lateinit var tvSeries: TextView
    private lateinit var tvDeskripsi: TextView
    private lateinit var tvGambar: ImageView
    private lateinit var tvDiameter: TextView
    private lateinit var tvImpedansi: TextView
    private lateinit var tvNominal: TextView
    private lateinit var tvProgram: TextView
    private lateinit var tvSensitiv: TextView
    private lateinit var tvFrekuensi: TextView
    private lateinit var tvVoiceDiameter: TextView
    private lateinit var tvVoiceMaterial: TextView
    private lateinit var tvVoiceWinding: TextView
    private lateinit var tvMagnet: TextView
    private lateinit var tvCone: TextView
    private lateinit var tvSorround: TextView
    private lateinit var tvBasket: TextView
    private lateinit var tvMagnetMaterial: TextView
    var tipe: String = ""
    var series: String = ""
    var deskripsi: String = ""
    var gambar: String = ""
    var diameter: String = ""
    var impedansi: String = ""
    var nominal: String = ""
    var program: String = ""
    var sensitiv: String = ""
    var frekuensi: String = ""
    var voiceDiameter: String = ""
    var voiceMaterial: String = ""
    var voiceWinding: String = ""
    var magnet: String = ""
    var cone: String = ""
    var sorround: String = ""
    var basket: String = ""
    var magnetMaterial: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
        initIntent()
        setView()
    }
    private fun initView(){
        tvTipe = findViewById(R.id.tvTipe)
        tvSeries = findViewById(R.id.tvSeries)
        tvDeskripsi = findViewById(R.id.tvDeskripsi)
        tvGambar = findViewById(R.id.imgCover)
        tvDiameter = findViewById(R.id.tvDiameter)
        tvImpedansi = findViewById(R.id.tvImpedansi)
        tvNominal = findViewById(R.id.tvNominal)
        tvProgram = findViewById(R.id.tvProgram)
        tvSensitiv = findViewById(R.id.tvSensitiv)
        tvFrekuensi = findViewById(R.id.tvFrekuensi)
        tvVoiceDiameter = findViewById(R.id.tvVoiceDiameter)
        tvVoiceMaterial = findViewById(R.id.tvVoiceMaterial)
        tvVoiceWinding = findViewById(R.id.tvVoiceWinding)
        tvMagnet = findViewById(R.id.tvMagnet)
        tvCone = findViewById(R.id.tvCone)
        tvSorround = findViewById(R.id.tvSorround)
        tvBasket = findViewById(R.id.tvBasket)
        tvMagnetMaterial = findViewById(R.id.tvMagnetMaterial)

    }
    private fun initIntent(){
        tipe = intent.getStringExtra("tipe").toString()
        //init semua intent gunakan sama persis dengan var nya
        series = intent.getStringExtra("series").toString()
        deskripsi = intent.getStringExtra("deskripsi").toString()
        gambar = intent.getStringExtra("gambar").toString()
        diameter = intent.getStringExtra("diameter").toString()
        impedansi = intent.getStringExtra("impedansi").toString()
        nominal = intent.getStringExtra("nominal").toString()
        program = intent.getStringExtra("program").toString()
        sensitiv = intent.getStringExtra("sensitiv").toString()
        frekuensi = intent.getStringExtra("frekuensi").toString()
        voiceDiameter = intent.getStringExtra("voiceDiameter").toString()
        voiceMaterial = intent.getStringExtra("voiceMaterial").toString()
        voiceWinding = intent.getStringExtra("voiceWinding").toString()
        magnet = intent.getStringExtra("magnet").toString()
        cone = intent.getStringExtra("cone").toString()
        sorround = intent.getStringExtra("sorround").toString()
        basket = intent.getStringExtra("basket").toString()
        magnetMaterial = intent.getStringExtra("magnetMaterial").toString()

    }
    private fun setView(){
        tvTipe.text = tipe
        tvSeries.text = series
        tvDeskripsi.text = deskripsi
        Glide.with(this)
            .load(gambar)
            .into(tvGambar)
        tvDiameter.text = diameter
        tvImpedansi.text = impedansi
        tvNominal.text = nominal
        tvProgram.text = program
        tvSensitiv.text = sensitiv
        tvFrekuensi.text = frekuensi
        tvVoiceDiameter.text = voiceDiameter
        tvVoiceMaterial.text = voiceMaterial
        tvVoiceWinding.text = voiceWinding
        tvMagnet.text = magnet
        tvCone.text = cone
        tvSorround.text = sorround
        tvBasket.text = basket
        tvMagnetMaterial.text = magnetMaterial
    }
}