import java.net.*;


class TimeClient {

    public static void main(String args[]) throws Exception {


        DatagramSocket ds = new DatagramSocket();


        String msg = "TIME";

        byte[] send = msg.getBytes();


        InetAddress ip =

            InetAddress.getByName("localhost");


        DatagramPacket dp =

            new DatagramPacket(

                send, send.length, ip, 5000);


        ds.send(dp);


        byte[] receive = new byte[100];


        dp = new DatagramPacket(

            receive, receive.length);


        ds.receive(dp);


        String time =

            new String(

                dp.getData(), 0, dp.getLength());


        System.out.println("Server Time: " + time);


        ds.close();

    }

}

