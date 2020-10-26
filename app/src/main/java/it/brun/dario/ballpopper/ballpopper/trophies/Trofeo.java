package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Calendar;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.Tempo;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 20/04/17.
 */
public abstract class Trofeo
{
    public static final String CODIFICA_INIZIALE="AHNICO";
    public static final String CODIFICA_FINALE="AHFICO";

    private String CODIFICA_TROFEO;
    public String getCODIFICA()
    {
        return CODIFICA_TROFEO;
    }
    protected void setCODIFICA_TROFEO(String c)
    {
        CODIFICA_TROFEO=c;
    }
    private Tempo tempo;
    private String nome;
    private String descrizione;
    private static Bitmap immagine0;
    private static Bitmap immagine1;
    public static void assegnaImmagine(Activity act)
    {
        immagine1=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.trofeo1), (int)Bomba.getDIAMETROBASE()*4, (int)Bomba.getDIAMETROBASE()*4);
        immagine0=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.trofeo0), (int)Bomba.getDIAMETROBASE()*4, (int)Bomba.getDIAMETROBASE()*4);

    }

    public Tempo getTempo()
    {
        return tempo;
    }
    public String getNome()
    {
        return nome;
    }

    public String getDescrizione()
    {
        return descrizione;
    }
    private boolean sbloccato;
    public void sblocca(Tempo t)
    {
        sbloccato=true;
        tempo=t;
    }
    public void sblocca()
    {
        tempo= new Tempo(Calendar.getInstance());
        sbloccato=true;
    }
    public boolean getSbloccato()
    {
        return sbloccato;
    }
    public abstract boolean condizione(Palla palla, Livello lev);

    public Bitmap immagineTrofeo()
    {
        if(sbloccato)
        {
            return immagine1;
        }
        else
        {
            return immagine0;
        }
    }


    public Bitmap getImmagineBlocco()
    {
        int diametroB=(int)Bomba.getDIAMETROBASE()*4;
        int diametro=(diametroB*8)/10;
        float cc=diametroB/10;
        Bitmap bitmap=Bitmap.createBitmap(diametroB,diametroB,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        Paint p=new Paint();
        p.setColor(Color.argb(0, 0, 0, 0));
        canvas.drawRect(new RectF(0, 0, diametroB, diametroB), p);
        p.setColor(Color.WHITE);
        canvas.drawOval(new RectF(cc, cc, cc + diametro, cc + diametro), p);
        p.setColor(Color.RED);
        p.setStrokeWidth(diametro / 10);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawOval(new RectF(cc,cc,cc+diametro,cc+diametro),p);
        double x1=diametro/2+diametro*(Math.sqrt(2)/4);
        double x2=diametro/2-diametro*(Math.sqrt(2)/4);

        canvas.drawLine(cc+(float)x1,cc+(float)x1,cc+(float)x2,cc+(float)x2,p);
        return bitmap;

    }

    public Trofeo(String n, String d)
    {
        nome=n;
        descrizione=d;

        sbloccato=false;
        tempo=null;
    }
}
