package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 15/05/17.
 */
public class TrofeoDistruttore extends Trofeo
{
    public static final String LACODIFICA="AGDIER";

    public TrofeoDistruttore(Activity act)
    {
        super(act.getResources().getString(R.string.trofeodistruttore),act.getResources().getString(R.string.trofeodistruttoredescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        return Giocatore.inventario().getPalleScoppiate()>=100000;
    }
}
