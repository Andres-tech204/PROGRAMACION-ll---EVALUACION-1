package cl.andvasquez.android.u1_pr2_pev

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.andvasquez.android.u1_pr2_pev.modelo.ItemMenu

class MainActivity : AppCompatActivity() {
    private lateinit var switchAgregarPropina: Switch
    private lateinit var etCantidadCazuela: EditText
    private lateinit var etCantidadPastel: EditText
    private lateinit var tvMontoComida: TextView

    private val cazuela = ItemMenu("Cazuela", 10000)
    private val pastelDeChoclo = ItemMenu("Pastel de Choclo", 12000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchAgregarPropina = findViewById(R.id.switchAgregarPropina)
        etCantidadCazuela = findViewById(R.id.etCantidadCazuela)
        etCantidadPastel = findViewById(R.id.etCantidadPastel)
        tvMontoComida = findViewById(R.id.tvMontoComida)

        // Escuchar cambios en los campos de cantidad
        etCantidadCazuela.addTextChangedListener(CantidadTextWatcher())
        etCantidadPastel.addTextChangedListener(CantidadTextWatcher())

        // Escuchar cambios en el switch
        switchAgregarPropina.setOnCheckedChangeListener { _, _ ->
            calcularMontoTotal()
        }
    }

    private inner class CantidadTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            calcularMontoTotal()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    private fun calcularMontoTotal() {
        val cantidadCazuela = etCantidadCazuela.text.toString().toIntOrNull() ?: 0
        val cantidadPastel = etCantidadPastel.text.toString().toIntOrNull() ?: 0
        val totalCazuela = cazuela.precio * cantidadCazuela
        val totalPastel = pastelDeChoclo.precio * cantidadPastel
        var total = totalCazuela + totalPastel

        // Verifica si el switch está activado
        val agregarPropina = switchAgregarPropina.isChecked

        // Si el switch está activado, agrega un 10% al total
        if (agregarPropina) {
            val propina = total * 0.1
            total += propina.toInt()
        }

        tvMontoComida.text = "$ $total"
    }
}
