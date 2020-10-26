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
 * Created by dario on 29/04/17.
 */
public class BombaSpinata extends Bomba
{
    private float angolo;
    private static Bitmap[] immagini;
    public static void setImmagine(Activity act)
    {
        immagini=new Bitmap[24];

        immagini[0]=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallaspinata), (int)(Bomba.getDIAMETROBASE()*4), (int)(Bomba.getDIAMETROBASE()*4));
        for(int i=23;i>0;i--)
        {
            immagini[i] = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.pallaspinata), (int) (Bomba.getDIAMETROBASE() * 4-Bomba.getDIAMETROBASE()*(24-i)/6), (int) (Bomba.getDIAMETROBASE() * 4-Bomba.getDIAMETROBASE()*(24-i)/6));
        }




    }

    @Override
    public void incrementaInventario()
    {
        Giocatore.inventario().incrementaSpinateScoppiate();
    }
    private static final int ANGOLOBASE=(int)(Bomba.PROPFPS*18);
    public BombaSpinata(Ambiente a, float ix, float iy, int c, float xx, float yy)
    {
        super(a,ix,iy,c,xx,yy);
        setPunti(200);
        impostaHp(50);
        setDiametro(Bomba.getDIAMETROBASE()*4);
        i=0;
        angolo=0;



    }

    private void aumentaAngolo()
    {
        angolo+=ANGOLOBASE;
        if(angolo>360)
        {
            angolo=angolo-360;
        }
    }
    public BombaSpinata(Ambiente a,float ix,float iy, int c,float xx,float yy,Bonus bon)
    {
        super(a,ix,iy,c,xx,yy,bon);
        impostaHp(50);
        setPunti(200);
        setDiametro(Bomba.getDIAMETROBASE()*4);
        i=0;
        angolo=0;


    }

    public  Bomba getBomba (Ambiente a,float ix,float iy, int c,float xx,float yy)
    {
        return new BombaSpinata(a,ix,iy,c,xx,yy,getBonus());

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
