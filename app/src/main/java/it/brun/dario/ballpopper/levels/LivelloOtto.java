package it.brun.dario.ballpopper.levels;

import it.brun.dario.ballpopper.ballpopper.balls.Bomba;
import it.brun.dario.ballpopper.ballpopper.balls.BombaBonusUp;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.InventarioMunizioni;
import it.brun.dario.ballpopper.activities.MainActivity;
import it.brun.dario.ballpopper.views.Ambiente;

/**
 * Created by dario on 10/04/17.
 */
public class LivelloOtto extends Livello
{

    public void creaLanciate(Ambiente a)
    {
        Bomba b=new Bomba(null,0,0,0,0,0);
        Bomba b1=new BombaBonusUp(null,0,0,0,0,0);

        resettaLanciate();
        for(int i=0;i<100;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }


        if(Giocatore.inventario().getMaxPotenziamenti()<3)
        {
            addLanciata(generaBomba(a, b1, Ambiente.getPuntoUscita(1)));
        }
        else
        {
            addLanciata(generaBomba(a, b, Ambiente.getPuntoUscita(1)));

        }

        for(int i=0;i<99;i++)
        {
            addLanciata(generaBomba(a,b,Ambiente.getPuntoUscita(i%8)));
        }
    }


    public LivelloOtto(MainActivity act, InventarioMunizioni invmun)
    {
        super(act,8, invmun,500,180000);
        setPuntiBonus(3);
    }



}
