package com.duolingo.app.util;

import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.IServerListener;
import net.sf.lipermi.net.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerRMI implements IServerRMI{

    private static CallHandler callHandler = new CallHandler();
    private static Server server = new Server();

    public void startServer(){
        try {
            callHandler.registerGlobal(IServerRMI.class, this);
            server.bind(7777, callHandler);
            server.addServerListener(new IServerListener() {

                @Override
                public void clientDisconnected(Socket socket) {
                    System.out.println("[SERVER] - Conexion TERMINADA con: [" + socket.getInetAddress()+"]");
                    for (int i = 0; i < 5; i++) {
                        System.out.println();
                    }
                }

                @Override
                public void clientConnected(Socket socket) {
                    System.out.println("[SERVER] - Conexion ESTABLECIDA con: [" + socket.getInetAddress()+"]");
                }
            });
            System.out.println("[SERVER] - Servidor aceptando conexiones...");
        } catch (LipeRMIException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer(){
        try{
            server.close();
            System.out.println("[SERVER] - Servidor desconectado correctamente!");
        }catch (Exception e){
            System.out.println("[SERVER] - ERROR al desconectar el servidor...");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getResponse(short originLang) {

        ArrayList<String> arrayCourses = new ArrayList<>();
        arrayCourses.add("Hola!");
        System.out.println("SUCCESS - [getResponse()]");
        return arrayCourses;
    }
}
