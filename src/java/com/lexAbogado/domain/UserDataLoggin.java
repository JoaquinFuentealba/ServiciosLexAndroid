/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lexAbogado.domain;

/**
 *
 * @author jfuentealba
 */
public class UserDataLoggin {
    
    private String user;
    private String pass;
    private String idRol;
    private String correo;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String column) {
        this.idRol = column;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "UserDataLoggin{" + "user=" + user + ", pass=" + pass + ", idRol=" + idRol + ", correo=" + correo + '}';
    }
    
    
}
