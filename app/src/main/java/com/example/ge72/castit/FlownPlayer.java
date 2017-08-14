package com.example.ge72.castit;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by GE72 on 06.08.2017.
 */

class FlownPlayer extends Thread {
    public static ArrayBlockingQueue<byte[]> datas = new ArrayBlockingQueue<>(10);
    public static ArrayBlockingQueue<Integer> datas_len = new ArrayBlockingQueue<>(10);
    public static ArrayBlockingQueue<Integer> datas_no = new ArrayBlockingQueue<>(10);
    volatile boolean finishFlag;
    String host;

    public FlownPlayer(String hostname) {
        host = hostname;
        finishFlag = false;
    }

    public void setFinishFlag() {
        finishFlag = true;
    }

    public void run() {
        try {
            new Listener().start();

            Socket s = new Socket(host, 7373);
            InputStream is = s.getInputStream();
            byte[] data = new byte[204800];
            DataInputStream x;
            int data_size;
            x = new DataInputStream(is);
            while (true) {
                if (x.available() >= 20488) {
                    int no = x.readInt();//читаем номер
                    data_size = x.readInt();//длину
                    data_size = x.read(data, 0, data_size);//сам блок
                    datas.add(data);
                    datas_len.add(data_size);
                    datas_no.add(no);
//                    Thread.sleep(data_size / 100);//ждём... ... ... (против нагрузки на проц)
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}