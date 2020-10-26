package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.Bombolina;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU4;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU5;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU6;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.balls.MiniBomba;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 14/05/17.
 */
public class LivelloQuattordici extends Livello
{
    public void creaLanciate(Ambiente a)
    {

        Bomba b=new Bombolina(null,0,0,0,0,0);
        Bomba mcb=new MiniBomba(null,0,0,0,0,0);
        Bomba b3=new Bomba(null,0,0,0,0,0,new BonusPIU4());

        Bomba b4=new Bomba(null,0,0,0,0,0,new BonusPIU5());

        Bomba b5=new Bomba(null,0,0,0,0,0,new BonusPIU6());

        resettaLanciate();

        for(int i=0;i<150;i++)
        {
            if(i%5==0)
            {
                addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, mcb, Ambiente.getPuntoUscita(i%8)));

            }

        }

        double d=Math.random();
        if(d<0.25)
        {
            addLanciata(generaBomba(a, b4, Ambiente.getPuntoUscita(4)));
        }
        else if(d<0.75)
        {
            addLanciata(generaBomba(a, b3, Ambiente.getPuntoUscita(4)));
        }

        else
        {
            addLanciata(generaBomba(a, b5, Ambiente.getPuntoUscita(4)));
        }
        for(int i=0;i<150;i++)
        {

                addLanciata(generaBomba(a, mcb, Ambiente.getPuntoUscita(i%8)));




        }

    }


    public LivelloQuattordici(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,14,invmun,400,180000);
        setPuntiBonus(5);
    }




}
