package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.balls.BombaAtomica;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 27/04/17.
 */
public class TrofeoScoppiaAtomica extends Trofeo
{
    public static final String LACODIFICA="AGIMAC";

    public TrofeoScoppiaAtomica(Activity act)
    {
        super(act.getResources().getString(R.string.trofeoscoppiaatomica),act.getResources().getString(R.string.trofeoscoppiaatomicadescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        if(!(palla instanceof BombaAtomica))
        {
            return false;
        }
        return ((BombaAtomica)palla).isDetonato();
    }
}
