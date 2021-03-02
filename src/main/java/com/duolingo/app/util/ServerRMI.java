package com.duolingo.app.util;

import com.duolingo.app.interfaces.impl.CategoryImpl;
import com.duolingo.app.interfaces.impl.CourseImpl;
import com.duolingo.app.interfaces.impl.LanguageImpl;
import com.duolingo.app.model.Category;
import com.duolingo.app.model.Course;
import com.duolingo.app.model.Language;
import net.sf.lipermi.exception.LipeRMIException;
import net.sf.lipermi.handler.CallHandler;
import net.sf.lipermi.net.IServerListener;
import net.sf.lipermi.net.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
    public List<Course> getAllCoursesByID(int idOriginLang) {

        List<Course> listCourses = null;

        try {
            CourseImpl courseManager = new CourseImpl();
            listCourses = courseManager.getAllCoursesByID(idOriginLang, 0);
            System.out.println("[SERVER] - SUCCESS: getAllCoursesByID()");
        }catch (Exception e){
            System.out.println("[SERVER] - ERROR: getAllCoursesByID()");
        }
        return listCourses;


    }

    @Override
    public List<Language> getAllLanguages() {

        List<Language> listLanguages = null;

        try {
            LanguageImpl languageManager = new LanguageImpl();
            listLanguages = languageManager.getAllLanguages();
            System.out.println("[SERVER] - SUCCESS: getAllLanguages()");
        }catch (Exception e){
            System.out.println("[SERVER] - ERROR: getAllLanguages()");
        }

        return listLanguages;
    }

    @Override
    public List<Category> getAllCategoriesByID(int idCourse) {

        System.out.println("course: " + idCourse);
        List<Category> listCategories = null;

        try {
            CategoryImpl categoryManager = new CategoryImpl();
            listCategories = categoryManager.getAllCategoriesByID(idCourse);
            System.out.println("[SERVER] - SUCCESS: getAllCategoriesByID()");
        }catch (Exception e){
            System.out.println("[SERVER] - ERROR: getAllCategoriesByID()");
        }

        return listCategories;
    }
}
