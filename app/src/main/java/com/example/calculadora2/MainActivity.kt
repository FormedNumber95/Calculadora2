package com.example.calculadora2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora2.databinding.ActivityMainBinding

/**
 * Actividad principal de la aplicación de calculadora, que implementa operaciones básicas.
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private var firstNumber=0.0
    private var secondNumber=0.0
    private var operation: String?=null

    /**
     * Inicializa la actividad, configurando el diseño y los listeners de clic para cada botón.
     *
     * @param savedInstanceState Si la actividad se está re-inicializando después de haber sido
     * previamente cerrada, este Bundle contiene los datos más recientes.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        operation=null

        binding.btn0.setOnClickListener(this)
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)
        binding.btnClear.setOnClickListener(this)
        binding.btnMas.setOnClickListener(this)
        binding.btnMenos.setOnClickListener(this)
        binding.btnPor.setOnClickListener(this)
        binding.btnDiv.setOnClickListener(this)
        binding.btnIgual.setOnClickListener(this)
        binding.btnComa.setOnClickListener(this)
    }

    /**
     * Método que se ejecuta al hacer clic en uno de los botones de la calculadora.
     *
     * @param view La vista del botón que ha sido clicado.
     */
    override fun onClick(view: View?) {
        when(view){
            binding.btn0 -> onNumberPresses("0")
            binding.btn1 -> onNumberPresses("1")
            binding.btn2 -> onNumberPresses("2")
            binding.btn3 -> onNumberPresses("3")
            binding.btn4 -> onNumberPresses("4")
            binding.btn5 -> onNumberPresses("5")
            binding.btn6 -> onNumberPresses("6")
            binding.btn7 -> onNumberPresses("7")
            binding.btn8 -> onNumberPresses("8")
            binding.btn9 -> onNumberPresses("9")
            binding.btnComa -> onNumberPresses(",")
            binding.btnMas -> onOperationPresed("+")
            binding.btnMenos -> onOperationPresed("-")
            binding.btnPor -> onOperationPresed("x")
            binding.btnDiv -> onOperationPresed("/")
            binding.btnIgual -> onEqualPressed()
            binding.btnClear -> onClearPressed()
        }
    }


    /**
     * Maneja el evento cuando se presiona un número.
     *
     * @param number El número en formato de cadena.
     */
    private fun onNumberPresses(number:String){
        renderScreen(number)
        checkOperation()
    }

    /**
     * Actualiza la pantalla de la calculadora con el número ingresado.
     *
     * @param number El número en formato de cadena que se mostrará en la pantalla.
     */
    private fun renderScreen(number:String){
        val result:String = if(binding.screen.text=="0" && number!=",")
            number
        else
            "${binding.screen.text}$number"

        binding.screen.text=result
    }

    /**
     * Comprueba la operación actual, asignando valores a los números correspondientes.
     */
    private fun checkOperation(){
        if(operation==null)
            firstNumber=binding.screen.text.toString().toDouble()
        else
            secondNumber=binding.screen.text.toString().toDouble()
    }

    /**
     * Maneja el evento cuando se presiona un operador.
     *
     * @param operation El operador en formato de cadena.
     */
    private fun onOperationPresed(operation:String){
        this.operation=operation
        firstNumber=binding.screen.text.toString().toDouble()

        binding.screen.text="0"
    }

    /**
     * Calcula el resultado de la operación y actualiza la pantalla.
     */
    private fun onEqualPressed(){
        val result=when(operation){
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "x" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            else ->0
        }

        operation=null
        firstNumber=result.toDouble()
        try{
            binding.screen.text=if(result.toString().endsWith(".0")){
                result.toString().replace(".0","")
            }else{
                "%.2".format(result)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    /**
     * Limpia la pantalla y restablece los valores de los números.
     */
    private fun onClearPressed(){
        binding.screen.text="0"
        firstNumber=0.0
        secondNumber=0.0
    }
}

