package it.brun.dario.ballpopper.pseudoviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;

import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 15/04/17.
 */
public class InventarioRotellante
{


    private void setTypeFace(Paint p)
    {
        p.setTextSize(Bomba.getDIAMETROBASE()*3/4);
        p.setShader(null);
        p.setColor(Color.BLACK);
        Typeface currentTypeFace =   p.getTypeface();
        Typeface bold = Typeface.create(currentTypeFace, Typeface.BOLD);
        p.setTypeface(bold);
    }
    public Palla usaMunizioni(Ambiente a, float x, float y, double ang, int liv)
    {
        if(selectedIndex>-1)
        {
            int n=inventariomunizioni.getNumeroMunizioni();
            Palla p= inventariomunizioni.usaMunizioni(selectedIndex,a,x,y,ang,liv);
            if(inventariomunizioni.getNumeroMunizioni()<n)
            {
                assegnaBase();
            }
            return p;

        }

        return null;
    }
    private float base;
    private float posizione;
    private float angolo;

   private void assegnaBase()
   {
       assegnaAngolo();
       base=270-angolo/2;
       posizione=base;
   }
    public void assegnaPosizione(float pos)
    {
        assegnaAngolo();
        posizione+=pos;
    }
    public void assegnaAngolo()
    {
        angolo=360/(inventariomunizioni.getNumeroMunizioni()+1);

    }
    public boolean interno(float px,float py)
    {
        return (px-cx)*(px-cx)+(py-cy)*(py-cy)<=raggio*raggio;
    }
    private int selectedIndex;
    private InventarioMunizioni inventariomunizioni;
    private float x;
    private float y;
    private float raggio;

    public void setSelectedIndex()
    {
        posizione=(270-angolo/2)-(selectedIndex+1)*angolo;
    }


    public InventarioRotellante(InventarioMunizioni invmun,float posx,float posy,float r)
    {
        inventariomunizioni=invmun;
        x=posx;
        y=posy;
        raggio=r;
        cx=x+raggio;
        cy=y+raggio;
        selectedIndex=-1;
        assegnaBase();

    }
    private float cx;
    private float cy;


    public void disegnaInventario(Canvas c,Paint p)
    {

        int num=inventariomunizioni.getNumeroMunizioni()+1;

        assegnaAngolo();
        float inizio=posizione;
        p.setShader(null);
        p.setColor(Color.argb(255,200, 200, 200));

        c.drawOval(new RectF(x, y, x + 2 * raggio, y + 2 * raggio), p);
        p.setColor(Color.RED);
        c.drawArc(new RectF(x, y, x + 2 * raggio, y + 2 * raggio), 270-angolo/2, angolo, true, p);




        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.BLACK);
        p.setTypeface(Typeface.create("",Typeface.NORMAL));
        float xx=cx;
        float yy=cy-raggio/2;
        float yyy=cy-(raggio*3)/4;

        for(int i=0;i<num;i++)
        {

            p.setStyle(Paint.Style.STROKE);

            c.drawArc(new RectF(x, y, x + 2 * raggio, y + 2 * raggio), inizio, angolo, true, p);
            p.setColor(Color.BLACK);
            p.setStyle(Paint.Style.FILL);

            float iniziooo=inizio;

            while(iniziooo<0)
            {
                iniziooo+=360;
            }
            while(iniziooo>360)
            {
                iniziooo-=360;
            }
            if((iniziooo-(270-angolo/2)>=-angolo/2)&&(iniziooo-(270-angolo/2)<angolo/2))
            {
                selectedIndex=i-1;

            }
            c.save();
            try
            {

                c.rotate((posizione-base)+angolo * i, cx, cy);
                c.drawBitmap(inventariomunizioni.immaginePallaSmall(i-1),xx - Bomba.getDIAMETROBASE() / 2, yy - raggio / 8,p);
                setTypeFace(p);
                c.drawText("" + inventariomunizioni.getNumero(i-1), xx-(p.measureText("" + inventariomunizioni.getNumero(i-1)))/2, yyy, p);
                c.restore();
            }
            catch(Exception e)
            {
                c.restore();
            }



            inizio+=angolo;
        }

        p.setStyle(Paint.Style.FILL);


    }
}
