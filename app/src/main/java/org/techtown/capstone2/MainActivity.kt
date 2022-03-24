package org.techtown.capstone2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    companion object{
        val test_url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"
    }

    private var mp:MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val music_player = layoutInflater.inflate(R.layout.music_player,container)

        val toggle = music_player.findViewById<ToggleButton>(R.id.toggleButton)
        val seekBar = music_player.findViewById<SeekBar>(R.id.seekBar)

        toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            when(isChecked){
                true -> {
                    if(mp == null){
                        mp = MediaPlayer()
                        mp?.apply{
                            setDataSource(test_url)
                            prepare()
                        }
                        initializeSeekBar(seekBar)
                    }
                    mp?.start()
                }
                false -> {
                    mp?.pause()
                }
            }
        }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) mp?.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })


    }

    fun initializeSeekBar(seekBar:SeekBar){
        seekBar.max = mp!!.duration

        val handler = Handler()
        handler.postDelayed(object:Runnable{
            override fun run() {
                try{
                    seekBar.progress = mp!!.currentPosition
                    handler.postDelayed(this,1000)
                }catch (e: Exception){
                    seekBar.progress = 0
                }
            }
        },0)
    }
}