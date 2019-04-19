package ptt.vn.icaremobileapp.drawer;


import ptt.vn.icaremobileapp.fragmentutils.Fragmentez;

public class DrawerModel {
    private Fragmentez fzg;
    private int icon;
    private String title;

    public DrawerModel() {
    }

    public DrawerModel(Fragmentez fzg, int icon, String title) {
        this.fzg = fzg;
        this.icon = icon;
        this.title = title;
    }

    public Fragmentez getFzg() {
        return fzg;
    }

    public void setFzg(Fragmentez fzg) {
        this.fzg = fzg;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

