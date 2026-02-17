package com.distribuida.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_pago")
    private String fechaPago;

    // 🔹 Constructor vacío (REQUERIDO por JPA)
    public Pagos() {
    }

    public Pagos(int idPago, String descripcion, String fechaPago) {
        this.idPago = idPago;
        this.descripcion = descripcion;
        this.fechaPago = fechaPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Pagos{" +
                "idPago=" + idPago +
                ", descripcion='" + descripcion + '\'' +
                ", fechaPago='" + fechaPago + '\'' +
                '}';
    }
}

