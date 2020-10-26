package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU1;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU2;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class LivelloQuattro extends Livello
{
    public void creaLanciate(Ambiente a)
    {
        Bomba b=new Bomba(null,0,0,0,0,0);
        Bomba b1=new Bomba(null,0,0,0,0,0,new BonusPIU1());
        Bomba b2=new Bomba(null,0,0,0,0,0,new BonusPIU2());

        resettaLanciate();
        for(int i=0;i<15;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%3)));
        }
        double dou=Math.random();
        if(dou<0.5)
        {
            addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(4)));
        }
        else
        {
            addLanciata(generaBomba(a, b2, Ambiente.getPuntoUscita(4)));

        }
        for(int i=0;i<15;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%3+5)));
        }
    }


    public LivelloQuattro(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,4, invmun,1500,120000);
        setPuntiBonus(2);
    }



}
