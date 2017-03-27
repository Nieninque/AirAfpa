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
public class Flight {

    // name columns in table flights
    private long id;
    private String departing_aita;
    private String departing_city;
    private String departing_country;
    private String arrival_aita;
    private String arrival_city;
    private String arrival_country;
    private String departing_hour;
    private int duration;
    private double price;
    private long id_pilot;
    private long id_copilot;
    private long id_staff1;
    private long id_staff2;
    private long id_staff3;
    private boolean planned;
    private InOut detailsAirport;

    // this construct create flight empty 
    public Flight() {
        this.id = 0;
        this.departing_aita = "";
        this.arrival_aita = "";
        this.departing_hour = "";
        this.duration = 0;
        this.price = 0;
        this.id_pilot = 0;
        this.id_copilot = 0;
        this.id_staff1 = 0;
        this.id_staff2 = 0;
        this.id_staff3 = 0;
        this.planned = false;
        
        this.detailsAirport = new InOut();
        this.departing_city = "";
        this.departing_country = "";
        this.arrival_city = "";
        this.arrival_country = "";
        
    }
    // this construct create flight full

    public Flight(long id, String departing_aita, String departing_city, 
            String departing_country, String arrival_aita, String arrival_city,
            String arrival_country, String departing_hour, int duration, 
            double price, long id_pilot, long id_copilot, long id_staff1, 
            long id_staff2, long id_staff3, boolean planned) {
        this.id = id;
        this.departing_aita = departing_aita;
        this.departing_city = departing_city;
        this.departing_country = departing_country;
        this.arrival_aita = arrival_aita;
        this.arrival_city = arrival_city;
        this.arrival_country = arrival_country;
        this.departing_hour = departing_hour;
        this.duration = duration;
        this.price = price;
        this.id_pilot = id_pilot;
        this.id_copilot = id_copilot;
        this.id_staff1 = id_staff1;
        this.id_staff2 = id_staff2;
        this.id_staff3 = id_staff3;
        this.planned = planned;
//        this.detailsAirport = detailsAirport;
    }



    public InOut getDetailsAirport() {
        return detailsAirport;
    }

