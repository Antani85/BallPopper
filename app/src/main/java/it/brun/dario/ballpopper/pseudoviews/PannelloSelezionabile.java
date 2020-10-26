package it.brun.dario.ballpopper.pseudoviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;

/**
 * Created by dario on 03/05/17.
 */
public class PannelloSelezionabile
{
    private float x;
    private float y;
    private float diametro;
    private Bonus bonus;

    public PannelloSelezionabile(Bonus b,float xx,float yy,float d)
    {
        bonus=b;
        x=xx;
        y=yy;
        diametro=d;
        selected=false;
    }
    private boolean selected;
    public boolean interno (float xx,float yy)
    {
        return xx>=x&&xx<=x+diametro&&yy>=y&&yy<=y+diametro;
    }

    public void disegnaPannello(Canvas g,Paint p)
    {
        p.setShader(null);
        if(selected)
        {
            p.setColor(Color.LTGRAY);
        }
        else
        {
            p.setColor(Color.TRANSPARENT);
        }
        g.drawRect(new RectF(x,y,x+diametro,y+diametro),p);
        if(bonus!=null)
        {
            bonus.disegnaBonus(g, p, x, y, diametro);
        }
        else
        {
            p.setColor(Color.BLACK);
            g.drawOval(new RectF(x,y,x+diametro,y+diametro),p);
        }
    }
    public Bonus getBonus()
    {
        return bonus;
    }
    public void setSelected(boolean b)
    {
        if(bonus!=null)
        {

            selected = b;
        }
    }
    public boolean selected()
    {
        return selected;
    }
}
