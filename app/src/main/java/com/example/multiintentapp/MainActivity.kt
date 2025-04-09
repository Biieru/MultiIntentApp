package com.example.multiintentapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Encontrando os botões e o EditText
        val btnOpenBrowser = findViewById<Button>(R.id.btnOpenBrowser)
        val etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)
        val btnMakeCall = findViewById<Button>(R.id.btnMakeCall)
        val btnSendSMS = findViewById<Button>(R.id.btnSendSMS)
        val btnOpenMap = findViewById<Button>(R.id.btnOpenMap)
        val btnShareText = findViewById<Button>(R.id.btnShareText)

        // Botão Abrir Navegador
        btnOpenBrowser.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            startActivity(intent)
        }

        // Botão Fazer Ligação
        btnMakeCall.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        }

        // Botão Enviar SMS
        btnSendSMS.setOnClickListener {
            // Você pode mudar o número aqui se desejar
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:123456789"))
            intent.putExtra("sms_body", "Olá! Esta é uma mensagem de teste.")
            startActivity(intent)
        }

        // Botão Abrir Mapa
        btnOpenMap.setOnClickListener {
            // Coordenadas de Recife
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-8.0476,-34.8770"))
            startActivity(intent)
        }

        // Botão Compartilhar Texto
        btnShareText.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Olá, estou aprendendo Android!")
            startActivity(Intent.createChooser(intent, "Compartilhar via"))
        }
    }
}