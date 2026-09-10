import java.net.*;

import java.util.*;


class TimeServer{

    public static void main(String args[]) throws Exception {


        DatagramSocket ds = new DatagramSocket(5000);


        while (true) {

            byte[] receive = new byte[100];


            DatagramPacket dp =

                new DatagramPacket(receive, receive.length);


            ds.receive(dp);


            // Create a thread for each client

            new Thread(() -> {

                try {

                    String time = new Date().toString();

                    byte[] send = time.getBytes();


                    DatagramPacket reply =

                        new DatagramPacket(

                            send, send.length,

                            dp.getAddress(), dp.getPort());


                    ds.send(reply);


                } catch (Exception e) {

                    e.printStackTrace();

                }

            }).start();

        }

    }

}
