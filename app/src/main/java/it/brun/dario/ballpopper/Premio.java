package it.brun.dario.ballpopper;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.graphics.Canvas;
import android.graphics.Paint;

import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU1;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU10;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU2;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU3;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU4;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU5;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU6;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU7;
import it.brun.dario.ballpopper.dialogs.FinestraDialogoGettatrice;

/**
 * Created by dario on 23/05/17.
 */

public class Premio
{

    public static Premio generaPremio(Activity act)
    {
        double d=Math.random();
        if(d<0.5)
        {

            int points=500+(int)((((double)Giocatore.getNumLivelliCompletati())/((double)Giocatore.getNumLivelli()))*1000);
            return new Premio(act,points);
        }
        else
        {
            return new Premio(act,getBonusPremio());
        }
    }

    public static Bonus getBonusPremio()
    {
        double k=((double)Giocatore.getNumLivelliCompletati())/((double)Giocatore.getNumLivelli());
        if(k<0.15)
        {
            return new BonusPIU1();
        }
        else if(k<0.30)
        {
            return new BonusPIU2();
        }
        else if(k<0.45)
        {
            return new BonusPIU3();
        }
        else if(k<0.6)
        {
            double d=Math.random();
            if(d<0.5)
            {
                return new BonusPIU4();
            }
            else
            {
                return new BonusPIU5();
            }
        }
        else if(k<0.85)
        {
            double d=Math.random();
            if(d<0.6)
            {
                return new BonusPIU4();
            }
            else
            {
                return new BonusPIU6();
            }
        }
        else if(k<1)
        {
            double d=Math.random();
            if(d<0.6)
            {
                return new BonusPIU5();
            }
            else
            {
                return new BonusPIU6();
            }
        }
        else
        {
            double d=Math.random();
            if(d<0.6)
            {
                return new BonusPIU5();
            }
            else if(d<0.9)
            {
                return new BonusPIU7();
            }
            else
            {
                return new BonusPIU10();
            }
        }

    }

    private Activity activity;
    private int punti;
    private Bonus bonus;

    public Premio(Activity a,Bonus b)
    {
        punti=0;
        bonus=b;
        activity=a;
    }

    public Premio(Activity a,int p)
    {
        punti=p;
        activity=a;
    }

    public void disegnaPremio(Canvas c, Paint p, float x,float y)
    {
        if(bonus==null)
        {
            c.drawText(punti+" "+activity.getResources().getString(R.string.ipunti),x,y,p);

        }
        else
        {
            bonus.disegnaBonusBig(c,p,x,y-Bonus.getDIAMETROBASE());
        }
    }
    public void consumaPremio()
    {
        if(bonus==null)
        {
            Giocatore.inventario().addPunti(punti);
        }
        else
        {
            Giocatore.inventario().addPotenziamento(bonus);
            if(Giocatore.inventario().numPotenziamenti()>Giocatore.inventario().getMaxPotenziamenti())
            {
                mostraFinestraGettatrice();
            }
        }
    }


    private void mostraFinestraGettatrice()
    {
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        /*Fragment prev = activity.getFragmentManager().findFragmentByTag("dialog");
        if (prev != null)
        {
            ft.remove(prev);
        }

        ft.addToBackStack(null);

        */
        FinestraDialogoGettatrice finestra = new FinestraDialogoGettatrice();
        finestra.show(ft, "dialog");

    }
}
