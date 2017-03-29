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
public class AccessBackoffice {
    private long user_id;
    private String nickname;
    private String password;
    private boolean isAdmin;
    private boolean isBlocked;
    private boolean hasChanged;

    public AccessBackoffice() {
        this.user_id = 0;
        this.nickname = "";
        this.password = "";
        this.isAdmin = false;
        this.isBlocked = false;
        this.hasChanged = true;
    }

    public AccessBackoffice(long user_id, String nickname, String password, boolean isAdmin, boolean isBlocked, boolean hasChanged) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isBlocked = isBlocked;
        this.hasChanged = hasChanged;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean isHasChanged() {
        return hasChanged;
    }

    public void setHasChanged(boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.user_id ^ (this.user_id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.nickname);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + (this.isAdmin ? 1 : 0);
        hash = 59 * hash + (this.isBlocked ? 1 : 0);
        hash = 59 * hash + (this.hasChanged ? 1 : 0);
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
        final AccessBackoffice other = (AccessBackoffice) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.isAdmin != other.isAdmin) {
            return false;
        }
        if (this.isBlocked != other.isBlocked) {
            return false;
        }
        if (this.hasChanged != other.hasChanged) {
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
    
   @Override
    public String toString() {
        return "AccessBackoffice{" + "user_id=" + user_id + ", nickname=" + nickname + ", password=" + password + ", isAdmin=" + isAdmin + ", isBlocked=" + isBlocked + ", hasChanged=" + hasChanged + '}';
    }
    
}
