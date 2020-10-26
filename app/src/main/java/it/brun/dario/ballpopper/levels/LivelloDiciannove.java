package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaVeloce;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU4;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU5;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU7;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 16/05/17.
 */
public class LivelloDiciannove extends Livello
{
    public void creaLanciate(Ambiente a)
    {

        Bomba b=new BombaVeloce(null,0,0,0,0,0);
        Bomba b3=new Bomba(null,0,0,0,0,0,new BonusPIU4());

        Bomba b4=new Bomba(null,0,0,0,0,0,new BonusPIU5());

        Bomba b5=new Bomba(null,0,0,0,0,0,new BonusPIU7());

        resettaLanciate();

        for(int i=0;i<100;i++)
        {

            addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));



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
        for(int i=0;i<99;i++)
        {


                addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(i%8)));






        }

    }


    public LivelloDiciannove(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,19,invmun,600,180000);
        setPuntiBonus(7);
    }




}
