package it.brun.dario.ballpopper.pseudoviews;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 12/04/17.
 */
public class PannelloPotenziamenti
{
    private float BASE;
    private Bonus selectedBonus;

    public void azzeraSelectedBonus()
    {
        selectedBonus=null;
    }
    public void setSelectedBonus()
    {


        if(posizioneY>=baseY&&posizioneY<=baseY+Bonus.getDIAMETROBASE()&&posizioneX>=baseX&&posizioneX<=baseX+Bonus.getDIAMETROBASE())
        {
            selectedBonus=inventario.getBonusCorrente();
            return;
        }


        if(!(posizioneY>=baseY+BASE&&posizioneY<=baseY+BASE+Bonus.getDIAMETROBASE()))
        {
            selectedBonus=null;
            return;
        }


        int n=inventario.numPotenziamenti();
        for(int i=0;i<n;i++)
        {
            if(posizioneX>=baseX+i*BASE&&posizioneX<=baseX+i*BASE+Bonus.getDIAMETROBASE()&&!inventario.getPotenziamento(i).equals(inventario.getBonusCorrente()))
            {


                selectedBonus=inventario.getPotenziamento(i);

                return;
            }

        }
        selectedBonus=null;
        return;
    }

    public void setBonusCorrente()
    {


        if(posizioneY>=baseY&&posizioneY<=baseY+Bonus.getDIAMETROBASE()&&posizioneX>=baseX&&posizioneX<=baseY+Bonus.getDIAMETROBASE()&&inventario.getBonusCorrente()==null)
        {
            inventario.setBonusCorrente(selectedBonus);
        }

        else if(posizioneY>=baseY+BASE&&posizioneY<=baseY+BASE+Bonus.getDIAMETROBASE())
        {
            int n=inventario.numPotenziamenti();
            for(int i=0;i<n;i++)
            {
                if(posizioneX>=baseX+i*BASE&&posizioneX<=baseX+i*BASE+Bonus.getDIAMETROBASE()&&inventario.getPotenziamento(i).equals(inventario.getBonusCorrente())&&inventario.getBonusCorrente().equals(selectedBonus))
                {
                    inventario.setBonusCorrente(null);
                    return;

                }
            }

        }



    }

    public boolean setPosizioni(float px,float py)
    {
        if(px>=baseX&&px<=baseX+dimmax&&py>=baseY&&py<=baseY+BASE+Bonus.getDIAMETROBASE())
        {
            posizioneX=px;
            posizioneY=py;


            return true;
        }
        return false;
    }
    private InventarioMunizioni inventario;
    public PannelloPotenziamenti(InventarioMunizioni inv,float bx,float by)
    {
        BASE=(Bonus.getDIAMETROBASE()*5)/3;
        inventario=inv;


        baseX=Ambiente.getWIDTH()*0.14f;
        baseY=by-(BASE+Bonus.getDIAMETROBASE());


    }



    private float dimmax;
    private float posizioneX;
    private float posizioneY;
    private float baseX;
    private float baseY;


    public float[] getDimensioni()
    {
        return new float[]{baseX,baseY};
    }
    public void disegnaPannelloPotenziamenti(Canvas g,Paint p)
    {

        int n=inventario.numPotenziamenti();
        int m=inventario.getMaxPotenziamenti();
        int h=n;
        if(m>h)
        {
            h=m;
        }

        p.setColor(Color.RED);
        p.setShader(null);
        g.drawOval(new RectF(baseX, baseY, baseX +Bonus.getDIAMETROBASE(), baseY + Bonus.getDIAMETROBASE()), p);
        float base=0;
        dimmax=baseX+base;
        Bonus selezion=null;

        for(int i=0;i<h;i++)
        {

            p.setShader(null);
            p.setColor(Color.BLACK);
            if(i<m)
            {
                g.drawOval(new RectF(baseX + base, baseY + BASE, baseX + base + Bonus.getDIAMETROBASE(), baseY + BASE + Bonus.getDIAMETROBASE()), p);
            }
            if(i<n)
            {
                try
                {
                    if ((!inventario.getPotenziamento(i).equals(inventario.getBonusCorrente())) && (!inventario.getPotenziamento(i).equals(selectedBonus)))
                    {
                        inventario.getPotenziamento(i).disegnaBonusBig(g, p, baseX + base, baseY + BASE);
                    }
                    else if ((inventario.getPotenziamento(i).equals(inventario.getBonusCorrente())) && (!inventario.getPotenziamento(i).equals(selectedBonus)))
                    {
                        inventario.getPotenziamento(i).disegnaBonusBig(g, p, baseX, baseY);
                    }
                    else
                    {
                        selezion = inventario.getPotenziamento(i);
                    }

                }
                catch (NullPointerException e)
                {
                    Log.d("errore", "" + e);
                    break;
                }
            }
            base += BASE;
            dimmax += base;
        }
        if(selezion!=null)
        {
            selezion.disegnaBonusBig(g, p, posizioneX - Bonus.getDIAMETROBASE() / 2, posizioneY - Bonus.getDIAMETROBASE() / 2);
        }
        dimmax+=Bonus.getDIAMETROBASE();
    }



}
