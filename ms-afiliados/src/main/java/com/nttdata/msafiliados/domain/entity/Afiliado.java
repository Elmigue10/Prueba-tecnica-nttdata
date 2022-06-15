package com.nttdata.msafiliados.domain.entity;

import lombok.ToString;
import javax.persistence.*;

@ToString
@Entity
@Table(name = "COB_AFILIADO", schema = "GCCOBRANZAS")
public class Afiliado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AFILIADO_ID", nullable=false)
    private Integer afiliadoId;

    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;

    @Column(name = "NUMERO_IDENTIFICACION")
    private String numeroIdentificacion;

    @Column(name = "PRIMER_NOMBRE")
    private String primerNombre;

    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;

    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;

    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;

    @Column(name = "RIESGO_CAT")
    private String riesgoCat;

    @Column(name = "ACTIVO")
    private char activo;

    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_CREACION")
    private String fechaCreacion;

    @Column(name = "USUARIO_ULTIMA_MODIFICACION")
    private String usuarioUltimaModificacion;

    @Column(name = "FECHA_ULTIMA_MODIFICACION")
    private String fechaUltimaModificacion;

    @Column(name = "NUMERO_CUENTA")
    private String numeroCuenta;

    @Column(name = "ESTADO_CUENTA")
    private String estadoCuenta;

    public Integer getAfiliadoId() {
        return afiliadoId;
    }

    public void setAfiliadoId(Integer afiliadoId) {
        this.afiliadoId = afiliadoId;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getRiesgoCat() {
        return riesgoCat;
    }

    public void setRiesgoCat(String riesgoCat) {
        this.riesgoCat = riesgoCat;
    }

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioUltimaModificacion() {
        return usuarioUltimaModificacion;
    }

    public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
        this.usuarioUltimaModificacion = usuarioUltimaModificacion;
    }

    public String getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }
}