package de.gyko.gameofcolors.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Spielt die Rolle eines Servers
 * TODO: Besser machen
 *
 * @author anonymous123-code
 */
public class Server implements Runnable{

    private Socket socket;
    private boolean closed = false;

    Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

    /**
     *
     *
     * @param port Port für den Server
     */
    public Server (int port){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!closed) {
                socket = serverSocket.accept();
                threadPool.submit(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args){
        new Server(6000);
    }


    @Override
    public void run() {
        try (OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream();
             PrintWriter outputStreamWriter = new PrintWriter(out, true);
             Scanner scanner = new Scanner(in)
        ){

            while (!closed) {
                //TODO: Aktuell nur ein Echo -> Richtig implementieren
                String input = scanner.nextLine();
                String[] inputSplit = input.split(" ");
                if(inputSplit.length < 2){
                    outputStreamWriter.println(input);
                } else if (inputSplit[0].equalsIgnoreCase("set")) {
                    if (inputSplit.length == 2){
                        map.remove(inputSplit[1]);
                    } else {
                        map.put(inputSplit[1], input.substring(inputSplit[0].length()+inputSplit[1].length()+2));
                    }
                } else if (inputSplit[0].equalsIgnoreCase("get")){
                    String result = map.get(inputSplit[1]);
                    outputStreamWriter.println(Objects.requireNonNullElse(result, "ERROR: Key not found"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }
}
