package ptt.vn.icaremobileapp.fragmentutils;

import android.support.v4.app.Fragment;

/**
 * Created by Chau Huynh on 4/17/2019.
 */

public class Fragmentoz {
    private Fragmentez fzg;
    private Fragment frg;

    public Fragmentoz(Fragmentez fzg, Fragment frg) {
        this.fzg = fzg;
        this.frg = frg;
    }

    public Fragmentez getFzg() {
        return fzg;
    }

    public void setFzg(Fragmentez fzg) {
        this.fzg = fzg;
    }

    public Fragment getFrg() {
        return frg;
    }

    public void setFrg(Fragment frg) {
        this.frg = frg;
    }
}
