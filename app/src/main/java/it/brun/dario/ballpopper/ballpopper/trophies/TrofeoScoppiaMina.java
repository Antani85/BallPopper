package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.bullets.MinaAntipalla;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 27/04/17.
 */
public class TrofeoScoppiaMina extends Trofeo
{
    public static final String LACODIFICA="AGIMAN";

    public TrofeoScoppiaMina(Activity act)
    {
        super(act.getResources().getString(R.string.trofeoscoppiamina),act.getResources().getString(R.string.trofeoscoppiaminadescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {
        if(palla==null)
        {
            return false;
        }
        if(!(palla instanceof MinaAntipalla))
        {
            return false;
        }
        return ((MinaAntipalla)palla).isDetonato();
    }
}
