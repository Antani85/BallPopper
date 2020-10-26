package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 16/05/17.
 */
public class TrofeoFantasmi extends Trofeo
{
    public static final String LACODIFICA="AGIMFA";

    public TrofeoFantasmi(Activity act)
    {
        super(act.getResources().getString(R.string.trofeofantasmi),act.getResources().getString(R.string.trofeofantasmidescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {

        if(palla==null)
        {
            return false;
        }

        return Giocatore.inventario().getPalleFantasmaScoppiate()>=100;
    }
}

