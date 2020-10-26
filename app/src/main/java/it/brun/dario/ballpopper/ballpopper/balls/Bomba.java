package it.brun.dario.ballpopper.ballpopper.balls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.Timer;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class Bomba implements Parcelable
{


    private float limitex;
    private float limitey;
    private Bomba(Parcel in)
    {


        bonus=in.readParcelable(Bonus.class.getClassLoader());
        punti=in.readInt();
        moltiplicatore=in.readInt();
        incrementatorex=in.readFloat();
        incrementatorexmax=incrementatorex;
        incrementatorey=in.readFloat();
        incrementatoreymax=incrementatorey;
        x=in.readFloat();
        y=in.readFloat();
        colore=in.readInt();
        raggio=in.readFloat();
        hp=in.readInt();
        hpmax=in.readInt();
        DIAMETRO=in.readFloat();

        collise=new ArrayList<Bomba>();
        limitex=Ambiente.getWIDTH()*(1-Timer.LARGHEZZA);
        limitey=Ambiente.getWIDTH()*Timer.LARGHEZZA*Timer.PROPORZIONE;
    }

    public void writeToParcel(Parcel dest, int flags)
    {

        dest.writeParcelable(bonus,flags);
        dest.writeInt(punti);
        dest.writeInt(moltiplicatore);
        dest.writeFloat(incrementatorex);
        dest.writeFloat(incrementatorey);
        dest.writeFloat(x);
        dest.writeFloat(y);
        dest.writeInt(colore);
        dest.writeFloat(raggio);
        dest.writeInt(hp);
        dest.writeInt(hpmax);
        dest.writeFloat(DIAMETRO);


    }
    public int describeContents()
    {
        return 0;
    }
    public static final Parcelable.Creator<Bomba> CREATOR = new Parcelable.Creator<Bomba>()
    {
        public Bomba createFromParcel(Parcel in)
        {
            return new Bomba(in);
        }

        public Bomba[] newArray(int size)
        {
            return new Bomba[size];
        }
    };

    private static float DIAMETROBASE;
    public static float getDIAMETROBASE()
    {
        return DIAMETROBASE;
    }
    public static void setDIAMETROBASE(float k)
    {
        DIAMETROBASE=k;
    }


    private Bonus bonus;
    public Bonus getBonus()
    {
        return bonus;
    }

    private List<Bomba> collise;

    private void invertiIncrementatoreX()
    {
        incrementatorex=-1*incrementatorex;
        incrementatorexmax=-1*incrementatorexmax;
    }
    private void invertiIncrementatoreY()
    {
        incrementatorey=-1*incrementatorey;
        incrementatoreymax=-1*incrementatoreymax;
    }
    public void collidi (Bomba b1)
    {
        if(b1==this)
        {
            return;
        }
        if(!collise.contains(b1)&&interno(b1.x,b1.y,b1.DIAMETRO/2))
        {

            collise.add(b1);
            b1.collise.add(this);
            if(incrementatorex*b1.incrementatorex<0)
            {
                invertiIncrementatoreX();
                b1.invertiIncrementatoreX();
            }
            else if(x<b1.x)
            {
                invertiIncrementatoreX();
            }
            if(incrementatorey*b1.incrementatorey<0)
            {
                invertiIncrementatoreY();
                b1.invertiIncrementatoreY();
            }
            else if(y<b1.y)
            {
                invertiIncrementatoreY();
            }

        }
        else if(!interno(b1.x,b1.y,b1.DIAMETRO/2))
        {
            collise.remove(b1);
        }
    }


    private int moltiplicatore;
    public void setMoltiplicatore(int k)
    {
        moltiplicatore=k;
    }

    public int getMoltiplicatore()
    {
        return moltiplicatore;
    }
    private int punti;
    private float cx;
    private float cy;
    public void setCentro()
    {
        cx=x+DIAMETRO/2;
        cy=y+DIAMETRO/2;
    }

    public float getCentroX()
    {
        return cx;
    }
    public float getCentroY()
    {
        return cy;
    }

    public boolean interno(float xx,float yy,float r)
    {




        /*double x2=(double)(xx)+(double)r;
        double y2=(double)(yy)+(double) r;
        double xc=(double)x+(double)(DIAMETRO)/2;
        double yc=(double)y+(double)(DIAMETRO)/2;

        double x1=x2-xc;
        double y1=y2-yc;


        double r1=(double)(DIAMETRO)/2;
        double r2=(double)r;


        double b=(x1*x1*y1+y1*y1*y1+y1*r1*r1-y1*r2*r2)/(x1*x1);


        double a=(x1*x1+y1*y1)/(x1*x1);
        double c1=(x1*x1+y1*y1+r1*r1-r2*r2);
        double c=(c1*c1/(4*x1*x1))-r1*r1;


        if((b*b)-(4*a*c)>=0)
        {
            return true;
        }

        else if((x1*x1+y1*y1)<=(DIAMETRO/2)*(DIAMETRO/2))
        {
            return true;
        }
        else if(((xc-x2)*(xc-x2)+(yc-y2)*(yc-y2))<=r*r)
        {
            return true;
        }

        return false;
        */
        setCentro();
        float x2=xx+r;
        float y2=yy+r;
        return (cx-x2)*(cx-x2)+(cy-y2)*(cy-y2)<=(r+DIAMETRO/2)*(r+DIAMETRO/2);
    }
   private void aumentaIncrementatori(int k)
   {

       incrementatorex+=DIAMETROBASE*((float)k/(float)(hpmax))*incrementatorexmax;
       incrementatorey+=DIAMETROBASE*((float)k/(float)(hpmax))*incrementatoreymax;
   }

    public int getColore()
    {
        return colore;
    }
    public int getHp()
    {
        return hp;
    }

    public int setHp(int k)
    {
        hp+=k;
        if(hp<=0)
        {
            hp=0;
            return 0;

        }
        if(k<0)
        {
           aumentaIncrementatori(-1*k);


        }
        setAlpha(255);
        return hp;
    }

    public void setAlpha(int a)
    {
        alpha+=a;
        if(alpha<0)
        {
            alpha=0;
        }
        if(alpha>255)
        {
            alpha=255;
        }
    }
    private int alpha;
    public void disegnaBarraHp(Canvas c,Paint p)
    {
        if(hp<hpmax)
        {
            p.setShader(null);
            p.setColor(Color.WHITE);
            p.setAlpha(alpha);
            c.drawRect(new RectF(x, y - 4 * DIAMETROBASE / 5, x + DIAMETRO, y - 2 * DIAMETROBASE / 5), p);

            p.setColor(Color.GREEN);
            p.setAlpha(alpha);
            c.drawRect(new RectF(new RectF(x, y - 4 * DIAMETROBASE / 5, x + DIAMETRO * ((float) hp / (float) hpmax), y - 2 * DIAMETROBASE / 5)), p);
            p.setColor(Color.BLACK);
            p.setAlpha(alpha);
            p.setStyle(Paint.Style.STROKE);
            c.drawRect(new RectF(x, y - 4 * DIAMETROBASE / 5, x + DIAMETRO, y - 2 * DIAMETROBASE / 5), p);
            p.setStyle(Paint.Style.FILL);
            p.setAlpha(255);
        }

    }
    private int hp;
    private int hpmax;

    public int getHpmax()
    {
        return hpmax;
    }
    protected void impostaHp(int h)
    {
        hp=h;
        hpmax=h;

    }


    public void runna()
    {
        if(raggio<=DIAMETRO)
        {
            muovi();
            if(!esplosa()&&incrementatorex!=incrementatorexmax)
            {
                    incrementatorex+=(incrementatorexmax-incrementatorex)/10;

            }
            if(!esplosa()&&incrementatorey!=incrementatoreymax)
            {
                incrementatorey+=(incrementatoreymax-incrementatorey)/10;

            }




        }
        else
        {

            ambiente.disinnescaBomba(this);
        }
        //ambiente.postInvalidate();

    }
    public void incrementaInventario()
    {
        Giocatore.inventario().incrementaScoppiate();
    }
    private static float INCREMENTATORE_MIN;
    private static float INCREMENTATORE_MAX;
    public static float getINCREMENTATORE_MIN()
    {
        return INCREMENTATORE_MIN;
    }
    public static float getINCREMENTATORE_MAX()
    {
        return INCREMENTATORE_MAX;
    }
    public static void setIncrementatori(float min,float max)
    {
        INCREMENTATORE_MIN=min*PROPFPS;
        INCREMENTATORE_MAX=max*PROPFPS;
    }
    private int colore;
    private float DIAMETRO;
    private Ambiente ambiente;
    private float incrementatorex;
    private float incrementatorey;
    private float incrementatorexmax;
    private float incrementatoreymax;
    private float x;
    private float y;
    public static final float PROPFPS=0.1666666f;
    private static final int DECREMENTATORE_ALPHA=(int)(-20f*PROPFPS);

    private static final int LEVEL=(int)(100*PROPFPS);

    public static int getLEVEL()
    {
        return LEVEL;
    }
    public Bomba(Ambiente a,float ix,float iy, int c,float xx,float yy)
    {

        bonus=null;
        punti=10;
        moltiplicatore=1;
        incrementatorex=ix;
        incrementatorey=iy;
        incrementatorexmax=ix;
        incrementatoreymax=iy;
        x=xx;
        y=yy;
        colore=c;
        ambiente=a;
        alpha=0;
        raggio=0;
        impostaHp(10);
        DIAMETRO=DIAMETROBASE;
        collise=new ArrayList<Bomba>();
        limitex=Ambiente.getWIDTH()*(1-Timer.LARGHEZZA);
        limitey=Ambiente.getWIDTH()*Timer.LARGHEZZA*Timer.PROPORZIONE;

    }

    protected void doubleIncrementatori(float n)
    {
        incrementatorexmax=n*incrementatorexmax;
        incrementatorex=incrementatorexmax;
        incrementatoreymax=n*incrementatoreymax;
        incrementatorey=incrementatoreymax;
    }
    public void setAmbiente(Ambiente a)
    {
        ambiente=a;
    }

    public Bomba (Ambiente a,float ix,float iy, int c,float xx,float yy,Bonus bon)
    {
        this(a, ix, iy,  c, xx, yy);
        bonus=bon;

    }


    public  Bomba getBomba (Ambiente a,float ix,float iy, int c,float xx,float yy)
    {
        return new Bomba(a,ix,iy,c,xx,yy,bonus);

    }

    protected void setPunti(int p)
    {
        punti=p;
    }
    public int getPunti()
    {
        return punti;
    }
    protected void setDiametro(float k)
    {
        DIAMETRO=k;
    }
    public float getDiametro()
    {
        return DIAMETRO;
    }
    public void setRaggio(float k)
    {

        if(raggio==0)
        {
            setCentro();



        }

        raggio=k;


    }
    public boolean esplosa()
    {

        return raggio!=0;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }

    public float getRaggio()
    {

        return raggio;
    }


    private float raggio;




    public void disegnaBomba(Canvas g, Paint p)
    {


        p.setShader(null);
        p.setColor(colore);
        if(raggio==0)
        {
            if(bonus==null)
            {

                g.drawOval(new RectF(x, y, x+DIAMETRO, y+DIAMETRO),p);
                setCentro();

                p.setShader(new LinearGradient(cx, cy, cx, cy-DIAMETRO/2, colore,Color.WHITE, Shader.TileMode.MIRROR));
                g.drawOval(new RectF(cx-DIAMETRO/3,cy-DIAMETRO/2,cx+DIAMETRO/3,cy),p);


            }
            else
            {
                bonus.disegnaBonus(g,p,x,y);
                /*
                p.setAlpha(50);
                g.drawOval(new RectF(x, y, x+DIAMETRO, y+DIAMETRO),p);
                setCentro();

                p.setShader(new LinearGradient(cx, cy, cx, cy-DIAMETRO/2, colore,Color.WHITE, Shader.TileMode.MIRROR));
                g.drawOval(new RectF(cx-DIAMETRO/3,cy-DIAMETRO/2,cx+DIAMETRO/3,cy),p);
                p.setAlpha(255);
                */

            }
            disegnaBarraHp(g,p);

        }
        else if(raggio<=DIAMETRO)
        {


            float x1=cx-raggio/2;
            float y1=cy-raggio/2;
            /*
            p.setShader(new LinearGradient(x1, y1, x1+raggio, y1+raggio, colore, Color.RED, Shader.TileMode.MIRROR));
            g.drawOval(new RectF(x1, y1, x1+raggio,y1+ raggio), p);

            */
            g.drawOval(new RectF(x1, y1, x1+raggio, y1+raggio),p);
            p.setShader(new LinearGradient(cx, cy, cx, cy-raggio/2, colore,Color.WHITE, Shader.TileMode.MIRROR));
            g.drawOval(new RectF(cx-raggio/3,cy-raggio/2,cx+raggio/3,cy),p);





        }


    }


    private static float INCREMENTATORE_RAGGIO;
    public static void setINCREMENTATORE_RAGGIO(float k)
    {
        INCREMENTATORE_RAGGIO=k;
    }
    public static float getINCREMENTATORE_RAGGIO()
    {
        return INCREMENTATORE_RAGGIO;
    }



    public void suonaColpitore()
    {
        ambiente.suonaColpitore();
    }
    public void muovi()
    {

        if(raggio==0)
        {
            x+=incrementatorex;

           /* if(y<limitey&&x>limitex-DIAMETRO)
            {
                x=limitex-DIAMETRO;
                invertiIncrementatoreX();
            }*/
            if(x>ambiente.getWIDTH()-DIAMETRO)
            {
                x=ambiente.getWIDTH()-DIAMETRO;
               invertiIncrementatoreX();
            }
            if(x<ambiente.getWIDTH()/4)
            {
                x=ambiente.getWIDTH()/4;
                invertiIncrementatoreX();
            }
            y+=incrementatorey;


          /*  if(y<limitey&&x>limitex-DIAMETRO)
            {
                y=limitey;
                invertiIncrementatoreY();
            }*/
            if(y>ambiente.getHEIGHT()-DIAMETRO)
            {

                y=ambiente.getHEIGHT()-DIAMETRO;
                invertiIncrementatoreY();
            }
            if(y<1)
            {
                y=1;
                invertiIncrementatoreY();
            }
            for(int i=0;i<ambiente.numBombe();i++)
            {


                collidi(ambiente.getBomb(i));


            }


        }
        else
        {
            raggio+=DIAMETRO*PROPFPS/4;
        }
        setAlpha(DECREMENTATORE_ALPHA);

    }

    protected Ambiente getAmbiente()
    {
        return ambiente;
    }

}
