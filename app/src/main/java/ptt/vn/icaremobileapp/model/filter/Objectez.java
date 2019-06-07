package ptt.vn.icaremobileapp.model.filter;

public enum Objectez {
    DICHVU(25401),
    BHYT(25402),
    BHTN(25403);

    private int value;

    public int getValue() {
        return this.value;
    }

    Objectez(int value) {
        this.value = value;
    }
}
