package it.brun.dario.ballpopper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.levels.Livello;
import it.brun.dario.ballpopper.views.Ambiente;
import it.brun.dario.ballpopper.views.AmbienteLivelli;

/**
 * Created by dario on 23/04/17.
 */
public class Punto
{

    private static final float SPUNTAPROPORZIONE=0.57879f;
    private static final float LARGHEZZASPUNTA=0.085f;
    private static Bitmap spunta;
    public static void assegnaImmagine(Activity act)
    {
        spunta=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.spunta), (int)(Ambiente.getWIDTH()*LARGHEZZASPUNTA*SPUNTAPROPORZIONE), (int)(Ambiente.getWIDTH()*LARGHEZZASPUNTA));

    }

    public FloatValore getPos()
    {
        return pos;
    }
    public int getPartialX()
    {
        return x+(int)moltiplicatore*Ambiente.getWIDTH();
    }
    public int getPartialY()
    {
        return y;
    }
    private FloatValore pos;
    private int moltiplicatore;
    private Personaggio contenuto;
    private Livello level;
    public int getZona()
    {
        return moltiplicatore;
    }
    public Livello getLivello()
    {
        return level;
    }
    public int setContenuto(Personaggio p)
    {
        if(p.getPosizione().equals(this))
        {
            contenuto=p;
            p.setContenitore(this);
            return 0;
        }
        return 1;
    }
    public void resetta()
    {
        contenuto=null;
    }
    public Drawable getImmagine()
    {
        if(level==null)
        {
            return null;
        }
        else
        {
            return new Drawable()
            {
                @Override
                public void draw(Canvas canvas)
                {
                    Paint p=new Paint();
                    Giocatore.setTypeface(p);
                    p.setTextSize(Ambiente.getHEIGHT()*AmbienteLivelli.COEFFICIENTE);
                    p.setColor(Color.BLACK);
                    float ff=p.measureText(""+level.getNumero());
                    canvas.drawText(""+level.getNumero(),getX()-ff/2,getY()-(Ambiente.getHEIGHT()*AmbienteLivelli.COEFFICIENTE)/2,p);
                    if(level.getCompletato())
                    {
                        /*
                        p.setColor(Color.RED);
                        p.setTextSize(p.getTextSize()/4);
                        float f=p.measureText(Livello.getCOMPLETATO());
                        canvas.drawText(Livello.getCOMPLETATO(),getX()-f/2,getY(),p);
                        */
                        canvas.drawBitmap(spunta,getX()-ff/2,getY()-(Ambiente.getHEIGHT()*AmbienteLivelli.COEFFICIENTE)/2,p);
                    }
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
    }
    private int x;
    private int y;
    public Punto(int xx,int yy)
    {
        x=xx;
        y=yy;
        contenuto=null;
        pos=new FloatValore(0);
        moltiplicatore=0;
        level=null;
    }
    public Punto (int xx, int yy,FloatValore f,int m)
    {
        x=xx;
        y=yy;
        pos=f;
        moltiplicatore=m;
        level=null;

    }
    public Punto (int xx, int yy,FloatValore f,int m,Livello lev)
    {
        x=xx;
        y=yy;
        pos=f;
        moltiplicatore=m;
        level=lev;

    }
    public int getX()
    {
        return x+(int)(pos.getValore()+moltiplicatore*Ambiente.getWIDTH());
    }
    public int getY()
    {
        return y;
    }
    public boolean equals(Object o)
    {
        if(!(o instanceof Punto))
        {
            return false;

        }
        Punto oo=(Punto)o;
        return oo.getX()==getX()&&oo.getY()==getY();
    }
    public String toString()
    {
        return getX()+" "+getY();
    }
}
