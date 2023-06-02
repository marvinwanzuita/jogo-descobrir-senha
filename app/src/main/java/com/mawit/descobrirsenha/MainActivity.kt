package com.mawit.descobrirsenha

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.mawit.descobrirsenha.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private lateinit var senhaAleatoria : MutableList<String>
    private var jogadas : Int? = null
    private var mInterstitialAd: InterstitialAd? = null
    lateinit var mAdView: AdView
    private final var TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializarAds()

        senhaAleatoria = gerarSenhaAleatoria()
        jogadas = 1

        binding.btnLimpar.setOnClickListener {
            botaoLimpar()
        }

        binding.btnJogar.setOnClickListener {
            if (verificarNumerosDigitados(lerNumerosDigitados())){
                when(jogadas){
                    1 -> resultado1(lerNumerosDigitados(), senhaAleatoria, 1)
                    2 -> resultado2(lerNumerosDigitados(), senhaAleatoria, 2)
                    3 -> resultado3(lerNumerosDigitados(), senhaAleatoria, 3)
                    4 -> resultado4(lerNumerosDigitados(), senhaAleatoria, 4)
                    5 -> resultado5(lerNumerosDigitados(), senhaAleatoria, 5)
                    6 -> resultado6(lerNumerosDigitados(), senhaAleatoria, 6)
                }
                jogadas = jogadas!! + 1
                botaoLimpar()

            } else {
                Toast.makeText(
                    this,
                    "Digite somente números entre 1 e 6",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }


    }

    fun gerarSenhaAleatoria(): MutableList<String>{

        val random = Random
        val senhaAleatoria = mutableSetOf<String>()

        while (senhaAleatoria.size < 4) {
            senhaAleatoria.add(random.nextInt(1, 7).toString())
        }

        return senhaAleatoria.toMutableList()
    }

    fun lerNumerosDigitados(): MutableList<String>{

        val listaNumerosDigitados = mutableListOf<String>()

        listaNumerosDigitados.add(binding.edtNumero1.text.toString())
        listaNumerosDigitados.add(binding.edtNumero2.text.toString())
        listaNumerosDigitados.add(binding.edtNumero3.text.toString())
        listaNumerosDigitados.add(binding.edtNumero4.text.toString())

        return listaNumerosDigitados

    }

    fun resultado1(listaNumerosDigitados: MutableList<String>, senhaAleatoria: MutableList<String>, jogadas: Int){

        var acertados = 0
        var posicaoCerta = 0
        var tentadoString = ""

        for (i in senhaAleatoria.indices){

            if (senhaAleatoria[i].equals(listaNumerosDigitados[i])) posicaoCerta++
            if (listaNumerosDigitados.contains(senhaAleatoria[i])) acertados++

        }

        binding.txtAcertado1.text = acertados.toString()
        binding.txtPosicao1.text = posicaoCerta.toString()
        listaNumerosDigitados.forEach {
            tentadoString += "$it "
        }

        verificarGanhador(acertados, posicaoCerta, tentadoString, jogadas)
        binding.txtTentado1.text = tentadoString
    }

    fun resultado2(listaNumerosDigitados: MutableList<String>, senhaAleatoria: MutableList<String>, jogadas: Int){

        var acertados = 0
        var posicaoCerta = 0
        var tentadoString = ""

        for (i in senhaAleatoria.indices){

            if (senhaAleatoria[i].equals(listaNumerosDigitados[i])) posicaoCerta++
            if (listaNumerosDigitados.contains(senhaAleatoria[i])) acertados++

        }

        binding.txtAcertado2.text = acertados.toString()
        binding.txtPosicao2.text = posicaoCerta.toString()
        listaNumerosDigitados.forEach {
            tentadoString += "$it "
        }

        verificarGanhador(acertados, posicaoCerta, tentadoString, jogadas)

        binding.txtTentado2.text = tentadoString

    }

    fun resultado3(listaNumerosDigitados: MutableList<String>, senhaAleatoria: MutableList<String>, jogadas: Int){

        var acertados = 0
        var posicaoCerta = 0
        var tentadoString = ""

        for (i in senhaAleatoria.indices){

            if (senhaAleatoria[i].equals(listaNumerosDigitados[i])) posicaoCerta++
            if (listaNumerosDigitados.contains(senhaAleatoria[i])) acertados++

        }

        binding.txtAcertado3.text = acertados.toString()
        binding.txtPosicao3.text = posicaoCerta.toString()
        listaNumerosDigitados.forEach {
            tentadoString += "$it "
        }

        verificarGanhador(acertados, posicaoCerta, tentadoString, jogadas)

        binding.txtTentado3.text = tentadoString
    }

    fun resultado4(listaNumerosDigitados: MutableList<String>, senhaAleatoria: MutableList<String>, jogadas: Int){

        var acertados = 0
        var posicaoCerta = 0
        var tentadoString = ""

        for (i in senhaAleatoria.indices){

            if (senhaAleatoria[i].equals(listaNumerosDigitados[i])) posicaoCerta++
            if (listaNumerosDigitados.contains(senhaAleatoria[i])) acertados++

        }

        binding.txtAcertado4.text = acertados.toString()
        binding.txtPosicao4.text = posicaoCerta.toString()
        listaNumerosDigitados.forEach {
            tentadoString += "$it "
        }
        verificarGanhador(acertados, posicaoCerta, tentadoString, jogadas)
        binding.txtTentado4.text = tentadoString
    }

    fun resultado5(listaNumerosDigitados: MutableList<String>, senhaAleatoria: MutableList<String>, jogadas: Int){

        var acertados = 0
        var posicaoCerta = 0
        var tentadoString = ""

        for (i in senhaAleatoria.indices){

            if (senhaAleatoria[i].equals(listaNumerosDigitados[i])) posicaoCerta++
            if (listaNumerosDigitados.contains(senhaAleatoria[i])) acertados++

        }

        binding.txtAcertado5.text = acertados.toString()
        binding.txtPosicao5.text = posicaoCerta.toString()
        listaNumerosDigitados.forEach {
            tentadoString += "$it "
        }
        verificarGanhador(acertados, posicaoCerta, tentadoString, jogadas)
        binding.txtTentado5.text = tentadoString
    }

    fun resultado6(listaNumerosDigitados: MutableList<String>, senhaAleatoria: MutableList<String>, jogadas: Int){

        var acertados = 0
        var posicaoCerta = 0
        var tentadoString = ""

        for (i in senhaAleatoria.indices){

            if (senhaAleatoria[i].equals(listaNumerosDigitados[i])) posicaoCerta++
            if (listaNumerosDigitados.contains(senhaAleatoria[i])) acertados++

        }

        binding.txtAcertado6.text = acertados.toString()
        binding.txtPosicao6.text = posicaoCerta.toString()
        listaNumerosDigitados.forEach {
            tentadoString += "$it "
        }
        verificarGanhador(acertados, posicaoCerta, tentadoString, jogadas)
        binding.txtTentado6.text = tentadoString
    }

    fun botaoLimpar(){

        binding.edtNumero1.setText("")
        binding.edtNumero2.setText("")
        binding.edtNumero3.setText("")
        binding.edtNumero4.setText("")

    }

    fun verificarGanhador(acertados: Int, posicaoCerta: Int, tentadoString: String, jogadas: Int){

        if (acertados == posicaoCerta && acertados == 4){
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("PARABÉNS, VOCÊ ACERTOU!!!")
            dialog.setMessage("A senha era $tentadoString.")
            dialog.setPositiveButton("Novo Jogo"){dialogInterface, i ->
                val intent = intent
                finish()
                startActivity(intent)
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(this)
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.")
                }

            }
            dialog.show()
        } else if (jogadas == 6) {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("INFELIZMENTE, VOCÊ NÃO ACERTOU!!!")
            dialog.setMessage("A senha era $tentadoString.")
            dialog.setPositiveButton("Novo Jogo"){dialogInterface, i ->
                val intent = intent
                finish()
                startActivity(intent)
                if (mInterstitialAd != null) {
                    mInterstitialAd?.show(this)
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.")
                }

            }
            dialog.show()
        }


    }

    fun verificarNumerosDigitados(listaNumerosDigitados: MutableList<String>): Boolean{

        var numerosCertos = true
        listaNumerosDigitados.forEach {
            if (it.toInt() !in 1..6){
                return false
            }
        }
        return true
    }

    fun inicializarAds(){
        MobileAds.initialize(this)


        val adViewBanner = AdView(this)
        var adRequest = AdRequest.Builder().build()

        adViewBanner.setAdSize(AdSize.BANNER)
        adViewBanner.adUnitId = "ca-app-pub-5618593123155937/1617415296"
        mAdView = binding.idBanner

        mAdView.loadAd(adRequest)


        InterstitialAd.load(
            this,"ca-app-pub-5618593123155937/3599746657",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    adError.toString().let { Log.d(TAG, it) }
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })

        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d(TAG, "Ad dismissed fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.")
            }
        }

    }


}