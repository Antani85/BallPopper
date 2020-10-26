package it.brun.dario.ballpopper.ballpopper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dario on 25/04/17.
 */
public class Informazioni implements Parcelable
{
    private float pos;
    private int x;
    private int y;
    private int direzione;
    private boolean suonabile;

    public Informazioni(int xx,int yy,int dir,float p,boolean s)
    {
        x=xx;
        y=yy;
        direzione=dir;
        pos=p;
        suonabile=s;
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;

    }
    public int getDirezione()
    {
        return direzione;
    }
    public float getPos()
    {
        return pos;
    }
    public boolean getSuonabile()
    {
        return suonabile;
    }
    private Informazioni(Parcel in)
    {

        x=in.readInt();
        y=in.readInt();
        direzione=in.readInt();
        pos=in.readFloat();
        suonabile=in.readByte() != 0;

    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(x);
        dest.writeInt(y);
        dest.writeInt(direzione);
        dest.writeFloat(pos);
        dest.writeByte((byte) (suonabile ? 1 : 0));


    }
    public int describeContents()
    {
        return 0;
    }
    public static final Parcelable.Creator<Informazioni> CREATOR = new Parcelable.Creator<Informazioni>()
    {
        public Informazioni createFromParcel(Parcel in)
        {
            return new Informazioni(in);
        }

        public Informazioni[] newArray(int size)
        {
            return new Informazioni[size];
        }
    };

    public String toString()
    {
        StringBuilder builder=new StringBuilder();
        builder.append(x);
        builder.append(" ");
        builder.append(y);
        builder.append(" ");
        builder.append(direzione);
        builder.append(" ");
        builder.append(pos);
        builder.append(" ");
        builder.append(suonabile);
        return builder.toString();
    }

}
