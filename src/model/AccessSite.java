/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class AccessSite {
    private long user_id;
    private String nickname;
    private String password;

    public AccessSite() {
        this.user_id = 0;
        this.nickname = "";
        this.password = "";
    }
        
    public AccessSite(long user_id, String nickname, String password) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.password = password;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccessSite{" + "user_id=" + user_id + ", nickname=" + nickname + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (this.user_id ^ (this.user_id >>> 32));
        hash = 71 * hash + Objects.hashCode(this.nickname);
        hash = 71 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccessSite other = (AccessSite) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }
    
    
}
