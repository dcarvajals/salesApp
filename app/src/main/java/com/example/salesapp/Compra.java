package com.example.salesapp;

public class Compra {
    private String numero_telefono = "";
    private String codigo_pais = "";
    private String cedula = "";
    private String direccion_referencia = "";
    private String respuesta_url = "";
    private String monto_pagar = "";
    private String importe_con_impuesto = ""; // amount - tax
    private String importe_sin_impuesto = "";
    private String impuesto = "";
    private String id_transaccion = "";

    public Compra() { }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getCodigo_pais() {
        return codigo_pais;
    }

    public void setCodigo_pais(String codigo_pais) {
        this.codigo_pais = codigo_pais;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion_referencia() {
        return direccion_referencia;
    }

    public void setDireccion_referencia(String direccion_referencia) {
        this.direccion_referencia = direccion_referencia;
    }

    public String getRespuesta_url() {
        return respuesta_url;
    }

    public void setRespuesta_url(String respuesta_url) {
        this.respuesta_url = respuesta_url;
    }

    public String getMonto_pagar() {
        return monto_pagar;
    }

    public void setMonto_pagar(String monto_pagar) {
        this.monto_pagar = monto_pagar;
    }

    public String getImporte_con_impuesto() {
        return importe_con_impuesto;
    }

    public void setImporte_con_impuesto(String importe_con_impuesto) {
        this.importe_con_impuesto = importe_con_impuesto;
    }

    public String getImporte_sin_impuesto() {
        return importe_sin_impuesto;
    }

    public void setImporte_sin_impuesto(String importe_sin_impuesto) {
        this.importe_sin_impuesto = importe_sin_impuesto;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getId_transaccion() {
        return id_transaccion;
    }

    public void setId_transaccion(String id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public String getJsonCompra (Compra compra) {
        String data = String.format("{\"phoneNumber\":\"%s\", \"countryCode\":\"%s\"," +
                        "\"clientUserId\":\"%s\",\"reference\":\"%s\",\"responseUrl\":\"%s\"," +
                        "\"amount\":\"%s\",\"amountWithTax\":\"%s\",\"amountWithoutTax\":\"%s\"," +
                        "\"tax\":\"%s\",\"clientTransactionId\":\"%s\"}",
                compra.getNumero_telefono(), compra.getCodigo_pais(), compra.getCedula(),
                compra.getDireccion_referencia(), compra.getRespuesta_url(), compra.getMonto_pagar(),
                compra.getImporte_con_impuesto(), compra.getImporte_sin_impuesto(), compra.getImpuesto(),
                compra.getId_transaccion());
        return data;
    }
}
