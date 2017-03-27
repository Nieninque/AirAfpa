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
public class User {

    private long id;
    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String country;
    private String tel;
    private String mail;
    private int function;
    private InfoAdmin info;


    
    public User() {
        this.id = 0;
        this.firstname = "";
        this.lastname = "";
        this.address = "";
        this.city = "";
        this.country = "";
        this.tel = "";
        this.mail = "";
        this.function = 0;
        this.info =new InfoAdmin();
    }
    public User(long id, String firstname, String lastname, String address, String city, String country, String tel, String mail, int function) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.country = country;
        this.tel = tel;
        this.mail = mail;
        this.function = function;
        
        
        this.info =new InfoAdmin();
    }
    public InfoAdmin getInfo() {
        return info;
    }

    public void setInfo(boolean admin,boolean blocked, boolean hasChanged) {
        this.info.hasChanged=hasChanged;
        this.info.isAdmin=admin;
        this.info.isBlocked=blocked;
    }
    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public int getFunction() {
        return function;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFunction(int function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", address=" + address + ", city=" + city + ", country=" + country + ", tel=" + tel + ", mail=" + mail + ", function=" + function + ", info=" + info + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.firstname);
        hash = 29 * hash + Objects.hashCode(this.lastname);
        hash = 29 * hash + Objects.hashCode(this.address);
        hash = 29 * hash + Objects.hashCode(this.city);
        hash = 29 * hash + Objects.hashCode(this.country);
        hash = 29 * hash + Objects.hashCode(this.tel);
        hash = 29 * hash + Objects.hashCode(this.mail);
        hash = 29 * hash + this.function;
        hash = 29 * hash + Objects.hashCode(this.info);
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.function != other.function) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.tel, other.tel)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.info, other.info)) {
            return false;
        }
        return true;
    }




    public class InfoAdmin{
    
        public boolean isAdmin = false;
        public boolean isBlocked = false;
        public boolean hasChanged = true;

        @Override
        public String toString() {
            return "InfoAdmin{" + "isAdmin=" + isAdmin + ", isBlocked=" + isBlocked + ", hasChanged=" + hasChanged + '}';
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 47 * hash + (this.isAdmin ? 1 : 0);
            hash = 47 * hash + (this.isBlocked ? 1 : 0);
            hash = 47 * hash + (this.hasChanged ? 1 : 0);
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
            final InfoAdmin other = (InfoAdmin) obj;
            if (this.isAdmin != other.isAdmin) {
                return false;
            }
            if (this.isBlocked != other.isBlocked) {
                return false;
            }
            if (this.hasChanged != other.hasChanged) {
                return false;
            }
            return true;
        }
        
        
    
    }
    
    
}
