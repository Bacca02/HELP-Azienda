/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ha.admin;

/**
 *
 * @author MAALFING
 */
public class Utente {

    public int iD;
    public String nome, cognome, username, email, tipo, telefono;

    public Utente(int iD, String nome, String cognome, String username, String email, String tipo, String telefono) {
        this.iD = iD;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.email = email;
        this.tipo = tipo;
        this.telefono = telefono;
    }

}
