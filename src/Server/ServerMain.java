package Server;

import utility.Const;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by MUKTADIR on 11/21/2015.
 */

public class ServerMain {

    public static void main(String[] args) throws ClassNotFoundException {
        try{
            ServerSocket listener = new ServerSocket(Const.portNo); //create a listener that will track the socket to check whether any client has sent a request or not;
            Socket client; //this is to store the socket of the accepted client
            RequestHandler requestHandler = new RequestHandler();
            while (true) { //this listen to the socket continuously
                client = listener.accept(); //whenever get any request, this line accept it and returns the socket

                new ClientHandler(client, requestHandler); //this line create a new thread to communicate with the clients;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




