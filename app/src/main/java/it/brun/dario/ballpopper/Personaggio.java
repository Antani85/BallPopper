package it.brun.dario.ballpopper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.views.AmbienteLivelli;

/**
 * Created by dario on 23/04/17.
 */
public class Personaggio implements Runnable
{
    public int getPartialX()
    {
        return x;
    }
    public int getPartialY()
    {
        return y;
    }
    public boolean inMoto()
    {
        return corrente!=null;
    }
    private FloatValore pos;
    public static final float PROPORZIONE=1.225f;
    private Punto contenitore;
    public void setContenitore(Punto p)
    {
        if(contenitore!=null)
        {
            contenitore.resetta();
            contenente.setVisibilityLivello(false);

        }
        contenitore=p;
        contenente.caricaSuono(contenitore.getZona());
        if(p!=null&&p.getLivello()!=null)
        {
            contenente.setVisibilityLivello(true);
        }
    }
    public Punto getContenitore()
    {
        return contenitore;
    }
    private Thread corrente;
    private AmbienteLivelli contenente;
    private static final int LIVELLO=10;
    private int x;
    private int y;
    private List<Punto> destinazione;
    private static Bitmap[] immagini;
    private static float ALTEZZA;

    public static float getALTEZZA()
    {
        return ALTEZZA;
    }
    public static void assegnaImmagini(Activity act)
    {
        ALTEZZA=Bomba.getDIAMETROBASE()*4;
        immagini=new Bitmap[8];
        immagini[0]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.personaggio0),(int)(ALTEZZA*PROPORZIONE),(int)ALTEZZA );
        immagini[1]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.personaggio1), (int)(ALTEZZA*PROPORZIONE),(int)ALTEZZA);
        immagini[2]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.personaggio2), (int)(ALTEZZA*PROPORZIONE),(int)ALTEZZA);
        immagini[3]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.personaggio3), (int)(ALTEZZA*PROPORZIONE),(int)ALTEZZA);
        immagini[4]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.personaggio4), (int)(ALTEZZA*PROPORZIONE),(int)ALTEZZA);
        immagini[5]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.personaggio5), (int)(ALTEZZA*PROPORZIONE),(int)ALTEZZA);
        immagini[6]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.personaggio6), (int)(ALTEZZA*PROPORZIONE),(int)ALTEZZA);
        immagini[7]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.personaggio7), (int)(ALTEZZA*PROPORZIONE),(int)ALTEZZA);



    }

    public int getDirection()
    {
        return direction;
    }
    private int direction;
    private int immagineCorrente;
    private int contatore;
    private void assegnaImmagineCorrente()
    {

        if(contatore%10==0)
        {
            immagineCorrente = (immagineCorrente + 1) % 4 + 4 * direction;
        }

    }


    public Personaggio(AmbienteLivelli c,int xx,int yy,FloatValore f,int dir)
    {
        contenente=c;
        x=xx;
        y=yy;
        pos=f;

        destinazione=null;
        corrente=null;
        contenitore=null;
        direction=dir;
        contatore=0;
        assegnaImmagineCorrente();

    }
    public void setDirection(Punto dest)
    {
        if(getX()<dest.getX())
        {
            direction=0;
        }
        else
        {
            direction=1;
        }
    }
    public boolean setDestinazione(List<Punto> d)
    {
        if(corrente==null)
        {
            destinazione=d;
            corrente=new Thread(this);
            corrente.start();
            return true;

        }
        return false;

    }
    public Personaggio(AmbienteLivelli c,Punto p,int dir)
    {
        this(c,p.getPartialX(),p.getPartialY(),p.getPos(),dir);
        contenitore=p;
    }
    public void disegnaPersonaggio(Canvas c)
    {
        c.drawBitmap(immagini[immagineCorrente],getX()-ALTEZZA/2,getY()-ALTEZZA,null);


    }
    public int getX()
    {
        return  x+(int)pos.getValore();

    }
    public int getY()
    {
        return y;
    }

    public Punto getPosizione()
    {

        return new Punto(x,y,pos,0);
    }
    public void run()
    {
        int i=0;


        contatore=0;
        try
        {
            for(i=0;i<destinazione.size();i++)
            {
                setDirection(destinazione.get(i));
                while (getX() != destinazione.get(i).getX() || getY() != destinazione.get(i).getY())
                {
                    contatore++;
                    if (getX() < destinazione.get(i).getX())
                    {
                        x += Bomba.getINCREMENTATORE_RAGGIO()/4;

                        if (getX() > destinazione.get(i).getX())
                        {
                            x = destinazione.get(i).getPartialX();
                        }
                    }
                    else if (getX() > destinazione.get(i).getX())
                    {

                        x -= Bomba.getINCREMENTATORE_RAGGIO()/4;
                        if (getX() < destinazione.get(i).getX())
                        {
                            x = destinazione.get(i).getPartialX();
                        }
                    }
                    if (getY() < destinazione.get(i).getY())
                    {
                        y += Bomba.getINCREMENTATORE_RAGGIO()/4;
                        if (getY() > destinazione.get(i).getY())
                        {
                            y = destinazione.get(i).getPartialY();
                        }
                    }
                    else if (getY() > destinazione.get(i).getY())
                    {
                        y -= Bomba.getINCREMENTATORE_RAGGIO()/4;
                        if (getY() < destinazione.get(i).getY())
                        {
                            y = destinazione.get(i).getPartialY();
                        }
                    }
                    if(destinazione.get(i).setContenuto(this)<0)
                    {
                        throw new InterruptedException();
                    }


                    assegnaImmagineCorrente();

                    contenente.postInvalidate();

                    Thread.sleep(LIVELLO);


                }
            }
            corrente=null;
        }
        catch (Exception e)
        {
            corrente=null;
            comeback(i);

            return;
        }
    }

    private void comeback(int i)
    {
        contenente.suonaErrore();
        Punto p1=destinazione.get(i);
        Punto p2=destinazione.get(i-1);
        List<Punto> lista=new ArrayList<Punto>();
        lista.add(p1);
        lista.add(p2);
        setDestinazione(lista);

    }
}
