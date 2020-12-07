/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telemedicine_ambulance_project_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class Server_Hospital_Thread_Object implements Runnable {

    Socket socket;
    Client patient;

    public Server_Hospital_Thread_Object(Socket socket) {
        this.socket = socket;
    }

    public Server_Hospital_Thread_Object(Client patient) {
        this.patient = patient;
    }

    public Server_Hospital_Thread_Object(Socket socket, Client patient) {
        this.socket = socket;
        this.patient = patient;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object tmp;
            tmp = objectInputStream.readObject();
            Patient patient = (Patient) tmp;
            System.out.println(patient.toString());
            releaseObjectInputStream(objectInputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Server_Hospital_Thread_Object.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static void releaseObjectInputStream(ObjectInputStream o) {

        try {
            o.close();
        } catch (IOException ex) {
            Logger.getLogger(Server_Hospital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
