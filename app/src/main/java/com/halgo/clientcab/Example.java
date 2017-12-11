package com.halgo.clientcab;

/**
 * Created by Halgo on 28/11/2017.
 */

public class Example {
        private String etat, date, heure;
        private Long idRdv;

        public Example() {
        }

        public Example(Long idRdv, String etat, String date, String heure) {
            this.etat = etat;
            this.date = date;
            this.heure = heure;
            this.idRdv=idRdv;
        }

        public Long getIdRdv(){return  idRdv;}

        public void setIdRdv(Long idRdv){this.idRdv=idRdv;}
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

