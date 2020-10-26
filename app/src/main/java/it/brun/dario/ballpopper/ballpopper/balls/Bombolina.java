package it.brun.dario.ballpopper.ballpopper.balls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 11/05/17.
 */
public class Bombolina extends Bomba
{


    public Bombolina(Ambiente a, float ix, float iy, int c, float xx, float yy)
    {
        super(a,ix,iy,c,xx,yy);
        impostaHp(15);
        setPunti(20);
        setDiametro(Bomba.getDIAMETROBASE()*1.5f);

    }
    public Bombolina(Ambiente a,float ix,float iy, int c,float xx,float yy,Bonus bon)
    {
        super(a,ix,iy,c,xx,yy,bon);
        impostaHp(15);
        setPunti(20);
        setDiametro(Bomba.getDIAMETROBASE()*1.5f);

    }



    public  Bomba getBomba (Ambiente a,float ix,float iy, int c,float xx,float yy)
    {
        return new Bombolina(a,ix,iy,c,xx,yy,getBonus());

    }

    public void disegnaBomba(Canvas g, Paint p)
    {


        p.setShader(null);
        p.setColor(getColore());
        if (getRaggio() == 0)
        {




            p.setShader(null);
            p.setColor(getColore());


            setCentro();

            g.drawOval(new RectF(getX(), getY(), getX()+getDiametro(),getY()+getDiametro()), p);



            p.setShader(new LinearGradient(getCentroX(), getCentroY(), getCentroX(), getCentroY()-getDiametro()/2, getColore(), Color.WHITE, Shader.TileMode.MIRROR));


            g.drawOval(new RectF(getCentroX()-getDiametro()/3,getCentroY()-getDiametro()/2,getCentroX()+getDiametro()/3,getCentroY()),p);

            disegnaBarraHp(g,p);


        }
        else if (getRaggio() <= getDiametro())
        {


            float x1=getCentroX()-getRaggio()/2;
            float y1=getCentroY()-getRaggio()/2;

            g.drawOval(new RectF(x1, y1, x1+getRaggio(), y1+getRaggio()),p);
            p.setShader(new LinearGradient(getCentroX(), getCentroY(), getCentroX(), getCentroY()-getRaggio()/2, getColore(),Color.WHITE, Shader.TileMode.MIRROR));
            g.drawOval(new RectF(getCentroX()-getRaggio()/3,getCentroY()-getRaggio()/2,getCentroX()+getRaggio()/3,getCentroY()),p);

            /*
            p.setShader(new LinearGradient(x1, y1, x1+getRaggio(), y1+getRaggio(), getColore(),Color.RED, Shader.TileMode.MIRROR));




            g.drawOval(new RectF(x1, y1, x1+getRaggio(),y1+getRaggio()), p);

            */

        }


    }




}
