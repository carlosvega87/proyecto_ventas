package com.distribuida.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;
    @Column(name = "metodo_pago")
    private String metodoPago;
    @Column(name = "fecha_pago")
    private String fechaPago;

    // 🔹 Constructor vacío (REQUERIDO por JPA)
    public Pagos() {
    }

    public Pagos(int idPago, String metodoPago, String fechaPago) {
        this.idPago = idPago;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
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
                ", metodoPago='" + metodoPago + '\'' +
                ", fechaPago='" + fechaPago + '\'' +
                '}';
    }
}

