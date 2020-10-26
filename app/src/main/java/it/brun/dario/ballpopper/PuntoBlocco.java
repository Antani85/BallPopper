package it.brun.dario.ballpopper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 23/04/17.
 */
public class PuntoBlocco extends Punto
{
    public int setContenuto(Personaggio p)
    {
       int k=super.setContenuto(p);
       if(k>0)
       {
           return k;
       }
       else if(sbloccato)
       {
           return 0;
       }
       else
       {
           return -1;
       }

    }
    private boolean sbloccato;
    public PuntoBlocco(int xx,int yy)
    {
        super(xx,yy);
        sbloccato=false;
    }
    public PuntoBlocco(int xx,int yy, FloatValore f, int m)
    {
        super(xx,yy,f,m);
        sbloccato=false;
    }
    public PuntoBlocco (int xx, int yy,FloatValore f,int m,Livello lev)
    {
        super(xx,yy,f,m,lev);
        sbloccato=false;

    }
    public void sblocca()
    {
        sbloccato=true;
    }
    public boolean getSbloccato()
    {
        return sbloccato;
    }
    public Drawable getImmagine()
    {
        if(!sbloccato)
        {
            return new Drawable()
            {
                @Override
                public void draw(Canvas canvas)
                {
                    Paint p=new Paint();
                    p.setColor(Color.BLACK);
                    p.setStrokeWidth(Bomba.getDIAMETROBASE()/4);
                    canvas.drawLine(getX()-Bomba.getDIAMETROBASE(),getY()-Bomba.getDIAMETROBASE()/2,getX(),getY()+Bomba.getDIAMETROBASE()/2,p);
                    canvas.drawLine(getX()-Bomba.getDIAMETROBASE(),getY()+Bomba.getDIAMETROBASE()/2,getX(),getY()-Bomba.getDIAMETROBASE()/2,p);
                }

                @Override
                public void setAlpha(int alpha)
                {

                }

                @Override
                public void setColorFilter(ColorFilter cf)
                {

                }

                @Override
                public int getOpacity()
                {
                    return 0;
                }
            };
        }
        else
        {
            return null;
        }
    }
}
