package com.duolingo.app.util;

import com.duolingo.app.interfaces.impl.CategoryImpl;
import com.duolingo.app.interfaces.impl.CourseImpl;
import com.duolingo.app.interfaces.impl.LanguageImpl;
import com.duolingo.app.model.Category;
import com.duolingo.app.model.Course;
import com.duolingo.app.model.Language;

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

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());

                System.out.println("[SERVER] - Creando nuevo hilo...");
                Thread threadConn = new ClientHandler(socket, dis, os);
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
    final DataInputStream dis;
    final ObjectOutputStream os;

    public ClientHandler(Socket socket, DataInputStream dis, ObjectOutputStream os){
        this.socket = socket;
        this.dis = dis;
        this.os = os;
    }

    @Override
    public void run() {

        String clientReceived;

        while (true) {
            try {
                // os.writeUTF("[SERVER] - What do you want?");
                clientReceived = dis.readUTF();

                switch (clientReceived) {
                    case "getAllLanguages":
                        LanguageImpl languageManager = new LanguageImpl();
                        List<Language> languageList = languageManager.getAllLanguages();
                        os.writeObject(languageList);
                        System.out.println("[SERVER] - SUCCESS: getAllLanguages()");
                        break;

                    case "getAllCoursesByID":
                        CourseImpl courseManager = new CourseImpl();
                        int idOriginLang = dis.readInt();
                        List<Course> courseList = courseManager.getAllCoursesByID(idOriginLang, 0);
                        os.writeObject(courseList);
                        System.out.println("[SERVER] - SUCCESS: getAllCoursesByID()");
                        break;

                    case "getAllCategoriesByID":
                        CategoryImpl categoryManager = new CategoryImpl();
                        int idCourse = dis.readInt();
                        List<Category> categoryList = categoryManager.getAllCategoriesByID(idCourse);
                        os.writeObject(categoryList);
                        System.out.println("[SERVER] - SUCCESS: getAllCategoriesByID()");
                        break;

                    default:
                        System.out.println("[SERVER] - Peticíon erronea...");
                        break;
                }

                System.out.println("[SERVER] - Terminada la conexión con: [" + socket.getInetAddress() + "]");
                break;

            } catch (IOException e) {
                System.out.println("[SERVER] - Error al completar la petición...");
                e.printStackTrace();
            }
        }

        try {
            this.socket.close();
            this.dis.close();
            this.os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}