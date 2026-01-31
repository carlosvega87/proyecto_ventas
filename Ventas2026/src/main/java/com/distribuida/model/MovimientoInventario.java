package com.distribuida.model;

import jakarta.persistence.*;

@Entity
@Table(name="movimientos_inventario")
public class MovimientoInventario {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name = "id_movimiento")
        private int idMovimiento;
        @Column (name = "tipo")
        private String tipo;
        @Column (name = "cantidad")
        private int cantidad;
        @Column (name = "fecha")
        private String fechaMovimiento;

        @ManyToOne
        @JoinColumn(name = "id_producto")
        private Productos productos;
public MovimientoInventario(){}
    public MovimientoInventario(int idMovimiento, String tipo, int cantidad, String fechaMovimiento, Productos productos) {
        this.idMovimiento = idMovimiento;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fechaMovimiento = fechaMovimiento;
        this.productos = productos;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "MovimientoInventario{" +
                "idMovimiento=" + idMovimiento +
                ", tipo='" + tipo + '\'' +
                ", cantidad=" + cantidad +
                ", fechaMovimiento='" + fechaMovimiento + '\'' +
                ", productos=" + productos +
                '}';
    }
}

