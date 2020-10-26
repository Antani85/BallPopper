package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaBonusUp;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.ballpopper.balls.MicroBomba;
import it.brun.dario.ballpopper.ballpopper.balls.MiniBomba;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 15/05/17.
 */
public class LivelloDiciassette extends Livello
{
    public void creaLanciate(Ambiente a)
    {

        Bomba b1=new MicroBomba(null,0,0,0,0,0);
        Bomba b2=new MiniBomba(null,0,0,0,0,0);
        Bomba b3= new BombaBonusUp(null,0,0,0,0,0);

        resettaLanciate();

        for(int i=0;i<100;i++)
        {
            if(i%5==0)
            {
                addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, b2, Ambiente.getPuntoUscita(i%8)));

            }

        }

        if(Giocatore.inventario().getMaxPotenziamenti()<4)
        {
            addLanciata(generaBomba(a, b3, Ambiente.getPuntoUscita(1)));
        }
        else
        {
            addLanciata(generaBomba(a, b2, Ambiente.getPuntoUscita(1)));

        }
        for(int i=0;i<149;i++)
        {

            if(i%5==0)
            {
                addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(i%8)));

            }
            else
            {
                addLanciata(generaBomba(a, b2, Ambiente.getPuntoUscita(i%8)));

            }




        }

    }


    public LivelloDiciassette(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,17,invmun,600,210000);
        setPuntiBonus(6);
    }




}
