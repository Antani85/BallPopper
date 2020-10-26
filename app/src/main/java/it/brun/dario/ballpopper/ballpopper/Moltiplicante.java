package it.brun.dario.ballpopper.ballpopper;

import it.brun.dario.ballpopper.ballpopper.bullets.SottoPalla;
import it.brun.dario.ballpopper.ballpopper.balls.Bomba;

/**
 * Created by dario on 29/04/17.
 */
public interface Moltiplicante
{
    public void addColpita(Bomba b);
    public void rimuovi(SottoPalla p);
    public void aggiungi(SottoPalla p);
    public void moltiplica(int i);
}
