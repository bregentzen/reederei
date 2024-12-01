package de.hsos.swa.reederei.flottenmanagement.boundary.dto;

public class SchiffWebDTO {
    private String name;
    private boolean isGebucht;

    public SchiffWebDTO(){
    }

    public SchiffWebDTO(String name, boolean isGebucht) {
        this.name = name;
        this.isGebucht = isGebucht;
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
}
