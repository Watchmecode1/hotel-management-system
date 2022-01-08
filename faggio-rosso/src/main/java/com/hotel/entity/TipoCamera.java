package com.hotel.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class TipoCamera {

    @Id
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private BigDecimal prezzo;

    @OneToMany
    @JoinColumn(name = "tipo_camera_id")
    private Set<Camera> camere;

    public enum Tipo {
        DOPPIA(2), TRIPLA(3), QUADRUPLA(4), QUINTUPLA(5);

        private int persone;

        Tipo(int persone) {
            this.persone = persone;
        }

        public int getPersone() { return persone; }
    }
    
    public TipoCamera() {}

    public TipoCamera(Tipo tipo, BigDecimal prezzo) {
        this.tipo = tipo;
        this.prezzo = prezzo;
    }

    public Tipo getTipo() { return tipo; }

    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public BigDecimal getPrezzo() { return prezzo; }

    public void setPrezzo(BigDecimal prezzo) { this.prezzo = prezzo; }

    public Set<Camera> getCamere() { return camere; }

    public void setCamere(Set<Camera> camere) { this.camere = camere; }
}