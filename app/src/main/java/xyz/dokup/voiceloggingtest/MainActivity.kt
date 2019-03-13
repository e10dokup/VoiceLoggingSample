package xyz.dokup.voiceloggingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val recorder = Recorder()
    private var isRecording = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if (isRecording) {
                recorder.stopRecord()
            } else {
                recorder.startRecord(this)
            }
            isRecording = !isRecording
        }
    }


}
