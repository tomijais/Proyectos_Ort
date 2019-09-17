package com.jais.editartoolbar;

public class User {

    public String nombre;
    public String mail;
    public String address;
    public String dni;


    public User()
    {
    }

    public User(String nombre, String mail, String address, String dni) {
        this.nombre = nombre;
        this.mail = mail;
        this.address = address;
        this.dni = dni;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
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

    public String getdni() {
        return dni;
    }

    public void setdni(String dni) {
        this.dni = dni;
    }
    @Override
    public String toString() {
        return nombre +"       " + mail;
        // return nombre +"       " +mail +"       " + address +"       " + dni;
    }

}