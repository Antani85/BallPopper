package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 12/05/17.
 */
public class Trofeo10000Perforanti extends Trofeo
{
    public static final String LACODIFICA="AGIPAD";

    public Trofeo10000Perforanti(Activity act)
    {
        super(act.getResources().getString(R.string.trofeo10000perforanti),act.getResources().getString(R.string.trofeo10000perforantidescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        return (Giocatore.inventario().getPerforantiSparati()>=10000);
    }
}
