package com.example.listview2;

public class Data {


    String Name;
    String Mail;
    String DNI;
    String Addres;

    public Data(String name, String mail, String DNI, String addres) {
        Name = name;
        Mail = mail;
        DNI = DNI;
        Addres = addres;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getAddres() {
        return Addres;
    }

    public void setAddres(String addres) {
        Addres = addres;
    }

    public Data() {
    }

    @Override
    public String toString() {
        return Name +"              " +Mail+"              " + DNI+"              " + Addres;
    }

}
