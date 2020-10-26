package it.brun.dario.ballpopper.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.widget.LinearLayout;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;

/**
 * Created by dario on 25/04/17.
 */
public class MessageView extends View
{
    private static final float PROPORZIONETESTO=0.82517f;

    private static final float PROPORZIONE=0.74825f;
    private static final float POSX=0.17482f;
    private static final float POSY=0.28037f;

    private static final float POSX2=0.08741f;
    private static final float POSY12=0.18691f;
    private static final float POSY22=0.35046f;
    private static final float MAXY=0.0701f;
    private static final float PROPORZIONETESTO2=0.40209f;


    private String message;
    private static int DIMX;
    private static int DIMY;
    private static Bitmap error_bitmap;
    private static Bitmap ok_bitmap;
    private static Bitmap nessun_bitmap;
    private static Bitmap fallito_bitmap;
    private int tipo;
    public static void assegnaImmagini(Activity act)
    {
        DIMY=(int)(PROPORZIONE*(Ambiente.getHEIGHT()*2)/3);
        DIMX=(int)(Ambiente.getHEIGHT()*2)/3;
        error_bitmap=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.errormessage), DIMY, DIMX);
        ok_bitmap=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.okmessage), DIMY, DIMX);
        nessun_bitmap=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.nessunmessage), DIMY, DIMX);
        fallito_bitmap=Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.fallitomessage), DIMY, DIMX);

    }


    public static void setDimensione(String s,Paint p, float lunghezza,float altezzaMax)
    {
        int dim=1;
        p.setTextSize(dim);
        int max=0;
        while(p.measureText(s)<lunghezza&&max<altezzaMax)
        {
            dim++;
            p.setTextSize(dim);
            Rect bounds = new Rect();
            p.getTextBounds(s, 0, s.length(), bounds);
            max = bounds.height();
        }
    }

    public static void setDimensione(Paint p,String s, float lunghezza)
    {
        int dim=1;
        p.setTextSize(dim);
        while(p.measureText(s)<lunghezza)
        {
            dim++;
            p.setTextSize(dim);
        }
    }
    public static final int ERROR_TYPE=0;
    public static final int OK_TYPE=1;
    public static final int NESSUN_TYPE=2;
    public static final int FALLITO_TYPE=3;

    private int type;
    public MessageView(Context c,int t,String m)
    {
        super(c);
        tipo=t;
        message=m;
        setLayoutParams(new LinearLayout.LayoutParams(DIMX,DIMY));
    }
    public void onDraw(Canvas c)
    {
        Paint p=new Paint();

        Giocatore.setTypeface(p);
        float x,y,lunghezza;


        if(tipo==ERROR_TYPE)
        {
            p.setColor(Color.argb(255,255,255,100));
            c.drawRect(new RectF(0,0,DIMX,DIMY),p);
            c.drawBitmap(error_bitmap, 0, 0, p);

        }
        else if(tipo==OK_TYPE)
        {
            p.setColor(Color.argb(255,200,235,250));
            c.drawRect(new RectF(0,0,DIMX,DIMY),p);

            c.drawBitmap(ok_bitmap, 0, 0, p);


        }
        else if(tipo==NESSUN_TYPE)
        {
            p.setColor(Color.argb(255,128,255,128));
            c.drawRect(new RectF(0,0,DIMX,DIMY),p);

            c.drawBitmap(nessun_bitmap, 0, 0, p);


        }
        else if(tipo==FALLITO_TYPE)
        {
            p.setColor(Color.argb(255,239,228,176));
            c.drawRect(new RectF(0,0,DIMX,DIMY),p);

            c.drawBitmap(fallito_bitmap, 0, 0, p);


        }
        p.setColor(Color.BLACK);
        if(tipo==ERROR_TYPE||tipo==OK_TYPE)
        {
            x = DIMX - ((PROPORZIONETESTO * DIMX * 9) / 10);
            y = DIMY * POSY;
            lunghezza = (PROPORZIONETESTO * DIMX * 4) / 5;
            setDimensione(p,message, lunghezza);
            c.drawText(message,x,y,p);
        }

        else
        {
            String[] stringhe=PannelloTrofeo.splitStringInTwo(message);
            x = POSX2*DIMX;
            y = DIMY * POSY12;
            lunghezza =PROPORZIONETESTO2 * DIMX;
            float lunghezzamax=MAXY*DIMY;
            setDimensione(stringhe[0],p,lunghezza,lunghezzamax);
            float m=p.getTextSize();
            setDimensione(stringhe[1],p,lunghezza,lunghezzamax);
            if(p.getTextSize()>m)
            {
                p.setTextSize(m);
            }

            c.drawText(stringhe[0],x,y,p);
            y= DIMY * POSY22;

            c.drawText(stringhe[1],x,y,p);
        }



    }
}
