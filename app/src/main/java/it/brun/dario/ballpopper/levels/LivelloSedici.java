package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU3;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU4;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU7;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.balls.MicroBomba;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 15/05/17.
 */
public class LivelloSedici extends Livello
{
    public void creaLanciate(Ambiente a)
    {

        Bomba b=new MicroBomba(null,0,0,0,0,0);
        Bomba mcb=new Bomba(null,0,0,0,0,0);
        Bomba b3=new Bomba(null,0,0,0,0,0,new BonusPIU3());

        Bomba b4=new Bomba(null,0,0,0,0,0,new BonusPIU4());

        Bomba b5=new Bomba(null,0,0,0,0,0,new BonusPIU7());

        resettaLanciate();

        for(int i=0;i<150;i++)
        {
            if(i%3==0)
            {
                addLanciata(generaBomba(a, mcb, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));

            }

        }

        double d=Math.random();
        if(d<0.33)
        {
            addLanciata(generaBomba(a, b4, Ambiente.getPuntoUscita(4)));
        }
        else if(d<0.66)
        {
            addLanciata(generaBomba(a, b3, Ambiente.getPuntoUscita(4)));
        }

        else
        {
            addLanciata(generaBomba(a, b5, Ambiente.getPuntoUscita(4)));
        }
        for(int i=0;i<149;i++)
        {

            if(i%3==0)
            {
                addLanciata(generaBomba(a, mcb, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));

            }




        }

    }


    public LivelloSedici(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,16,invmun,500,210000);
        setPuntiBonus(6);
    }




}
