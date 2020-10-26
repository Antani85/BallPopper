package it.brun.dario.ballpopper.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;

/**
 * Created by dario on 05/05/17.
 */
public class PannelloBonus extends View
{
    private Bonus bonus;
    private String utilizzi;
    private String title;

    public PannelloBonus(Context c,Bonus b)
    {
        super(c);
        bonus=b;
        //width=Ambiente.getHEIGHT();
        //height=(int)(Ambiente.getHEIGHT()*PannelloSalvataggio.PROPORZIONE);
        utilizzi=c.getResources().getString(R.string.usirimasti)+": "+bonus.getNumUsi();
        title=c.getResources().getString(R.string.bonus);

    }
    public void onDraw(Canvas canvas)
    {
        Paint p=new Paint();
        Giocatore.setTypeface(p);
        p.setColor(Color.argb(255, 235, 235, 255));
        canvas.drawRect(new RectF(0,0,getWidth(),getHeight()),p);
        p.setColor(Color.BLACK);
        MessageView.setDimensione(title,p,getWidth()*2/3,getHeight()/9);
        float dim=p.measureText(title);
        canvas.drawText(title,getWidth()/2-dim/2,getHeight()/6,p);
        int r=getHeight()/4;
        canvas.drawBitmap(bonus.getBitmap(r),getWidth()/2-r/2,getHeight()*5/12-r/2,p);
        Giocatore.setTypefaceCorsivo(p);
        p.setTextSize(p.getTextSize()/2);
        String [] desc=PannelloTrofeo.splitString(bonus.getDescrizione(),p,getWidth()*8/10);
        float margin=getHeight()/40;
        float altezza=margin;
        Rect bounds= new Rect();
        for(int i=0;i<desc.length;i++)
        {
            p.getTextBounds(desc[desc.length-1-i],0,desc[desc.length-1-i].length(),bounds);
            altezza+=bounds.height();
            canvas.drawText(desc[desc.length-1-i],getWidth()/20,getHeight()-altezza,p);
            altezza+=margin;


        }

        p.getTextBounds(utilizzi,0,utilizzi.length(),bounds);
        altezza+=bounds.height();
        canvas.drawText(utilizzi,getWidth()/20,getHeight()-altezza,p);



    }

}
