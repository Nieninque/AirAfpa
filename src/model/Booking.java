/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Salim El Moussaoui <salim.elmoussaoui.afpa2017@gmail.com>
 */
public class Booking {
    private long id;
    private long user_id;
    private long flight_id;
    private int place;

    public Booking() {
        this.id = 0;
        this.user_id = 0;
        this.flight_id = 0;
        this.place = 0;
    }
    
    public Booking(long id, long user_id, long flight_id, int place) {
        this.id = id;
        this.user_id = user_id;
        this.flight_id = flight_id;
        this.place = place;
    }

    public long getId() {
        return id;
    }

    public long getUser_id() {
        return user_id;
    }

    public long getFlight_id() {
        return flight_id;
    }

    public int getPlace() {
        return place;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setFlight_id(long flight_id) {
        this.flight_id = flight_id;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Booking{" + "id=" + id + ", user_id=" + user_id + ", flight_id=" + flight_id + ", place=" + place + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + (int) (this.user_id ^ (this.user_id >>> 32));
        hash = 29 * hash + (int) (this.flight_id ^ (this.flight_id >>> 32));
        hash = 29 * hash + this.place;
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
        final Booking other = (Booking) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (this.flight_id != other.flight_id) {
            return false;
        }
        if (this.place != other.place) {
            return false;
        }
        return true;
    }
 
    
}
