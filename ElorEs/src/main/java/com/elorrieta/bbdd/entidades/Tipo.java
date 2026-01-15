package com.elorrieta.bbdd.entidades;

public class Tipo {
	
    private Integer id;
    private String name;
    private String nameEu;

    public Tipo() {}

    public Tipo(String name, String nameEu) {
        this.name = name;
        this.nameEu = nameEu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEu() {
        return nameEu;
    }

    public void setNameEu(String nameEu) {
        this.nameEu = nameEu;
    }

}