    public void setDetailsAirport(String departingCity,String departingCountry,String arivalCity, String arivalCountry) {
        this.detailsAirport.arrivalCity=arivalCity;
        this.detailsAirport.arrivalCountry=arivalCountry;
        this.detailsAirport.departingCity=departingCity;
        this.detailsAirport.departingCountry=departingCountry;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeparting_aita() {
        return departing_aita;
    }

    public void setDeparting_aita(String departing_aita) {
        this.departing_aita = departing_aita;
    }

    public String getDeparting_city() {
        return departing_city;
    }

    public void setDeparting_city(String departing_city) {
        this.departing_city = departing_city;
    }

    public String getDeparting_country() {
        return departing_country;
    }

    public void setDeparting_country(String departing_country) {
        this.departing_country = departing_country;
    }

    public String getArrival_aita() {
        return arrival_aita;
    }

    public void setArrival_aita(String arrival_aita) {
        this.arrival_aita = arrival_aita;
    }

    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

    public String getArrival_country() {
        return arrival_country;
    }

    public void setArrival_country(String arrival_country) {
        this.arrival_country = arrival_country;
    }

    public String getDeparting_hour() {
        return departing_hour;
    }

    public void setDeparting_hour(String departing_hour) {
        this.departing_hour = departing_hour;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getId_pilot() {
        return id_pilot;
    }

    public void setId_pilot(long id_pilot) {
        this.id_pilot = id_pilot;
    }

    public long getId_copilot() {
        return id_copilot;
    }

    public void setId_copilot(long id_copilot) {
        this.id_copilot = id_copilot;
    }

    public long getId_staff1() {
        return id_staff1;
    }

    public void setId_staff1(long id_staff1) {
        this.id_staff1 = id_staff1;
    }

    public long getId_staff2() {
        return id_staff2;
    }

    public void setId_staff2(long id_staff2) {
        this.id_staff2 = id_staff2;
    }

    public long getId_staff3() {
        return id_staff3;
    }

    public void setId_staff3(long id_staff3) {
        this.id_staff3 = id_staff3;
    }

    public boolean isPlanned() {
        return planned;
    }

    public void setPlanned(boolean planned) {
        this.planned = planned;
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", departing_aita=" + departing_aita + ", departing_city=" + departing_city + ", departing_country=" + departing_country + ", arrival_aita=" + arrival_aita + ", arrival_city=" + arrival_city + ", arrival_country=" + arrival_country + ", departing_hour=" + departing_hour + ", duration=" + duration + ", price=" + price + ", id_pilot=" + id_pilot + ", id_copilot=" + id_copilot + ", id_staff1=" + id_staff1 + ", id_staff2=" + id_staff2 + ", id_staff3=" + id_staff3 + ", planned=" + planned + ", detailsAirport=" + detailsAirport + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 47 * hash + Objects.hashCode(this.departing_aita);
        hash = 47 * hash + Objects.hashCode(this.arrival_aita);
        hash = 47 * hash + Objects.hashCode(this.detailsAirport);
        hash = 47 * hash + Objects.hashCode(this.departing_hour);
        hash = 47 * hash + this.duration;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 47 * hash + (int) (this.id_pilot ^ (this.id_pilot >>> 32));
        hash = 47 * hash + (int) (this.id_copilot ^ (this.id_copilot >>> 32));
        hash = 47 * hash + (int) (this.id_staff1 ^ (this.id_staff1 >>> 32));
        hash = 47 * hash + (int) (this.id_staff2 ^ (this.id_staff2 >>> 32));
        hash = 47 * hash + (int) (this.id_staff3 ^ (this.id_staff3 >>> 32));
        hash = 47 * hash + (this.planned ? 1 : 0);
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
        final Flight other = (Flight) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.duration != other.duration) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (this.id_pilot != other.id_pilot) {
            return false;
        }
        if (this.id_copilot != other.id_copilot) {
            return false;
        }
        if (this.id_staff1 != other.id_staff1) {
            return false;
        }
        if (this.id_staff2 != other.id_staff2) {
            return false;
        }
        if (this.id_staff3 != other.id_staff3) {
            return false;
        }
        if (this.planned != other.planned) {
            return false;
        }
        if (!Objects.equals(this.departing_aita, other.departing_aita)) {
            return false;
        }
        if (!Objects.equals(this.arrival_aita, other.arrival_aita)) {
            return false;
        }
        if (!Objects.equals(this.departing_hour, other.departing_hour)) {
            return false;
        }
        if (!Objects.equals(this.detailsAirport, other.detailsAirport)) {
            return false;
        }
        return true;
    }



    public class InOut {

        public String departingCity= "";
        public String departingCountry= "";
        public String arrivalCity = "";
        public String arrivalCountry = "";

        @Override
        public String toString() {
            return "InOut{" + "departingCity=" + departingCity + ", departingCountry=" + departingCountry + ", arrivalCity=" + arrivalCity + ", arrivalCountry=" + arrivalCountry + '}';
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 37 * hash + Objects.hashCode(this.departingCity);
            hash = 37 * hash + Objects.hashCode(this.departingCountry);
            hash = 37 * hash + Objects.hashCode(this.arrivalCity);
            hash = 37 * hash + Objects.hashCode(this.arrivalCountry);
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
            final InOut other = (InOut) obj;
            if (!Objects.equals(this.departingCity, other.departingCity)) {
                return false;
            }
            if (!Objects.equals(this.departingCountry, other.departingCountry)) {
                return false;
            }
            if (!Objects.equals(this.arrivalCity, other.arrivalCity)) {
                return false;
            }
            if (!Objects.equals(this.arrivalCountry, other.arrivalCountry)) {
                return false;
            }
            return true;
        }

    }

}
