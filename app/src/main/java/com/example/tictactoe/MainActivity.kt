package com.example.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun buttonClick(view: View){
        val click = view as Button
        var cellID = 0

        when(click.id){
            R.id.button1 -> cellID = 1
            R.id.button2 -> cellID = 2
            R.id.button3 -> cellID = 3
            R.id.button4 -> cellID = 4
            R.id.button5 -> cellID = 5
            R.id.button6 -> cellID = 6
            R.id.button7 -> cellID = 7
            R.id.button8 -> cellID = 8
            R.id.button9 -> cellID = 9
        }

        playGame(cellID,click)
    }

    var activePlayer = 1
    var player1: ArrayList<Int> = ArrayList<Int>()
    var player2: ArrayList<Int> = ArrayList<Int>()

    fun playGame(cellID: Int, click:Button){

        if(activePlayer == 1){
            click.text = "X"
            click.setBackgroundColor(Color.GRAY)
            player1.add(cellID)
            activePlayer = 2
            autoPlay()
        }else{
            click.text = "O"
            click.setBackgroundColor(Color.RED)
            player2.add(cellID)
            activePlayer = 1
        }
        Log.d("size1", player1.size.toString())
        Log.d("size2", player2.size.toString())


        click.isEnabled = false

        checkWinner()
    }

    fun checkWinner(){
        var check = -1

        //Row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            check = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            check = 2
        }

        //Row 2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            check = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            check = 2
        }

        //Row 3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            check = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            check = 2
        }

        //Column 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            check = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            check = 2
        }

        //Column 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            check = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            check = 2
        }

        //Column 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            check = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            check = 2
        }

        when (check) {
            1 -> {
                Toast.makeText(this,"Player 1 wins the game.",Toast.LENGTH_LONG).show()
                player1Counts += 1
                restart()
            }
            2 -> {
                Toast.makeText(this,"Player 2 wins the game.",Toast.LENGTH_LONG).show()
                player2Counts += 1
                restart()
            }
        }

    }

    fun autoPlay(){

        var emptyCells = ArrayList<Int>()

        for( cellID in 1..9){
            if(!(player1.contains(cellID) || player2.contains(cellID))){
                emptyCells.add(cellID)
            }
        }

        if(emptyCells.size == 0){
            restart()
        }

        val r = Random
        val randomIndex = r.nextInt(emptyCells.size)
        val cellID = emptyCells[randomIndex]

        val button : Button?
        val buttonSelected = when(cellID){
            1 -> button1
            2 -> button2
            3 -> button3
            4 -> button4
            5 -> button5
            6 -> button6
            7 -> button7
            8 -> button8
            9 -> button9
            else -> {button1}
        }

        playGame(cellID,buttonSelected)


    }

    var player1Counts = 0
    var player2Counts = 0

    fun restart(){
        activePlayer = 1
        player1.clear()
        player2.clear()

        for(cellID in 1..9){
            val buttonSelected: Button? = when(cellID){
                1-> button1
                2-> button2
                3-> button3
                4-> button4
                5-> button5
                6-> button6
                7-> button7
                8-> button8
                9-> button9
                else -> {button1}
            }

            buttonSelected!!.isEnabled = true
            buttonSelected!!.setBackgroundColor(Color.WHITE)
            buttonSelected!!.text = ""
        }

        Toast.makeText(this, "Player 1: $player1Counts , Player 2: $player2Counts",Toast.LENGTH_LONG).show()
    }

}

