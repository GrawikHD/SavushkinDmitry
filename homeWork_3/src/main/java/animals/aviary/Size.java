package animals.aviary;

public enum Size {
    SMALL(1),
    MEDIUM(2),
    LARGE(3),
    HOUG(4);

    private int value;

    Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
