package de.gyko.gameofcolors.net.server;

import de.gyko.gameofcolors.net.PacketReceiveEvent;
import de.gyko.gameofcolors.net.PacketReceiveListener;
import de.gyko.gameofcolors.net.PacketSendRequest;
import de.gyko.gameofcolors.net.PacketSendRequest.Target;
import de.gyko.gameofcolors.net.TextPacket;

import java.util.*;

/**
 * Ein Server fuer Testzwecke
 *
 * @author anonymous123-code
 */
public class Testserver {
    public static void main(String... args) {
        new Server(new PacketReceiveListener() {
            final Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

            @Override
            public ArrayList<PacketSendRequest> onPacketReceived(PacketReceiveEvent p) {
                ArrayList<PacketSendRequest> requests = new ArrayList<>();
                if (p.getPacket() instanceof TextPacket) {
                    String input = ((TextPacket) p.getPacket()).getText();
                    String[] inputSplit = input.split(" ");
                    if (inputSplit.length < 2) {
                        requests.add(new PacketSendRequest(Target.ALL_EXCEPT_CALLER, new TextPacket(input + '\n')));
                    } else if (inputSplit[0].equalsIgnoreCase("set")) {
                        if (inputSplit.length == 2) {
                            map.remove(inputSplit[1]);
                        } else {
                            map.put(inputSplit[1], input.substring(inputSplit[0].length() + inputSplit[1].length() + 2));
                        }
                    } else if (inputSplit[0].equalsIgnoreCase("get")) {
                        System.out.println(map);
                        String result = map.get(inputSplit[1]);
                        result = (result != null ? result : "ERROR: Key not found") + '\n';
                        requests.add(new PacketSendRequest(Target.CALLER, new TextPacket(result)));
                    } else {
                        requests.add(new PacketSendRequest(Target.ALL_EXCEPT_CALLER, new TextPacket(input + '\n')));
                    }
                }
                return requests;
            }
        }, 6000);
    }
}
