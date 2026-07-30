import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class UDPServer {

    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket(9876);

        byte[] receiveData = new byte[2048];
        byte[] sendData;

        System.out.println("UDP Server is running...");

        Map<String, String> map = new HashMap<>();

        map.put("tbh", "to be honest");
        map.put("ig", "I guess");
        map.put("tbf", "to be fair");
        map.put("atm", "at the moment");
        map.put("irl", "in real life");
        map.put("lol", "laughing out loud");
        map.put("asap", "as soon as possible");
        map.put("omg", "oh my God");
        map.put("ttyl", "talk to you later");
        map.put("idk", "I don't know");
        map.put("nvm", "never mind");
        map.put("idc", "I don't care");

        while (true) {

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            socket.receive(receivePacket);

            String sentence = new String(
                    receivePacket.getData(),
                    0,
                    receivePacket.getLength());

            System.out.println("Received: " + sentence);

            String translated = translate(sentence, map);

            sendData = translated.getBytes();

            InetAddress clientIP = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            DatagramPacket sendPacket =
                    new DatagramPacket(
                            sendData,
                            sendData.length,
                            clientIP,
                            clientPort);

            socket.send(sendPacket);

            System.out.println("Translated Sentence Sent: " + translated);
        }
    }

    public static String translate(String sentence,
                                   Map<String, String> map) {

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {

            String punctuation = "";

            while (word.length() > 0 &&
                    !Character.isLetterOrDigit(word.charAt(word.length() - 1))) {

                punctuation = word.charAt(word.length() - 1) + punctuation;
                word = word.substring(0, word.length() - 1);
            }

            String lower = word.toLowerCase();

            if (map.containsKey(lower))
                result.append(map.get(lower));
            else
                result.append(word);

            result.append(punctuation).append(" ");
        }

        return result.toString().trim();
    }
}
