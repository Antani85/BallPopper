package it.brun.dario.ballpopper.ballpopper.bullets;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.exceptions.EsplosaException;
import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class Palla extends Thread
{
    public int getPuntiTotali()
    {
        return puntiTotali;
    }

    private int puntiTotali;
    private static Bitmap immagine;
    private static Bitmap immagineSmall;


    public static void assegnaImmagine(Activity act)
    {
        immagine = BitmapFactory.decodeResource(act.getResources(), R.drawable.normali);
        immagineSmall = Giocatore.getResizedBitmap(BitmapFactory.decodeResource(act.getResources(), R.drawable.normali),(int)Bomba.getDIAMETROBASE(),(int)Bomba.getDIAMETROBASE());

    }
    public static Bitmap getImmagineSmall()
    {
        return immagineSmall;
    }
    public static Bitmap getImmagine() {
        return immagine;
    }


    public void resettaColpite() {

        colpite = new ArrayList<Bomba>();
    }

    private int danno;

    private List<Bomba> colpite;

    public void addColpita(Bomba b)
    {
        int n = getNumColpite() + 1;
        b.setMoltiplicatore(n);


        colpite.add(b);
    }

    public Bomba getColpita(int i)
    {
        return colpite.get(i);
    }
    public int getNumColpite() {
        return colpite.size();
    }
    public int getNumScoppiate()
    {
        int num=0;
        for(int i=0;i<colpite.size();i++)
        {
            if(colpite.get(i).esplosa())
            {
                num++;
            }
        }
        return num;
    }

    public boolean colpita(Bomba b) {
        return colpite.contains(b);
    }

    private float cx;
    private float cy;

    public void setCentro()
    {

        cx = x + width / 2;
        cy = y + height / 2;
    }

    public float getCentroX() {
        return cx;
    }

    public float getCentroY() {
        return cy;
    }


    public void disegnaStella(Canvas g, Paint p, double diametro, double innerDiametro, int numOfPt, int c1, int c2) {


        double radius = diametro / 2;
        double innerRadius = innerDiametro / 2;
        float x1 = x + (float) radius;
        float y1 = y + (float) radius;
        float[] xx = new float[numOfPt * 2];
        float[] yy = new float[numOfPt * 2];
        double section = 2.0 * Math.PI / numOfPt;
        xx[0] = (float) (x1 + radius * Math.cos(0));
        yy[0] = (float) (y1 + radius * Math.sin(0));
        xx[1] = (float) (x1 + innerRadius * Math.cos(0 + section / 2.0));
        yy[1] = (float) (y1 + innerRadius * Math.sin(0 + section / 2.0));

        for (int i = 2; i < 2 * numOfPt; i += 2) {

            xx[i] = (float) (x1 + radius * Math.cos(section * i));
            yy[i] = (float) (y1 + radius * Math.sin(section * i));
            xx[i + 1] = (float) (x1 + innerRadius * Math.cos(section * i + section / 2.0));
            yy[i + 1] = (float) (y1 + innerRadius * Math.sin(section * i + section / 2.0));


        }


        float[] xxx = new float[numOfPt * 2];
        float[] yyy = new float[numOfPt * 2];

        int i = 0;
        int k = 1;
        int kk1 = 0;
        int kk2 = numOfPt + 1;

        while (i < 2 * numOfPt) {
            if (k == 1) {
                xxx[i] = xx[kk1];
                xxx[i + 1] = xx[kk1 + 1];
                yyy[i] = yy[kk1];
                yyy[i + 1] = yy[kk1 + 1];
                kk1 += 2;

            } else {

                xxx[i] = xx[kk2];
                xxx[i + 1] = xx[kk2 + 1];
                yyy[i] = yy[kk2];
                yyy[i + 1] = yy[kk2 + 1];
                kk2 += 2;


            }
            k = -1 * k;
            i += 2;
        }


        p.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.moveTo(xxx[0], yyy[0]);


        for (i = 1; i < xxx.length; i++) {
            path.lineTo(xxx[i], yyy[i]);
        }
        path.lineTo(xxx[0], yyy[0]);


        g.drawPath(path, p);


        float[] ff = new float[]{0.3f, 1f};

        int[] cc = new int[]{c1, c2};
        p.setShader(new RadialGradient(x1, y1, (float) radius, cc, ff, Shader.TileMode.MIRROR));


        g.drawPath(path, p);

    }



    public void setDanno(int d) {
        danno = d;
    }

    public float getLivello() {

        return livello;
    }

    public void setLivello(float i) {

        livello = i;

    }
    public boolean getStoppa()
    {
        return stoppa;
    }




    private boolean stoppa;
    private float width;
    private float height;

    public void run()
    {

        while (true)
        {

            long l=System.currentTimeMillis();
            if(stoppa)
            {
                break;
            }
            try
            {
                muovi();
                ambiente.postInvalidate();
                l=System.currentTimeMillis()-l;
                sleep(VELOCITAFPS-l);

            }
            catch (IllegalArgumentException ex)
            {

                continue;
            }
            catch (InterruptedException ex)
            {

                break;
            }
        }
        ambiente.destroy(this);

    }

    private static float VELOCITA;

    public static void assegnaVELOCITA(float k)
    {

        VELOCITA=k;
    }
    private float livello;
    private Ambiente ambiente;
    public int numBombe()
    {
        return ambiente.numBombe();


    }
    public static final int VELOCITAFPS=(int)(100*Bomba.PROPFPS);

    public void stoppa()
    {
        stoppa=true;
    }
    public Palla(Ambiente a,float xx,float yy,double ang,int liv)
    {
        super();
        height=Bomba.getDIAMETROBASE()/4;
        width=Bomba.getDIAMETROBASE()/4;
        colpite=new ArrayList<Bomba>();
        livello=(Bomba.getDIAMETROBASE()*VELOCITAFPS)/(liv*5);
        ambiente=a;
        x=xx;
        y=yy;
        angolo=ang;
        danno=10;
        puntiTotali=0;
        stoppa=false;

    }
    public Ambiente getAmbiente()
    {
        return ambiente;
    }
    protected Bitmap getImmagine(int res,int dim)
    {
        return ambiente.getImmagine(res,dim);
    }
    public void setAngolo(double g)
    {

        angolo=g;
    }

    private float x;
    private float y;
    private double angolo;


    public void  setX(float a)throws InterruptedException
    {
        x=a;
        if(x>ambiente.getWIDTH()||x<1*width)
        {
            throw new InterruptedException();

        }

    }
    public void  setY(float a)throws InterruptedException
    {
        y=a;
        if(y<-1*height||y>ambiente.getHEIGHT())
        {
            throw new InterruptedException();

        }

    }

    public void centraSuCannone()
    {

        y=y-height/2;
    }

    public float getWidth()
    {
        return width;
    }
    public float getHeight()
    {
        return height;
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public double getAngolo()
    {


        return angolo;
    }

    public void incrementaInventario()
    {
        Giocatore.inventario().addNormaliSparati();
    }

    public void disegnaPalla(Canvas g,Paint p)
    {


        p.setShader(null);
        p.setColor(Color.BLACK);

        /*
        g.drawOval(new RectF(x, y, x+width, y+height),p);
        */


        g.save();

        g.rotate((float)Math.toDegrees(angolo),x,y);
        g.drawRect(new RectF(x, y, x+width, y+height),p);
        g.drawArc(new RectF(x+width-width/2,y,x+width+width/2,y+height),270,180,true,p);
        g.restore();

    }



    public void setWidth(float w)
    {

        width=w;
    }

    public void setHeight(float w)
    {

        height=w;
    }


    public void suonaEsploditore()
    {
        ambiente.suonaEsploditore();
    }
    public void suonaEsploditoreUno()
    {
        ambiente.suonaEsploditoreUno();
    }


    public void scoppia(int i)throws EsplosaException
    {


        synchronized (ambiente)
        {
            try
            {
                if (!ambiente.esplosa(i) && ambiente.interno(i, x, y, getWidth() / 2) && !colpita(ambiente.getBomb(i)))
                {
                    addColpita(ambiente.getBomb(i));
                    if (ambiente.colpisciBomba(i, danno) == 0)
                    {
                        ambiente.esplodiBomba(i);
                        puntiTotali += ambiente.getPunti(ambiente.getBomb(i));
                        throw new EsplosaException(true);


                    }
                    else
                    {
                        ambiente.suonaColpitore(i);
                        throw new EsplosaException(false);

                    }

                }
            }
            catch (IndexOutOfBoundsException e)
            {
                //Log.d("Errore",e.getMessage());

            }
            catch (NullPointerException e)
            {

            }
        }
    }






        public void muovi() throws InterruptedException
        {

            setX(x+(float)(livello*Math.cos(angolo)));
            setY(y+(float)(livello*Math.sin(angolo)));
            for(int i = 0; i < numBombe(); i++)
            {
                 scoppia(i);

            }




        }


}
