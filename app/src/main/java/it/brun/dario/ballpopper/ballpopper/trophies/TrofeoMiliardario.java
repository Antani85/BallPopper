package it.brun.dario.ballpopper.ballpopper.trophies;

import android.app.Activity;

import it.brun.dario.ballpopper.R;
import it.brun.dario.ballpopper.ballpopper.Giocatore;
import it.brun.dario.ballpopper.ballpopper.bullets.Palla;
import it.brun.dario.ballpopper.levels.Livello;

/**
 * Created by dario on 12/05/17.
 */
public class TrofeoMiliardario extends Trofeo
{
    public static final String LACODIFICA="AGOIIM";

    public TrofeoMiliardario(Activity act)
    {
        super(act.getResources().getString(R.string.trofeomiliardario),act.getResources().getString(R.string.trofeomiliardariodescription));
        setCODIFICA_TROFEO(LACODIFICA);

    }

    @Override
    public boolean condizione(Palla palla, Livello lev)
    {

        return (Giocatore.inventario().getPuntiTotali()>=1000000000);
    }
}
