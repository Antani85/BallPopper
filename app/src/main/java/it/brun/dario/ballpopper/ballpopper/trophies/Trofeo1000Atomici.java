package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 12/05/17.
 */
public class Trofeo1000Atomici extends Trofeo
{
    public static final String LACODIFICA="AGIMAI";

    public Trofeo1000Atomici(Activity act)
    {
        super(act.getResources().getString(R.string.trofeo1000atomici),act.getResources().getString(R.string.trofeo1000atomicidescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        return (Giocatore.inventario().getAtomiciEsplosi()>=1000);
    }
}
