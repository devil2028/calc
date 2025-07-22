package cdn.kotlincalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
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

        // Numbers with animation
        binding.num0.setOnClickListener { animateButtonClick(it) { appendVal("0", false) } }
        binding.num1.setOnClickListener { animateButtonClick(it) { appendVal("1", false) } }
        binding.num2.setOnClickListener { animateButtonClick(it) { appendVal("2", false) } }
        binding.num3.setOnClickListener { animateButtonClick(it) { appendVal("3", false) } }
        binding.num4.setOnClickListener { animateButtonClick(it) { appendVal("4", false) } }
        binding.num5.setOnClickListener { animateButtonClick(it) { appendVal("5", false) } }
        binding.num6.setOnClickListener { animateButtonClick(it) { appendVal("6", false) } }
        binding.num7.setOnClickListener { animateButtonClick(it) { appendVal("7", false) } }
        binding.num8.setOnClickListener { animateButtonClick(it) { appendVal("8", false) } }
        binding.num9.setOnClickListener { animateButtonClick(it) { appendVal("9", false) } }
        binding.numDot.setOnClickListener { animateButtonClick(it) { appendVal(".", false) } }
        
        // Operators with animation
        binding.clear.setOnClickListener { animateButtonClick(it) { appendVal("", true) } }
        binding.startBracket.setOnClickListener { animateButtonClick(it) { appendVal(" ( ", false) } }
        binding.closeBracket.setOnClickListener { animateButtonClick(it) { appendVal(" ) ", false) } }
        binding.actionDivide.setOnClickListener { animateButtonClick(it) { appendVal(" / ", false) } }
        binding.actionMultiply.setOnClickListener { animateButtonClick(it) { appendVal(" * ", false) } }
        binding.actionMinus.setOnClickListener { animateButtonClick(it) { appendVal(" - ", false) } }
        binding.actionAdd.setOnClickListener { animateButtonClick(it) { appendVal(" + ", false) } }

        binding.actionBack.setOnClickListener {
            animateButtonClick(it) {
                val expression = binding.placeholder.text.toString()
                if (expression.isNotEmpty()) {
                    binding.placeholder.text = expression.substring(0, expression.length - 1)
                }
            }
        }

        binding.actionEquals.setOnClickListener {
            animateButtonClick(it) {
                try {
                    val expression = ExpressionBuilder(binding.placeholder.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    if (result == longResult.toDouble()) {
                        animateResultDisplay(longResult.toString())
                    } else {
                        animateResultDisplay(result.toString())
                    }
                } catch (e: Exception) {
                    val message = e.message ?: "An error occurred"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    Log.d("EXCEPTION", "Message: ${e.message}")
                }
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

    private fun animateButtonClick(view: View, action: () -> Unit) {
        val pressAnimation = AnimationUtils.loadAnimation(this, R.anim.button_press)
        val releaseAnimation = AnimationUtils.loadAnimation(this, R.anim.button_release)
        
        view.startAnimation(pressAnimation)
        
        pressAnimation.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {}
            
            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                action()
                view.startAnimation(releaseAnimation)
            }
            
            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
        })
    }

    private fun animateResultDisplay(result: String) {
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.text_fade_out)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.text_fade_in)
        
        binding.answer.startAnimation(fadeOut)
        
        fadeOut.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
            override fun onAnimationStart(animation: android.view.animation.Animation?) {}
            
            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                binding.answer.text = result
                binding.answer.startAnimation(fadeIn)
            }
            
            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
        })
    }
}