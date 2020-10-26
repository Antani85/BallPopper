package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.bonus.BonusPIU1;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class LivelloUno extends Livello
{

    public void creaLanciate(Ambiente a)
    {
        Bomba b=new  Bomba(null,0,0,0,0,0);
        Bomba b1=new Bomba(null,0,0,0,0,0,new BonusPIU1());

        resettaLanciate();
        //addLanciata(generaBomba(a,new BombaVeloce(null,0,0,0,0,0),Ambiente.getPuntoUscita(0)));

        for(int i=0;i<2;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(0)));
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(2)));

        }

        addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(4)));
        for(int i=0;i<2;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(0)));
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(2)));

        }

    }



    public LivelloUno(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,1,invmun,3000,120000);
        setPuntiBonus(1);
    }


}
