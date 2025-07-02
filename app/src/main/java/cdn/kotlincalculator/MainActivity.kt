package cdn.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import cdn.kotlincalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Api35Utils.applySystemBarInsets(this, true, true, true, null)

        // Numbers
        binding.num0.setOnClickListener {
            Toast.makeText(this, "0 clicked", Toast.LENGTH_SHORT).show()
            appendVal("0", false) }
        binding.num1.setOnClickListener { appendVal("1", false) }
        binding.num2.setOnClickListener { appendVal("2", false) }
        binding.num3.setOnClickListener { appendVal("3", false) }
        binding.num4.setOnClickListener { appendVal("4", false) }
        binding.num5.setOnClickListener { appendVal("5", false) }
        binding.num6.setOnClickListener { appendVal("6", false) }
        binding.num7.setOnClickListener { appendVal("7", false) }
        binding.num8.setOnClickListener { appendVal("8", false) }
        binding.num9.setOnClickListener { appendVal("9", false) }
        binding.numDot.setOnClickListener { appendVal(".", false) }
        // Operators
        binding.clear.setOnClickListener { appendVal("", true) }
        binding.startBracket.setOnClickListener { appendVal(" ( ", false) }
        binding.closeBracket.setOnClickListener { appendVal(" ) ", false) }
        binding.actionDivide.setOnClickListener { appendVal(" / ", false) }
        binding.actionMultiply.setOnClickListener { appendVal(" * ", false) }
        binding.actionMinus.setOnClickListener { appendVal(" - ", false) }
        binding.actionAdd.setOnClickListener { appendVal(" + ", false) }

        binding.actionBack.setOnClickListener {
            val expression = binding.placeholder.text.toString()
            if (expression.isNotEmpty()) {
                binding.placeholder.text = expression.substring(0, expression.length - 1)
            }
        }

        binding.actionEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.placeholder.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding.answer.text = longResult.toString()
                } else
                    binding.answer.text = result.toString()
            } catch (e: Exception) {
                val message = e.message ?: "An error occurred"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                Log.d("EXCEPTION", "Message: ${e.message}")
            }
        }
    }

    fun appendVal(string: String, isClear: Boolean) {
        if (isClear) {
            binding.placeholder.text = ""
            binding.answer.text = ""
        } else {
            binding.placeholder.append(string)
        }
    }
}