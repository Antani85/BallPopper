package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaApe;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.balls.MicroBomba;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 15/05/17.
 */
public class LivelloDiciotto extends Livello
{
    public void creaLanciate(Ambiente a)
    {

        Bomba b1=new MicroBomba(null,0,0,0,0,0);
        Bomba b2=new BombaApe(null,0,0,0,0,0);

        resettaLanciate();

        for(int i=0;i<100;i++)
        {
            if(i%3==0)
            {
                addLanciata(generaBomba(a, b2, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(i%8)));

            }

        }




    }


    public LivelloDiciotto(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,18,invmun,500,240000);
        setPuntiBonus(12);
    }




}
