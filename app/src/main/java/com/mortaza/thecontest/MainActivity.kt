package com.mortaza.thecontest

import android.media.MediaPlayer
import android.os.Bundle
import android.util.IntProperty
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlin.properties.ObservableProperty

class MainActivity : AppCompatActivity() {

    // Set the initial count to 0
    private var count = 0
    private val TAG = "ContestActivity"
    private val KEY_INDEX = "count_index"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate() called")
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_INDEX, 0)
        }

        var music: MediaPlayer = MediaPlayer.create(this, R.raw.achievement_sound)


        // Find the buttons and text label in the layout
        val incrementButton: Button = findViewById(R.id.increment_button)
        val decrementButton: Button = findViewById(R.id.decrement_button)
        val resetButton: Button = findViewById(R.id.reset_button)
        val countTextView: TextView = findViewById(R.id.count_text_view)

        // Set the initial text of the label to the current count
        countTextView.text = "$count"

        // Increment the count when the user clicks the "Increment" button
        incrementButton.setOnClickListener {
            count++
            Log.i(TAG, "Value Incremented: $count")
            countTextView.text = "$count"
        }

        // Decrement the count when the user clicks the "Decrement" button
        decrementButton.setOnClickListener {
            count--
            Log.i(TAG, "Value Decremented: $count")
            countTextView.text = "$count"
        }

        // Reset the count when the user clicks the "Reset" button
        resetButton.setOnClickListener {
            count = 0
            countTextView.text = "$count"
            Log.i(TAG, "Value Reset: $count")
        }

        // Listen for when the text view changes
        countTextView.addTextChangedListener {
            when (count) {
                15 -> {
                    music.start()
                }
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        Log.d(TAG, "onSaveInstanceState() called")
        outState.putInt(KEY_INDEX, count)
    }
}