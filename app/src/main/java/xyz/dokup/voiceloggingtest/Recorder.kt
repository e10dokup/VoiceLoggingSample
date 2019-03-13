package xyz.dokup.voiceloggingtest

import android.content.Context
import android.media.MediaRecorder
import androidx.core.content.FileProvider
import java.io.File
import java.lang.Exception


class Recorder {

    companion object {
        private const val fileName = "/sample.aac"
    }

    private var mediaRecorder: MediaRecorder? = null

    fun startRecord(context: Context) {
        if (mediaRecorder != null) {
            return
        }
        val shareFilePath = context.filesDir.path + fileName
        val shareFile = File(shareFilePath)
        if (shareFile.exists()) {
            shareFile.delete()
        }
        try {
            mediaRecorder = MediaRecorder().apply {
                setAudioChannels(1)
                setMaxDuration(180 * 1000)
                setAudioSamplingRate(48000)
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setOutputFile(shareFilePath)
            }

            mediaRecorder?.prepare()
            mediaRecorder?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopRecord() {
        mediaRecorder ?: return
        try {
            mediaRecorder?.run {
                stop()
                reset()
                release()
            }
            mediaRecorder = null
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
