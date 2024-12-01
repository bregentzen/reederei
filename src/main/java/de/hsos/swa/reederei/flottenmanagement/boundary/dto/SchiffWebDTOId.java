package de.hsos.swa.reederei.flottenmanagement.boundary.dto;

public class SchiffWebDTOId extends SchiffWebDTO{
    private Long id;

    public SchiffWebDTOId(){
    }

    public SchiffWebDTOId(Long id, String name, boolean isGebucht) {
        super(name, isGebucht);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
