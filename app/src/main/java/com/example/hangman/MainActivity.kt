package com.example.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hangman.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dict: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dict = arrayOf("riddle", "Python", "javascript", "Bewafa") ;
        var randIndex = Random.nextInt(dict.size)
        var word: String = dict[randIndex] ;
        var wordHidden = ""
        var numGuessLeft = 5 ;
        for (i in 0..word.length -1 ) {
            wordHidden+= "?"
        }
        binding.lblWord.text = wordHidden ;
        binding.txtResult.text = "Gamee started (Gusses left: 5)"

        binding.btnGuess.setOnClickListener {
            numGuessLeft--
            val strInput = binding.etInputWord.text.toString()
            if (strInput == word) {
                binding.lblWord.text = strInput ;
                binding.imgHangman.setImageResource(R.drawable.hangmansaved)
                binding.txtResult.text = "You guessed Correctly, You win"
                binding.btnGuess.isClickable = false

            }
            else{

                var j = 0 ;
                for (i in strInput){
                    if (word.contains(i)){
                        j = word.indexOf(i)
                        wordHidden = (if(wordHidden.substring(0, j) != null) wordHidden.substring(0, j) else "") + i + (if(wordHidden.substring(j + 1) != null) wordHidden.substring(j + 1) else "")
                    }
                }

                binding.lblWord.text = wordHidden ;
                binding.txtResult.text = "You guessed: ${strInput} (Guesses left: ${numGuessLeft})"
                if (numGuessLeft == 0){
                    binding.imgHangman.setImageResource(R.drawable.hang)
                    binding.txtResult.text = "YOU Lose.. (Guesses left: ${numGuessLeft})"
                    binding.btnGuess.isClickable = false

                }

            }
        }
        binding.btnNew.setOnClickListener {
            binding.imgHangman.setImageResource(R.drawable.beforehang)
            randIndex = Random.nextInt(dict.size)
            word= dict[randIndex] ;
            wordHidden = ""
            numGuessLeft = 5 ;
            for (i in 0..word.length -1 ) {
                wordHidden+= "?"
            }
            binding.lblWord.text = wordHidden ;
            binding.btnGuess.isClickable = true
            binding.txtResult.text = "Gamee started (Gusses left: 5)"

        }
    }
}