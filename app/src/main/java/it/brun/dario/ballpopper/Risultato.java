package it.brun.dario.ballpopper;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import it.brun.dario.ballpopper.ballpopper.bonus.Bonus;

/**
 * Created by dario on 18/04/17.
 */
public class Risultato implements Parcelable
{

    private int punti;
    private int palle;
    private int proiettili;
    private int livello;
    private int puntiLivello;
    private long time;

    public void addBonus (Bonus b)
    {
        bonus.add(b);
    }
    private List<Bonus> bonus;

    public int getPunti()
    {
        return punti;
    }
    public int getPalle()
    {
        return palle;
    }
    public int getProiettili()
    {
        return proiettili;
    }
    public int getLivello()
    {
        return livello;
    }
    public int getNumBonus()
    {
        return bonus.size();
    }
    public int getPuntiLivello()
    {
        return puntiLivello;
    }
    public Bonus getBonus(int i)
    {
        return bonus.get(i);
    }
    public long getTime()
    {
        return time;
    }
    public double getPrecisione()
    {
        return (double)palle/(double)proiettili;

    }
    public String getStringPrecisione()
    {
       double kk=Math.floor(getPrecisione()*10000);
        return ""+(kk/100)+"%";
    }
    public void addPunti(int p)
    {
        punti+=p;
    }
    public void addPalle(int p)
    {
        palle+=p;
    }
    public void addProiettili(int p)
    {
        proiettili+=p;
    }
    public void setPuntiLivello(int p)
    {
        puntiLivello=p;
    }
    public Risultato(int lev)
    {
        livello=lev;
        palle=0;
        punti=0;
        proiettili=0;
        bonus=new ArrayList<Bonus>();
        puntiLivello=0;
        time=0;
    }
    private Risultato(Parcel in)
    {

        livello=in.readInt();

        punti=in.readInt();
        puntiLivello=in.readInt();
        time=in.readLong();
        palle=in.readInt();
        proiettili=in.readInt();

        int n=in.readInt();
        bonus=new ArrayList<Bonus>();

        for(int i=0;i<n;i++)
        {

            try
            {
                String ss=in.readString();
                Bonus bb = Bonus.getBonusFromCodifica(ss);
                bonus.add(bb);
            }
            catch (Exception e)
            {
                continue;
            }

        }

    }
    public void setTime(long l)
    {
        time=l;
    }
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(livello);
        dest.writeInt(punti);
        dest.writeInt(puntiLivello);
        dest.writeLong(time);
        dest.writeInt(palle);
        dest.writeInt(proiettili);

        dest.writeInt(bonus.size());

        for(int i=0;i<bonus.size();i++)
        {
            dest.writeString(bonus.get(i).codifica());

        }


    }
    public int describeContents()
    {
        return 0;
    }
    public static final Parcelable.Creator<Risultato> CREATOR = new Parcelable.Creator<Risultato>()
    {
        public Risultato createFromParcel(Parcel in)
        {
            return new Risultato(in);
        }

        public Risultato[] newArray(int size)
        {
            return new Risultato[size];
        }
    };


}
