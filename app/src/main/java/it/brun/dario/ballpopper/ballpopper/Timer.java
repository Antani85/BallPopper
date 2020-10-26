package it.brun.dario.ballpopper.ballpopper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.Riproduttore;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 04/05/17.
 */


public class Timer extends Thread
{
    private static Bitmap immagine;
    private static Bitmap lancetta;

    private static Riproduttore timeup;
    private static Riproduttore drin;

    public static void assegnaImmagine(Activity act)
    {
        immagine=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.timer),(int)(Ambiente.getWIDTH()*LARGHEZZA*PROPORZIONE),(int)(Ambiente.getWIDTH()*LARGHEZZA));
        lancetta=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.lancetta),(int)(Ambiente.getWIDTH()*LARGHEZZA*0.5f),(int)(Ambiente.getWIDTH()*LARGHEZZA*0.5f*PROPORZIONELANCETTA));
        timeup=new Riproduttore(act,R.raw.timeup);
        drin=new Riproduttore(act,R.raw.perdita);
    }


    public static final float LARGHEZZA=0.1f;
    public static final float PROPORZIONE=1.5f;
    private static final float PROPORZIONELANCETTA=0.20106f;
    private static final float XCENTRO=0.5f;
    private static final float YCENTRO=0.66667f;
    public static final float BORDO=0.025f;

    public boolean isTerminato()
    {
        return stoppa;
    }
    private boolean stoppa;
    private Ambiente contenente;
    private static final long SECONDO=100l;
    private static final long TEMPOCRITICO=10000l;
    private long tempoTotale;
    private long tempoRimanente;
    private float x;
    private float y;
    private double angolo;
    private float cx;
    private float cy;
    private float raggio;
    private float raggiodue;

    private float diametrolancetta;
    public Timer(Ambiente a,long time)
    {
        contenente=a;
        tempoTotale=time;
        tempoRimanente=time;
        angolo=((double)TEMPOCRITICO/(double)tempoTotale)*360;

        x=Ambiente.getWIDTH()*(1-LARGHEZZA);
        y=0;
        cx=x+Ambiente.getWIDTH()*LARGHEZZA*XCENTRO;
        cy=y+Ambiente.getWIDTH()*LARGHEZZA*PROPORZIONE*YCENTRO;
        raggio=Ambiente.getWIDTH()*LARGHEZZA*XCENTRO;
        diametrolancetta=Ambiente.getWIDTH()*LARGHEZZA*0.5f*PROPORZIONELANCETTA;
        stoppa=false;
        raggiodue=raggio-BORDO*Ambiente.getWIDTH()*LARGHEZZA;

    }
    public void stoppa()
    {
        stoppa=true;
    }
    public void run()
    {
        try
        {
            while (tempoRimanente > 0&&!stoppa)
            {

                tempoRimanente-=100;
                if(tempoRimanente==TEMPOCRITICO)
                {
                    timeup.play();
                }

                Thread.sleep(SECONDO);
            }
        }
        catch (InterruptedException e)
        {

        }
        if(!stoppa)
        {
            contenente.termina();
        }
    }
    public static void suonaPerdita()
    {
        drin.play();
    }
    public static void stoppaPerdita()
    {
        drin.pausa();
    }

    public long getTempoRimanente()
    {
        return tempoRimanente;
    }
    public void setTempoRimanente(long t)
    {
        if(t>=0&&t<=tempoTotale)
        {
            tempoRimanente = t;
        }

    }
    public int getMinutiRimasti()
    {
        return (int)tempoRimanente/60000;
    }
    public int getSecondiRimasti()
    {
        return (int)(tempoRimanente/1000)%60;
    }

    private double calcolaAngolo()
    {
        return -1*((double)(tempoTotale-tempoRimanente)/(double)tempoTotale)*360;
    }

    public void disegnaTimer(Canvas g,Paint p)
    {
        g.drawBitmap(immagine, x, y, p);
        p.setShader(null);
        p.setColor(Color.RED);

        g.drawArc(new RectF(cx-raggiodue,cy-raggiodue,cx+raggiodue,cy+raggiodue),270,(float)angolo,true,p);

        g.save();
        g.rotate((float)calcolaAngolo(),cx,cy);
        g.drawBitmap(lancetta,cx-diametrolancetta/2,cy-raggio,p);
        g.restore();




    }
}

