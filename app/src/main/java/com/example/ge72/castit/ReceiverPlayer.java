package com.example.ge72.castit;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;

/**
 * Created by GE72 on 06.08.2017.
 */

class ReceiverPlayer extends Thread {
    volatile boolean finishFlag;
    String host;

    public ReceiverPlayer(String hostname) {
        host = hostname;
        finishFlag = false;
    }

    public void setFinishFlag() {
        finishFlag = true;
    }

    public void run() {
        try {
            Log.e("ReceiverPlayer", "Start");

            Socket s = new Socket(host, 7373);
            InputStream is = s.getInputStream();

            int bufferSize = AudioTrack.getMinBufferSize(16000,
                    AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT);

            int numBytesRead;
            byte[] data = new byte[bufferSize];

            AudioTrack aTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                    16000, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                    bufferSize, AudioTrack.MODE_STREAM);
            Log.e("ReceiverPlayer", "Play");
            aTrack.play();

            while (!finishFlag) {
                numBytesRead = is.read(data, 0, bufferSize);
                aTrack.write(data, 0, numBytesRead);
            }

            aTrack.stop();
            s.close();
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            Log.e("Error", sw.toString());
        }
    }
}