package lab12;


import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        Class cls = null;
        try {
            cls = Class.forName("lab12.Testing");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        File f = new File("C:\\Users\\40757\\Desktop\\Sapt12\\Laboratorul 6\\build\\classes\\lab6\\Lab6.class");
        URL urls[] = new URL[1];
        urls[0]= f.toURI().toURL();
        ClassLoader cl = new URLClassLoader(urls);
        cl.loadClass("lab12.Testing");


        for (Method m : cls.getMethods()) {
            System.out.println(m.getName());
        }

    }
}