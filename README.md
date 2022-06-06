# TipTime-Android
1 - Criar um projeto com o nome TipTime
2 - Em ActivityMain.kt 

3 - Fazer a seguinte configuração para referenciar a Binding e criar um evento para o botao Calcular
    
   Modifique o build.gradle do módulo do app
    
    android {
    ...

    buildFeatures {
        viewBinding = true
    }
    ...
}

4 -  Adicionar as seguintes linhas para referenciar o binding e realizar o evento o botao calculateButton e btnProxima

         lateinit var binding: ActivityMainBinding
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }  //Executa a função calculateTip
        binding.btnProxima.setOnClickListener { prosseguir() } // Executa a função Prosseguir


adicionar  a seguinte linha binding.calculateButton.setOnClickListener{ calculateTip() }



5 - criar a funcao calculateTip

    fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString() // Acessa o Custo de serviço
        val cost = stringInTextField.toDouble() // Converte o texto em numero decimal
        val selectedId = binding.tipOptions.checkedRadioButtonId  //Esta Val acessa a porcentagem da gorjeta
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        //Esta var é usada para realizar o arredondamento da gorjeta para cima, caso o usuario clique em no  Checkedk
        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip) //Formota a gorjeta para um tipo de moeda que pode ser alterado de acordo com o idioma do sistena do dispotivo
        binding.editGorjeta.text = getString(R.string.tip_amount, formattedTip) // Exibe o valor da gorjeta na ViewText @id/TipResult 
    }
Criar função para Prosseguir
// Esta função tem como objetivo enviar o resultado da gorjeta para a segunda tela(SegundaActivity) Atraves da Itennt

     fun prosseguir(){
            val nome = binding.editGorjeta.text.toString()

            val intent = Intent (this, SegundaActivity::class.java)
            intent.putExtra("resultado", nome)

            startActivity(intent)
    }
  ====================================================================================================================================
    Codigo da segunda activity
    
  1 - realizar a referencia da Binding
  
  
    package com.example.tiptime

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import com.example.tiptime.databinding.ActivityMainBinding
    import com.example.tiptime.databinding.ActivitySegundaBinding

    class SegundaActivity : AppCompatActivity() {
        lateinit var binding: ActivitySegundaBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySegundaBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setContentView(R.layout.activity_segunda)
            recuperaDados()


       }
    
2 -  Criar um função para receber os dados e exibir na tela

        private fun recuperaDados(){

            binding.editGorjeta.text = getString(R.string.tip_amount)
            val nome  = intent.getStringExtra("resultado")

            binding.editGorjeta.text=nome
        }


    }
    
    
    
    
   Projero original Calcular Gorjeta :
   https://developer.android.com/codelabs/basic-android-kotlin-training-tip-calculator#0
    
    
    
