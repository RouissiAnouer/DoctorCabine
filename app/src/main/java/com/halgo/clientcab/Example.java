package com.halgo.clientcab;

/**
 * Created by Halgo on 28/11/2017.
 */

public class Example {
        private String etat, date, heure;

        public Example() {
        }

        public Example(String etat, String date, String heure) {
            this.etat = etat;
            this.date = date;
            this.heure = heure;
        }

        public String getEtat() {
            return etat;
        }

        public void setEtat(String etat) {
            this.etat = etat;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHeure() {
            return heure;
        }

        public void setHeure(String heure) {
            this.heure = heure;
        }
    }

