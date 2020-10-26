package it.brun.dario.ballpopper.pseudoviews;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


/**
 * Created by dario on 07/05/17.
 */
public class InputTextView implements Runnable
{
    private float width;
    private float height;
    private float posX;
    private float posY;
    private String text;
    private String textBase;
    private boolean selected;
    private View parent;
    private Paint paint;
    private IBinder ibinder;
    private static final long PAUSE=600l;
    private static final long TIME_MAX=500l;


    private boolean stoppa;
    public void stoppa()
    {
        stoppa=true;
    }
    public void riparti()
    {
        if(stoppa)
        {
            stoppa = false;
            Log.d("partito ","adesso");
            new Thread(this).start();
        }

    }
    private boolean lampeggiante;
    public void run()
    {
        try
        {
            while (!stoppa)
            {
                lampeggiante=!lampeggiante;
                parent.postInvalidate();
                Thread.sleep(PAUSE);

            }
        }
        catch (InterruptedException ex)
        {
            return;
        }
        InputMethodManager imm = (InputMethodManager) parent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);




        sparisciTastiera();
        Log.d("arrestato ", "adesso!");
    }

    public InputTextView(View p,float x,float y,float w,float h,String bt)
    {
        posX=x;
        posY=y;

        parent=p;
        height=h;
        width=w;
        text="";
        textBase=bt;
        ibinder=null;
        selected=false;
        index=0;
        lampeggiante=false;
        paint=new Paint();
        stoppa=true;

    }
    public boolean setSelected(boolean b)
    {
        selected=b;
        return selected;
    }

    public String getText()
    {
        return text;
    }
    public boolean isSelected()
    {
        return selected;
    }

    private void sparisciTastiera()
    {
        InputMethodManager imm = (InputMethodManager) parent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean bool=false;
        if(ibinder!=null)
        {
          bool= imm.hideSoftInputFromWindow(ibinder, 0);

        }
        if(!bool)
        {
            IBinder iBinder = ((Activity) (parent.getContext())).getWindow().getDecorView().getWindowToken();
            if (iBinder != null)
            {

                imm.hideSoftInputFromWindow(iBinder, 0);
            }
        }

    }
    public  void onTouch(MotionEvent event)
    {
        if(!setSelected(interno(event.getX(),event.getY())))
        {
            sparisciTastiera();
            return;
        }


        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            setIndex(event.getX());
        }


        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            InputMethodManager imm = (InputMethodManager) parent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(parent,InputMethodManager.SHOW_FORCED);
            ibinder=parent.getWindowToken();


        }

    }


    public boolean onKey(View v, int keyCode, KeyEvent event)
    {

        if (event.getAction() == KeyEvent.ACTION_DOWN)
        {


            if(event.getKeyCode()==KeyEvent.KEYCODE_DEL)
            {
                if(text.length()>0)
                {
                    removeChar();

                }
            }
            else if(event.getKeyCode()==KeyEvent.KEYCODE_DPAD_LEFT)
            {
                if(index>0)
                {
                    index--;
                }
            }
            else if(event.getKeyCode()==KeyEvent.KEYCODE_DPAD_RIGHT)
            {
                if(index<text.length())
                {
                    index++;
                }
            }
            else
            {
                char pressedKey = (char) event.getUnicodeChar();
                append(pressedKey);
            }

            return true;
        }
        return false;
    }


    private void append(char h)
    {
        text=text.substring(0,index)+h+text.substring(index,text.length());
        index++;
    }
    private void removeChar()
    {
        if(index>0)
        {
            text = text.substring(0, index - 1) + text.substring(index, text.length());
            index--;
        }
    }

    public boolean interno(float x,float y)
    {
        return (x>=posX&&x<=posX+width&&y>=posY&&y<=posY+height);
    }

    private int index;
    private float dim;
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
    private void setIndex(float click)
    {
        float lunghezza=click-posX-width/20;
        String stringa=getDisplayedText();
        paint.setTextSize(dim);
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<stringa.length();i++)
        {
            builder.append(stringa.charAt(i));
            if(paint.measureText(builder.toString())>=lunghezza)
            {
                index=i+indexdispleyed;

                return;
            }
        }
        index=stringa.length();



    }

    public void disegnaInputTextView(Canvas canvas)
    {


        setDimensione("A",paint,width*9/10,height/2);
        dim=paint.getTextSize();

        paint.setColor(Color.argb(255, 230, 234, 213));

        canvas.drawRect(posX, posY, posX+width,posY+height, paint);
        String tt=getDisplayedText();
        if (tt.equals(""))
        {

            paint.setColor(Color.argb(255,180,180,180));

            canvas.drawText(textBase, posX+width/20, posY+height*3/4, paint);
        }
        else
        {
            paint.setColor(Color.BLACK);
            canvas.drawText(tt, posX + width / 20, posY + height * 3 / 4, paint);

        }
        paint.setColor(Color.BLACK);
        if(lampeggiante)
        {
            float baseStrokeWidth = paint.getStrokeWidth();

            paint.setStrokeWidth(dim / 15);
            canvas.drawLine(posX + width / 20 + displayedLength, posY + height / 5, posX + width / 20 + displayedLength, posY + height * 4 / 5, paint);
            paint.setStrokeWidth(baseStrokeWidth);

        }
    }

    private float displayedLength;

    private int indexdispleyed;

    private String getDisplayedText()
    {

        paint.setTextSize(dim);
        float lengthmax=width*9/10;
        float hh=paint.measureText(text);
        if(hh<=lengthmax)
        {
            displayedLength=paint.measureText(text.substring(0,index));
            indexdispleyed=0;
            return text;
        }
        StringBuilder builder=new StringBuilder();
        for(int i=index-1;i>=0;i--)
        {
            builder.insert(0,text.charAt(i));
            if(paint.measureText(builder.toString())>lengthmax)
            {
                String retval=builder.toString().substring(1,builder.length());
                indexdispleyed=i+1;
                displayedLength=paint.measureText(retval);
                return retval;

            }
        }
        indexdispleyed=0;
        displayedLength=paint.measureText(text.substring(0,index));
        for(int i=index;i<text.length();i++)
        {
            builder.append(text.charAt(i));
            if(paint.measureText(builder.toString())>lengthmax)
            {
                String retval=builder.toString().substring(0,builder.length()-1);
                return retval;

            }
        }
        return builder.toString();
    }

}
