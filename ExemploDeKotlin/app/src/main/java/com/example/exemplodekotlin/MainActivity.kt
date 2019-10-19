package com.example.exemplodekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

fun Int.somar(b : Int) {this + b}

class MainActivity : AppCompatActivity() {

    //val é constante e var é variável

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contatoJava = ContatoJava(
            1,
            "Ricardo",
            "ricardorodrigo.oliveira@gmail.com",
            "11969051743")

        val contatoKotlin = ContatoKotlin(
            1,
            "Ricardo",
            null,
            null)

        txtExemplo.text = "Qualquer coisa aqui"

        contatoJava.nome = "Ricardo"
        contatoJava.email = "ricardorodrigo.oliveira@gmail.com"

        var soma:String = 10.somar(5) as String
        Toast.makeText(this, soma, Toast.LENGTH_SHORT).show()

    }

    fun somar(a : Int, b : Int) {a + b}
}
