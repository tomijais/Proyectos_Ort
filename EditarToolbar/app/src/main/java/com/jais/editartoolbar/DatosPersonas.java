package com.jais.editartoolbar;

public class DatosPersonas {

    public String nombre;
    public String dni;
    public String mail;
    public String address;


    public DatosPersonas()
    {
    }

    public DatosPersonas(String nombre, String dni, String mail, String address) {
        this.nombre = nombre;
        this.dni = dni;
        this.mail = mail;
        this.address = address;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getdni() {
        return dni;
    }

    public void setdni(String dni) {
        this.dni = dni;
    }

    public String getmail() {
        return mail;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return nombre +"       " +dni +"       " + mail +"       " + address;
    }

}