package it.brun.dario.ballpopper.ballpopper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class Cannone
{
    private float cx;
    private float cy;
    private float x;



    private float[] getCentro()
    {


        float kx=length-WIDTH/2;
        float ky=pos+WIDTH/2;
        float[] retval=new float[2];

        double h1=(kx-cx)*Math.cos(angolo)-(ky-cy)*Math.sin(angolo)+cx;
        double h2=(kx-cx)*Math.sin(angolo)+(ky-cy)*Math.cos(angolo)+cy;
        retval[0]=(float)h1;
        retval[1]=(float)h2;

        return retval;
    }
    private void assegnaCentro()
    {
        cx=length/4+WIDTH/2;
        cy=pos+WIDTH/2+WIDTH/2;
    }

    public void disegnaCannone(Canvas g,Paint p)
    {

        int dkgray=Color.argb(255,80,80,80);
        g.save();
        g.rotate((float)(angolo/Math.PI)*180,cx,cy);

        p.setColor(dkgray);

        g.drawRect(new RectF(x + WIDTH / 2, pos,x + WIDTH / 2+ length - WIDTH, pos+WIDTH),p);


        p.setShader(new LinearGradient(x+WIDTH/2+(length-WIDTH)/2, pos, x+WIDTH/2+(length-WIDTH)/2, pos+WIDTH/3, Color.LTGRAY,dkgray, Shader.TileMode.MIRROR));
        g.drawRect(new RectF(x + WIDTH / 2, pos, x + WIDTH / 2 + length - WIDTH, pos + WIDTH / 3), p);

        p.setShader(null);


        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        g.drawRect(new RectF(x+WIDTH/2,pos,x+WIDTH/2+length-WIDTH,pos+WIDTH),p);
        p.setColor(dkgray);
        p.setStyle(Paint.Style.FILL);
        g.drawOval(new RectF(x, pos, x+WIDTH,pos+ WIDTH),p);
        g.drawOval(new RectF(length - WIDTH, pos, length, pos+WIDTH),p);



       // p.setShader(new LinearGradient(x+WIDTH/2+(length-WIDTH)/2, pos, x+WIDTH/2+(length-WIDTH)/2, pos+WIDTH/3, Color.LTGRAY,dkgray, Shader.TileMode.MIRROR));
        p.setShader(new LinearGradient(x + WIDTH / 2 + (length - WIDTH) / 2, pos, x + WIDTH / 2 + (length - WIDTH) / 2, pos + WIDTH, new int[]{Color.LTGRAY, dkgray, dkgray, dkgray}, null, Shader.TileMode.MIRROR));

        //g.drawArc(new RectF(x, pos, x+WIDTH,pos+ WIDTH),(float)(90+Math.toDegrees(Math.acos(1/3))),180,false,p);
        g.drawOval(new RectF(x, pos, x+WIDTH,pos+ WIDTH),p);
        p.setShader(null);



        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        g.drawArc(new RectF(x,pos,x+WIDTH,pos+WIDTH),90,180,false,p);


        g.drawOval(new RectF(length-WIDTH,pos,length,pos+WIDTH),p);
        p.setStyle(Paint.Style.FILL);


        p.setColor(Color.argb(255,40,40,40));

        g.drawOval(new RectF(length - WIDTH + (WIDTH / 10), pos + (WIDTH / 10),length  + (WIDTH / 10) - (WIDTH / 5),pos + (WIDTH / 10)+ WIDTH - (WIDTH / 5)),p);



        p.setColor(Color.argb(255,139,  69,  19));
        g.restore();
        g.drawOval(new RectF(length / 4, pos + WIDTH / 2,length / 4+ WIDTH, pos + WIDTH / 2+WIDTH),p);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        g.drawOval(new RectF(length / 4, pos + WIDTH / 2,length / 4+ WIDTH, pos + WIDTH / 2+WIDTH),p);
        p.setStyle(Paint.Style.FILL);

        p.setColor(Color.argb(255,73,37,10));
        g.drawOval(new RectF(cx-WIDTH/10,cy-WIDTH/10,cx+WIDTH/10,cy+WIDTH/10),p);
        int angolino=0;

        while(angolino<360)
        {

            g.save();
            g.rotate(angolino,cx,cy);

            g.drawArc(new RectF(cx - WIDTH / 4, cy - WIDTH / 8 - WIDTH / 4, cx + WIDTH / 4, cy - WIDTH / 8 + WIDTH / 4), 250, 40, true, p);
            angolino+=72;
            g.restore();
        }




    }
    public void posiziona(float kx, float ky)
    {


        float valore=(ky-pos)/(kx-x);
        double ang=Math.atan(valore);
        setLunghezza(kx);

        setAngolo(ang);

    }
    
    public boolean spara()
    {
        float[] centro=getCentro();
        return ambiente.creaPalla(centro[0],centro[1], angolo, potenza);




    }

    public static int getPotenzamax()
    {
        return (int)((MIN_LENGTH) - MAX_LENGTH / 5);

    }

    private void assegnaPotenza()
    {
        potenza = (int)((MIN_LENGTH) - length() / 5);
    }
    public Cannone(Ambiente ambiente1, float i)
    {
        ambiente = ambiente1;
        x=0;
        pos = i;
        angolo = 0;
        min_length=(WIDTH*8)/3;
        max_length=6*WIDTH;
        length = min_length+(max_length-min_length)/2;
        assegnaPotenza();

        assegnaCentro();
    }

    public void setAngolo(double k)
    {
        angolo =k;
        if(angolo > ANGOLO_MAX)
        {
            angolo = ANGOLO_MAX;
        }
        else if (angolo < -1*ANGOLO_MAX)
        {
            angolo = -1*ANGOLO_MAX;
        }

    }
    public void setLunghezza(float k)
    {
        length=k;
        if(length > max_length)
        {
            length = max_length;
        }
        else if(length < min_length)
        {
            length = min_length;
        }

        assegnaPotenza();
        assegnaCentro();
    }

    public void alza()
    {
        angolo += 0.01;
        if(angolo > ANGOLO_MAX)
            angolo = ANGOLO_MAX;

    }

    public void abbassa()
    {
        angolo -= 0.01;
        if(angolo < -1*ANGOLO_MAX)
            angolo = -1*ANGOLO_MAX;
    }

    public void allunga()
    {
        length+=2;
        if(length > max_length)
            length = max_length;
        assegnaPotenza();
        assegnaCentro();

    }

    public void accorcia()
    {
        length-=2;
        if(length < min_length)
            length = min_length;
        assegnaPotenza();
        assegnaCentro();
    }

    private float length()
    {
        float d=(length-min_length)/(max_length-min_length);
        d= d*(MAX_LENGTH-MIN_LENGTH)+MIN_LENGTH;
        return d;
    }

    public static final float MIN_LENGTH = 50;
    public static final float MAX_LENGTH = 200;
    private static  float WIDTH;

    public static void setWIDTH(float k)
    {
        WIDTH=k;
    }
    private float min_length;
    private float max_length;

    private float length;
    private double angolo;
    private Ambiente ambiente;
    private float pos;
    private int potenza;
    public static final double ANGOLO_MAX = (Math.PI/2)-0.5;
}
