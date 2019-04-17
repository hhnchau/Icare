package ptt.vn.icaremobileapp.utils;

import android.support.v4.app.Fragment;

import ptt.vn.icaremobileapp.enums.Fragmentez;

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
