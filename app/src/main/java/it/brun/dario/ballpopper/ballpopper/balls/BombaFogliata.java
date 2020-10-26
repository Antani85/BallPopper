package it.brun.dario.ballpopper.ballpopper.balls;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 30/04/17.
 */
public class BombaFogliata extends Bomba
{
    private float angolo;
    private static Bitmap[] immagini;
    public static void setImmagine(Activity act)
    {
        immagini=new Bitmap[24];
        immagini[0]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallafoglie), (int)(Bomba.getDIAMETROBASE()*2.5f), (int)(Bomba.getDIAMETROBASE()*2.5f));

        for(int i=23;i>0;i--)
        {
            immagini[i]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallafoglie), (int)(Bomba.getDIAMETROBASE()*2.5f-Bomba.getDIAMETROBASE()*(24-i)*2.5/24), (int)(Bomba.getDIAMETROBASE()*2.5f-Bomba.getDIAMETROBASE()*(24-i)*2.5/24));
        }



    }
    private static final int ANGOLOBASE=(int)(Bomba.PROPFPS*18);
    public BombaFogliata(Ambiente a, float ix, float iy, int c, float xx, float yy)
    {
        super(a,ix,iy,c,xx,yy);
        setPunti(100);
        impostaHp(35);
        setDiametro(Bomba.getDIAMETROBASE()*2.5f);
        i=0;
        angolo=0;



    }

    @Override
    public void incrementaInventario()
    {
        Giocatore.inventario().incrementaFogliateScoppiate();
    }
    private void aumentaAngolo()
    {
        angolo+=ANGOLOBASE;
        if(angolo>360)
        {
            angolo=angolo-360;
        }
    }
    public BombaFogliata(Ambiente a,float ix,float iy, int c,float xx,float yy,Bonus bon)
    {
        super(a,ix,iy,c,xx,yy,bon);
        impostaHp(35);
        setPunti(100);
        setDiametro(Bomba.getDIAMETROBASE()*2.5f);
        i=0;
        angolo=0;


    }

    public  Bomba getBomba (Ambiente a,float ix,float iy, int c,float xx,float yy)
    {
        return new BombaFogliata(a,ix,iy,c,xx,yy,getBonus());

    }

    private int i;



    public void muovi()
    {
        super.muovi();
        if(getRaggio()!=0)
        {
            i++;
            if(i>=immagini.length)
            {
                i=immagini.length-1;
            }
        }
        aumentaAngolo();
    }

    public void disegnaBomba(Canvas g, Paint p)
    {

        if (getRaggio() == 0)
        {
            setCentro();


            g.save();

            g.rotate(angolo,getCentroX(),getCentroY());

            g.drawBitmap(immagini[i],getX(),getY(),p);
            g.restore();
            disegnaBarraHp(g,p);




        }
        else if (getRaggio() <= getDiametro())
        {


            float x1=getCentroX()-getRaggio()/2;
            float y1=getCentroY()-getRaggio()/2;
            g.drawBitmap(immagini[i],x1,y1,p);




        }


    }



}
