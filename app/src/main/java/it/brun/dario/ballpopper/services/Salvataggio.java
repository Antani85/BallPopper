package it.brun.dario.ballpopper.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.Informazioni;

public class Salvataggio extends Service implements  Runnable
{
    private File file;
    private Informazioni info;

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void run()
    {
        FileWriter fw = null;
        PrintWriter bw = null;
        try
        {
            fw = new FileWriter(file, false);
            bw = new PrintWriter(fw);
            Giocatore.inventario().salva(bw,info);

            bw.close();
            fw.close();
        }
        catch (Exception e)
        {
            Log.d("errore ", e.toString());
        }
    }


    public int onStartCommand(Intent intent, int flags, int startId)
    {


        file=(File)intent.getSerializableExtra(Giocatore.URI);
        info=intent.getParcelableExtra(Giocatore.INFORMAZIONI);
        new Thread(this).start();
        return Service.START_NOT_STICKY;
    }


}