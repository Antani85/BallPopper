package it.brun.dario.ballpopper.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.Tempo;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.trophies.Trofeo;

/**
 * Created by dario on 25/04/17.
 */
public class PannelloTrofeo extends View
{
    private Trofeo trofeo;
    private int width;
    private int height;
    private static String blocked;
    public static void assegnaBlocked(Activity act)
    {
        blocked=act.getResources().getString(R.string.bloccato);
    }

    public PannelloTrofeo(Context c, Trofeo t,int w, int h)
    {
        super(c);
        trofeo=t;
        width=w;
        height=h;
        setLayoutParams(new ListView.LayoutParams(width,height));

    }
    public static String piuLunga(String stringa)
    {
        String [] stringhe= stringa.split(" ");
        int i=0;
        for(int j=1;j<stringhe.length;j++)
        {
            if(stringhe[j].length()>stringhe[i].length())
            {
                i=j;
            }
        }
        return stringhe[i];
    }
    public static String[] splitStringInTwo(String stringa)
    {
        String [] stringhe= stringa.split(" ");
        String [] retval=new String [2];
        int size=stringa.length()/2;
        int length=0;
        int i=0;
        StringBuilder builder=new StringBuilder();
        while(length<=size&&i<stringhe.length-1)
        {
            builder.append(stringhe[i]);
            builder.append(" ");
            length+=stringhe[i].length();
            i++;

        }
        retval[0]=builder.toString().trim();
        builder=new StringBuilder();
        for(;i<stringhe.length;i++)
        {
            builder.append(stringhe[i]);
            builder.append(" ");
        }
        retval[1]=builder.toString().trim();
        return retval;
    }


    public static String [] splitString(String stringa,Paint p,int lengthLine)
    {
        String [] stringhe= stringa.split(" ");
        List<String> retval= new ArrayList<String>();
        StringBuilder costruttore=new StringBuilder();
        costruttore.append(stringhe[0]);
        for(int i=1;i<stringhe.length;i++)
        {
            String retvallo=costruttore.toString();

            if(p.measureText(retvallo+" "+stringhe[i])>=lengthLine)
            {
                retval.add(retvallo);
                costruttore=new StringBuilder();
            }
            else
            {
                costruttore.append(" ");
            }
            costruttore.append(stringhe[i]);
        }
        retval.add(costruttore.toString());
        return retval.toArray(new String[retval.size()]);

    }
    public void onDraw(Canvas c)
    {
        Paint p=new Paint();

        Bitmap disegnante=trofeo.immagineTrofeo();

        p.setColor(Color.argb(255,240,240,255));
        c.drawRect(new RectF(0,0,width,height),p);



        p.setColor(Color.BLACK);
        float baseX=Bomba.getDIAMETROBASE();

        float baseY=height/2-disegnante.getHeight()/2;
        c.drawBitmap(disegnante,baseX,baseY,p);


        Tempo t=trofeo.getTempo();
        if(t!=null)
        {
            baseX=width*4/5;
            baseY=height/4+height/10;
            p.setTextSize(height/7);
            Giocatore.setTypeface(p);
            c.drawText(t.getData(),baseX,baseY,p);

            baseY=height/2+height/8+height/10;
            c.drawText(t.getTempo(),baseX,baseY,p);

        }

        Giocatore.setTypeface(p);
        p.setTextSize(height/4);

        baseX=width/5;
        baseY=height/4+height/10;
        c.drawText(trofeo.getNome(),baseX,baseY,p);
        p.setTextSize(height/7);
        String [] stringhe=splitString(trofeo.getDescrizione(),p,width*3/5);
        baseX=width/5;
        baseY=height/2+height/8+height/10;
        Giocatore.setTypefaceCorsivo(p);

        for(int i=0;i<stringhe.length;i++)
        {
            c.drawText(stringhe[i],baseX,baseY,p);
            baseY+=height/7;
        }



        /*
        if(!trofeo.getSbloccato())
        {
            Giocatore.setTypeface(p);
            p.setColor(Color.argb(70,255,0,0));
            p.setTextSize(height/2);
            c.drawText(blocked,width/4,height*3/4,p);
        }
        */
    }



}
