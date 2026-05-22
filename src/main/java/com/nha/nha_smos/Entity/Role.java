package com.nha.nha_smos.Entity;

public class Role {
    private Integer id;
    private String name;
    private String code;
    private  Boolean status;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Role(Integer id, String name, String code, Boolean status) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.status = status;
    }


}
