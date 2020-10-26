package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU5;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU6;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.balls.MiniBomba;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 14/05/17.
 */
public class LivelloTredici extends Livello
{
    public void creaLanciate(Ambiente a)
    {

        Bomba b=new Bomba(null,0,0,0,0,0);
        Bomba mcb=new MiniBomba(null,0,0,0,0,0);
        Bomba b3=new Bomba(null,0,0,0,0,0,new BonusPIU5());

        Bomba b4=new Bomba(null,0,0,0,0,0,new BonusPIU6());

        resettaLanciate();

        for(int i=0;i<100;i++)
        {
            if(i%3==0)
            {
                addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, mcb, Ambiente.getPuntoUscita(i%8)));

            }

        }

        double d=Math.random();
        if(d<0.5)
        {
            addLanciata(generaBomba(a, b3, Ambiente.getPuntoUscita(4)));
        }

        else
        {
            addLanciata(generaBomba(a, b4, Ambiente.getPuntoUscita(4)));
        }
        for(int i=0;i<100;i++)
        {
            if(i%3==0)
            {
                addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, mcb, Ambiente.getPuntoUscita(i%8)));

            }


        }

    }


    public LivelloTredici(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,13,invmun,500,180000);
        setPuntiBonus(5);
    }




}
