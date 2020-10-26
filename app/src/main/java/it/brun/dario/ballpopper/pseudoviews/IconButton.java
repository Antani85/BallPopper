package it.brun.dario.ballpopper.pseudoviews;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import it.brun.dario.ballpopper.interfaces.Eseguibile;

/**
 * Created by dario on 17/05/17.
 */
public class IconButton{


    private float posX;
    private float posY;
    private float width;
    private float height;
    private Bitmap icona1;
    private Bitmap icona2;

    private Eseguibile eseguibile1;
    private Eseguibile eseguibile2;

    public IconButton(float x,float y,Bitmap ic1,Bitmap ic2,Eseguibile ese1,Eseguibile ese2)
    {
        posX=x;
        posY=y;

        icona1=ic1;
        icona2=ic2;
        width=icona1.getWidth();
        height=icona1.getHeight();

        eseguibile1=ese1;
        eseguibile2=ese2;
        selected=true;


    }
    public void setSelected(boolean b)
    {
        selected=b;
        if(selected)
        {
            eseguibile1.esegui();
        }
        else
        {
            eseguibile2.esegui();
        }

    }

    public  boolean onTouch(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN&&interno(event.getX(),event.getY()))
        {
            setSelected(!selected);
            return true;




        }
        return false;

    }
    public boolean interno(float x,float y)
    {
        return (x>=posX&&x<=posX+width&&y>=posY&&y<=posY+height);

    }
    private boolean selected;
    public boolean isSelected()
    {
        return selected;
    }
    public void disegnaBottone(Canvas c)
    {
      if(selected)
      {
          c.drawBitmap(icona1,posX,posY,null);
      }
      else
      {
          c.drawBitmap(icona2,posX,posY,null);
      }

    }

}
