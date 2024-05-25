package model;

public class User {

    private String nom;

    private String tel;
    private String email;

    public User(String nom,  String tel) {
        this.nom = nom;

        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
