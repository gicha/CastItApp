package com.example.ge72.castit;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

import static com.example.ge72.castit.FlownPlayer.datas;
import static com.example.ge72.castit.FlownPlayer.datas_len;
import static com.example.ge72.castit.FlownPlayer.datas_no;

/**
 * Created by GE72 on 14.08.2017.
 */

public class Listener extends Thread {

    public Listener() {

    }

    @Override
    public void run() {
        try {
            int bufferSize = AudioTrack.getMinBufferSize(16000,
                    AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT);
            AudioTrack aTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                    16000, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
                    bufferSize, AudioTrack.MODE_STREAM);
            Log.e("ReceiverPlayer", "Play");
            aTrack.play();

            while (true) {
                aTrack.write(datas.take(), 0, datas_len.take()); //пишем блок в колонки
                datas_no.take();//номер воспроизводимого блока
            }
        } catch (InterruptedException ex) {
        }
    }
}
