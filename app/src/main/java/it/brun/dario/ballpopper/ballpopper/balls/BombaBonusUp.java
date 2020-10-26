package it.brun.dario.ballpopper.ballpopper.balls;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.views.MessageView;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 14/05/17.
 */
public class BombaBonusUp extends Bomba
{


    private static final String BONUS="BONUS";
    private static final String BUP="UP";

    public static final String UP="BONUS UP";
    public BombaBonusUp(Ambiente a, float ix, float iy, int c, float xx, float yy)
    {
        super(a,ix,iy,c,xx,yy);
        impostaHp(25);
        setPunti(0);
        setDiametro(Bomba.getDIAMETROBASE()*2f);

    }
    public BombaBonusUp(Ambiente a,float ix,float iy, int c,float xx,float yy,Bonus bon)
    {
        super(a,ix,iy,c,xx,yy,bon);
        impostaHp(25);
        setPunti(0);
        setDiametro(Bomba.getDIAMETROBASE()*2f);

    }



    public  Bomba getBomba (Ambiente a,float ix,float iy, int c,float xx,float yy)
    {
        return new BombaBonusUp(a,ix,iy,c,xx,yy,getBonus());

    }

    public void disegnaBomba(Canvas g, Paint p)
    {


        p.setShader(null);
        p.setColor(getColore());
        if (getRaggio() == 0)
        {




            p.setShader(null);
            p.setColor(Color.CYAN);


            setCentro();

            g.drawOval(new RectF(getX(), getY(), getX()+getDiametro(),getY()+getDiametro()), p);





            Giocatore.setTypeface(p);
            /*
            MessageView.setDimensione(BONUS,p,getDiametro()*0.5f,getDiametro()*0.5f);
            Rect bounds=new Rect();
            p.getTextBounds(BONUS,0,BONUS.length(),bounds);
            p.setColor(Color.BLACK);
            p.setShader(null);
            g.drawText(BONUS,getCentroX()-bounds.width()/2,getCentroY()+bounds.height()/2,p);
            */
            MessageView.setDimensione(BONUS,p,getDiametro()*0.8f,getDiametro()*0.8f);
            Rect bounds=new Rect();
            p.getTextBounds(BONUS,0,BONUS.length(),bounds);
            p.setColor(Color.BLACK);
            p.setShader(null);
            g.drawText(BONUS,getCentroX()-bounds.width()/2,getCentroY(),p);
            p.getTextBounds(BUP,0,BUP.length(),bounds);
            g.drawText(BUP,getCentroX()-bounds.width()/2,getCentroY()+bounds.height(),p);






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
