package it.brun.dario.ballpopper.ballpopper.balls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 19/04/17.
 */
public class BombaNegra extends Bomba
{
    private int hpMAX;
    public BombaNegra(Ambiente a, float ix, float iy, int c, float xx, float yy)
    {
        super(a,ix,iy,c,xx,yy);
        hpMAX=1000;
        impostaHp(1000);
        setPunti(1000);
        setDiametro(Bomba.getDIAMETROBASE()*2);

    }
    public BombaNegra(Ambiente a,float ix,float iy, int c,float xx,float yy,Bonus bon)
    {
        super(a,ix,iy,c,xx,yy,bon);
        hpMAX=1000;
        impostaHp(1000);
        setPunti(1000);
        setDiametro(Bomba.getDIAMETROBASE()*2);

    }



    public  Bomba getBomba (Ambiente a,float ix,float iy, int c,float xx,float yy)
    {
        return new BombaNegra(a,ix,iy,c,xx,yy,getBonus());

    }

    public void disegnaBomba(Canvas g, Paint p)
    {


        if (getRaggio() == 0)
        {


            p.setShader(null);
            p.setColor(Color.BLACK);
            g.drawOval(new RectF(getX(), getY(), getX()+getDiametro(),getY()+getDiametro()), p);

            float ango=360/10;
            float xx=getX()+getDiametro()/4;
            float yy=getY()+getDiametro()/4;
            float rr=Bomba.getDIAMETROBASE()/4;
            float cxx=getX()+Bomba.getDIAMETROBASE();
            float cyy=getY()+Bomba.getDIAMETROBASE();
            p.setColor(Color.WHITE);
            g.save();
            for(int i=0;i<10;i++)
            {
                if(!(hpMAX-getHp()>(hpMAX/10)*(i+1)))
                {
                    g.drawOval(new RectF(xx, yy, xx + rr, yy + rr), p);
                    g.rotate(ango, cxx, cyy);

                }

            }
            g.restore();
            disegnaBarraHp(g,p);






        }
        else if (getRaggio() <= getDiametro())
        {


            float x1=getCentroX()-getRaggio()/2;
            float y1=getCentroY()-getRaggio()/2;
            p.setShader(null);
            p.setColor(Color.BLACK);




            g.drawOval(new RectF(x1, y1, x1+getRaggio(),y1+getRaggio()), p);



        }


    }



}
