package de.hsos.swa.reederei.flottenmanagement.enitity;

public class Schiff {
    private long id;
    private String name;
    private boolean isGebucht;

    public Schiff() {
    }

    public Schiff(long id, String name, boolean isGebucht) {
        this.id = id;
        this.name = name;
        this.isGebucht = isGebucht;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGebucht() {
        return isGebucht;
    }

    public void setGebucht(boolean gebucht) {
        isGebucht = gebucht;
    }

    @Override
    public String toString() {
        return "Schiff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isGebucht=" + isGebucht +
                '}';
    }
}
