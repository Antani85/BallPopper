package it.brun.dario.ballpopper.ballpopper.bonus;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;

import it.brun.dario.ballpopper.exceptions.CaricamentoException;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.views.MessageView;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;

/**
 * Created by dario on 10/04/17.
 */

public abstract class Bonus implements Parcelable
{

    private static float DIAMETROBASE;
    public static void assegnaDIAMETROBASE(float k)
    {
        DIAMETROBASE=k;
    }
    public static float getDIAMETROBASE()
    {
        return DIAMETROBASE;
    }
    public int getNumUsi()
    {
        return numUsi;
    }
    private Bonus(Parcel in)
    {


        nome=in.readString();
        descrizione=in.readString();
        numUsi=in.readInt();
        numUsiMax=in.readInt();
    }

    public void writeToParcel(Parcel dest, int flags)
    {

        dest.writeString(codifica());
        dest.writeString(nome);
        dest.writeString(descrizione);
        dest.writeInt(numUsi);
        dest.writeInt(numUsiMax);



    }

    public int describeContents()
    {
        return 0;
    }

    public static final Parcelable.Creator<Bonus> CREATOR = new Parcelable.Creator<Bonus>()
    {
        public Bonus createFromParcel(Parcel in)
        {
            try
            {
                return getBonusFromCodifica(in.readString());
            }
            catch (Exception e)
            {
                return null;
            }

        }

        public Bonus[] newArray(int size)
        {
            return new Bonus[size];
        }
    };

    public abstract String codice();
    public String codifica()
    {
        return codice()+((char)(numUsi+65));
    }

    public static Bonus getBonusFromCodifica(String cod)throws CaricamentoException {

        try
        {
            int num = ((int) cod.charAt(cod.length() - 1)) - 65;


            String cod1 = cod.substring(0, cod.length() - 1);

            if (BonusX2.CODIFICA.equals(cod1))
            {
                return new BonusX2(num);
            }
            if (BonusX4.CODIFICA.equals(cod1))
            {
                return new BonusX4(num);

            }
            if (BonusPIU1.CODIFICA.equals(cod1))
            {
                return new BonusPIU1(num);
            }
            if (BonusPIU2.CODIFICA.equals(cod1))
            {
                return new BonusPIU2(num);
            }
            if (BonusPIU3.CODIFICA.equals(cod1))
            {
                return new BonusPIU3(num);
            }
            if (BonusPIU4.CODIFICA.equals(cod1))
            {
                return new BonusPIU4(num);
            }
            if (BonusPIU5.CODIFICA.equals(cod1))
            {
                return new BonusPIU5(num);
            }
            if (BonusPIU6.CODIFICA.equals(cod1))
            {
                return new BonusPIU6(num);
            }
            if (BonusPIU7.CODIFICA.equals(cod1))
            {
                return new BonusPIU7(num);
            }
            if (BonusPIU10.CODIFICA.equals(cod1))
            {
                return new BonusPIU10(num);
            }

            else
            {
                throw new RuntimeException();
            }
        }
        catch (Exception e)
        {
            throw new CaricamentoException();
        }
    }
    public abstract int getEffetto(int punti,int numPalla);

    /*
    private void setTypeFace(Paint p,float r)
    {
        p.setTextSize((r/3)*2);
        /*Typeface currentTypeFace =   p.getTypeface();
        Typeface bold = Typeface.create(currentTypeFace, Typeface.BOLD);
        p.setTypeface(bold);
        Giocatore.setTypeface(p);
    }
   */

    public boolean usa()
    {
        numUsi--;
        return numUsi>0;
    }
    private int numUsiMax;
    private int numUsi;
    private String descrizione;
    private String nome;


    public String getNome()
    {
        return nome;
    }
    public String getDescrizione()
    {
        return descrizione;
    }
    public Bonus(String n, String d,int numMax)
    {
        nome=n;
        descrizione=d;
        numUsi=numMax;
        numUsiMax=numMax;
    }
    public Bonus(String n, String d,int num,int numMax)
    {
        nome=n;
        descrizione=d;

        numUsiMax=numMax;
        numUsi=num;
        if(numUsi>numUsiMax)
        {
            numUsi=numUsiMax;
        }
        if(numUsi<0)
        {
            numUsi=0;
        }
    }

    public void disegnaBonus(Canvas g,Paint p,float x, float y)
    {

       disegnaBonus(g, p, x, y, Bomba.getDIAMETROBASE());
    }
    public void disegnaBonus(Canvas g,Paint p,float x, float y,float r)
    {
        Giocatore.setTypeface(p);
        p.setShader(null);

        p.setColor(Color.YELLOW);

        g.drawOval(new RectF(x, y, x+r, y+r),p);

        float angolo=360/numUsiMax;
        float x1=x+r/40;
        float y1=y+r/40;
        float r1=r*19/20;
        p.setColor(Color.RED);
        float mm=p.getStrokeWidth();
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(r/20);

        RectF oval = new RectF(x1, y1, x1+r1, y1+r1);
        Path path = new Path();
        path.arcTo(oval, 270, angolo*(numUsiMax-numUsi), true);
        g.drawPath(path,p);


        //g.drawArc(new RectF(x1, y1, x1+r1, y1+r1),270,angolo*(numUsiMax-numUsi),true,p);


        p.setStrokeWidth(mm);
        p.setStyle(Paint.Style.FILL);




        p.setColor(Color.RED);

        MessageView.setDimensione(nome,p,r*0.8f,r*0.8f);
        Rect bounds=new Rect();
        p.getTextBounds(nome,0,nome.length(),bounds);
        g.drawText(nome,x+r/2-bounds.width()/2,y+r/2+bounds.height()/2,p);


       // g.drawText(nome,x,y+(int)(dd*3),p);
       /* p.setColor(Color.BLACK);
        float angolo=360/numUsiMax;
        g.drawArc(new RectF(x, y, x+r, y+r),270,angolo*(numUsiMax-numUsi),true,p);*/

    }
    public Bitmap getBitmap(int r)
    {
        Bitmap retval= Bitmap.createBitmap(r, r, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(retval);
        Paint p=new Paint();
        p.setColor(Color.TRANSPARENT);
        c.drawRect(new RectF(0,0,r,r),p);
        disegnaBonus(c,p,0,0,r);
        return retval;
    }
    public void disegnaBonusBig(Canvas g,Paint p,float x, float y)
    {

       disegnaBonus(g,p,x,y,getDIAMETROBASE());
    }

    public boolean equals(Object o)
    {
       /* if(!(o instanceof Bonus))
        {
            return false;
        }
        Bonus b=(Bonus) o;
        return nome.equals(b.nome);
        */
        return o==this;
    }

}
