package it.brun.dario.ballpopper.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.interfaces.Contenitore;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.Risultato;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.SbloccaTrofeo;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.trophies.Trofeo;

/**
 * Created by dario on 21/04/17.
 */
public class PannelloFineLivello extends View implements Contenitore
{

    private double ht;
    private double margin_left;
    private double margin_top;
    private double top;
    private double cc;

    private List<SbloccaTrofeo> trofei;
    public void invalida()
    {
        postInvalidate();
    }

    public void rimuovi(SbloccaTrofeo b)
    {
        synchronized(trofei)
        {
            trofei.remove(b);

        }

    }


    private Risultato risultato;
    private void assegnaPosizioni()
    {
        ht=0.18204;
        top=0.35;
        margin_left=0.05112;
        margin_top=0.04248;
        cc=0.83844;
    }
    private Activity chiamante;
    private static Bitmap immagine;
    public static void assegnaImmagine(Activity act)
    {
        immagine=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.livellocompletato), Ambiente.getHEIGHT(),Ambiente.getWIDTH());
    }


    private void creaSbloccaTrofei()
    {
        List<Trofeo> t=Giocatore.controllaTrofei(null,Giocatore.getLivello(risultato.getLivello()-1));
        if(t!=null)
        {
            for(int i=0;i<t.size();i++)
            {
                SbloccaTrofeo panne = new SbloccaTrofeo(this, Ambiente.getWIDTH() - Bomba.getDIAMETROBASE() * 6, 0, t.get(i));
                trofei.add(panne);
                panne.start();
                Ambiente.suonaDingo();
            }
        }
    }

    public PannelloFineLivello(Context context,Risultato res)
    {
        super(context);
        chiamante=(Activity)context;
        risultato=res;
        trofei=new ArrayList<SbloccaTrofeo>();


        assegnaPosizioni();
        creaSbloccaTrofei();

    }
    @Override
    public void onDraw(Canvas c)
    {
        Paint p=new Paint();

        Giocatore.setTypeface(p);
        float textsize=Bomba.getDIAMETROBASE();
        p.setTextSize((textsize*3)/2);
        p.setColor(Color.BLACK);
        c.drawBitmap(immagine,0,0,p);
        String lev=chiamante.getResources().getString(R.string.livello_completato);
        float lunghezza=p.measureText(lev);
        c.drawText(lev,(int)(Ambiente.getWIDTH()/2-lunghezza/2),(int)(ht*Ambiente.getHEIGHT()+(textsize*3)/4),p);
        String numLev=""+risultato.getLivello();
        lunghezza=p.measureText(numLev);
        c.drawText(numLev,(int)(cc*Ambiente.getWIDTH()-lunghezza/2),(int)(ht*Ambiente.getHEIGHT()+(textsize*3)/4),p);
        p.setTextSize(textsize);
        int xx=(int)(margin_left*Ambiente.getWIDTH());
        int yy=(int)(top*Ambiente.getHEIGHT()+textsize);
        c.drawText(chiamante.getResources().getString(R.string.punti_ottenuti)+": "+risultato.getPunti(),xx,yy,p);
        yy+=(int)(margin_top*Ambiente.getHEIGHT()+textsize);
        String bonus=chiamante.getResources().getString(R.string.bonus_ottenuti)+": ";
        int numBonus=risultato.getNumBonus();
        if(numBonus==0)
        {
            bonus=bonus+chiamante.getResources().getString(R.string.nessuno);
            c.drawText(bonus,xx,yy,p);
        }

        else
        {
            c.drawText(bonus,xx,yy,p);
            float hkh=xx+p.measureText(bonus);
            for(int i=0;i<numBonus;i++)
            {
                risultato.getBonus(i).disegnaBonus(c,p,hkh,yy-Bomba.getDIAMETROBASE());
                hkh+=2*Bomba.getDIAMETROBASE();
            }
            p.setTextSize(textsize);
            p.setColor(Color.BLACK);

        }


        yy+=(int)(margin_top*Ambiente.getHEIGHT()+textsize);
        c.drawText(chiamante.getResources().getString(R.string.palle_scoppiate)+": "+risultato.getPalle(),xx,yy,p);
        yy+=(int)(margin_top*Ambiente.getHEIGHT()+textsize);
        c.drawText(chiamante.getResources().getString(R.string.proiettili_sparati)+": "+risultato.getProiettili(),xx,yy,p);
        yy+=(int)(margin_top*Ambiente.getHEIGHT()+textsize);
        c.drawText(chiamante.getResources().getString(R.string.precisione)+": "+risultato.getStringPrecisione(),xx,yy,p);
        yy+=(int)(margin_top*Ambiente.getHEIGHT()+textsize);
        c.drawText(chiamante.getResources().getString(R.string.punti_livello)+": "+(int)(risultato.getPuntiLivello()*(risultato.getTime()/1000)),xx,yy,p);

        for(int i=0;i<trofei.size();i++)
        {
            trofei.get(i).disegnaSbloccaTrofeo(c);
        }


    }
}

