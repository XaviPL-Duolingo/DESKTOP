package com.duolingo.app.util;

import com.duolingo.app.interfaces.impl.*;
import com.duolingo.app.model.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server extends Thread{

    int option;
    static ServerSocket serverSocket;

    public Server(int option){
        this.option = option;
    }

    @Override
    public void run(){

        switch (option){
            case 1:
                try {
                    startServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 0:
                try {
                    stopServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    public void startServer() throws IOException {

        serverSocket = new ServerSocket(25012);
        System.out.println("[SERVER] - Servidor connectado correctamente!");

         while (true) {
            Socket socket = null;
            try {

                socket = serverSocket.accept();
                System.out.println("[SERVER] - Nueva conexión con: [" + socket.getInetAddress() + "]");

                DataInputStream is = new DataInputStream(socket.getInputStream());
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

                System.out.println("[SERVER] - Creando nuevo hilo...");
                Thread threadConn = new ClientHandler(socket, is, os);
                threadConn.start();

            } catch (Exception e) {
                System.out.println("[SERVER] - Error al recibir connexion...");
                socket.close();
            }
         }
    }

    public void stopServer() throws IOException {

        serverSocket.close();
        System.out.println("[SERVER] - Servidor desconectado correctamente!");

    }
}

class ClientHandler extends Thread{

    final Socket socket;
    final DataInputStream is;
    final ObjectOutputStream os;

    public ClientHandler(Socket socket, DataInputStream is, ObjectOutputStream os){
        this.socket = socket;
        this.is = is;
        this.os = os;
    }

    @Override
    public void run() {

        String clientReceived;
        UserImpl userManager = new UserImpl();

        while (true) {
            try {
                clientReceived = is.readUTF();
                switch (clientReceived) {
                    case "getAllLanguages":
                        LanguageImpl languageManager = new LanguageImpl();
                        List<Language> languageList = languageManager.getAllLanguages();
                        os.writeObject(languageList);
                        System.out.println("[SERVER] - SUCCESS: getAllLanguages()");
                        break;

                    case "getAllCoursesByID":
                        CourseImpl courseManager = new CourseImpl();
                        int idOriginLang = is.readInt();
                        List<Course> courseList = courseManager.getAllCoursesByID(idOriginLang, 0);
                        os.writeObject(courseList);
                        System.out.println("[SERVER] - SUCCESS: getAllCoursesByID()");
                        break;

                    case "getAllCategoriesByID":
                        CategoryImpl categoryManager = new CategoryImpl();
                        int idCourse = is.readInt();
                        List<Category> categoryList = categoryManager.getAllCategoriesByID(idCourse);
                        os.writeObject(categoryList);
                        System.out.println("[SERVER] - SUCCESS: getAllCategoriesByID()");
                        break;

                    case "loginUser":
                        boolean success = userManager.loginUser(is.readUTF(), is.readUTF());
                        os.writeObject(success);
                        System.out.println("[SERVER] - SUCCESS: loginUser()");
                        break;

                    case "registerUser":
                        boolean success2 = userManager.registerUser(is.readUTF(), is.readUTF(), is.readUTF(), is.readInt());
                        os.writeObject(success2);
                        System.out.println("[SERVER] - SUCCESS: registerUser()");
                        break;

                    case "getUserData":
                        User dataUser = userManager.getUserData(is.readUTF());
                        os.writeObject(dataUser);
                        System.out.println("[SERVER] - SUCCESS: getUserData()");
                        break;

                    case "getRanking":
                        List<User> rankingList = userManager.getRanking(is.readInt());
                        os.writeObject(rankingList);
                        System.out.println("[SERVER] - SUCCESS: getRanking()");
                        break;

                    case "getItems":
                        ItemImpl itemManager = new ItemImpl();
                        List<Item> itemList = itemManager.getAllItems();
                        os.writeObject(itemList);
                        System.out.println("[SERVER] - SUCCESS: getItems()");
                        break;

                    default:
                        System.out.println("[SERVER] - Peticíon erronea...");
                        break;
                }

                System.out.println("[SERVER] - Terminada la conexión con: [" + socket.getInetAddress() + "]\n");
                break;

            } catch (IOException e) {
                System.out.println("[SERVER] - Error al completar la petición...");
                e.printStackTrace();
            }
        }

        try {
            this.socket.close();
            this.is.close();
            this.os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}