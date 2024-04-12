package com.talentiva.absensi;

public class Instansi {
    private String id;
    private String namaInstansi;
    private String emailInstansi;
    private String namaKepsek;
    private String nipKepsek;
    private String kontakInstansi;
    private String logoInstansi;
    private String alamatInstansi;

    public Instansi() {
        // Default constructor diperlukan untuk panggilan ke DataSnapshot.getValue(Instansi.class)
    }

    public Instansi(String id, String namaInstansi, String emailInstansi, String namaKepsek, String nipKepsek, String kontakInstansi, String logoInstansi, String alamatInstansi) {
        this.id = id;
        this.namaInstansi = namaInstansi;
        this.emailInstansi = emailInstansi;
        this.namaKepsek = namaKepsek;
        this.nipKepsek = nipKepsek;
        this.kontakInstansi = kontakInstansi;
        this.logoInstansi = logoInstansi;
        this.alamatInstansi = alamatInstansi;
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getNamaInstansi() {
        return namaInstansi;
    }

    public String getEmailInstansi() {
        return emailInstansi;
    }

    public String getNamaKepsek() {
        return namaKepsek;
    }

    public String getNipKepsek() {
        return nipKepsek;
    }

    public String getKontakInstansi() {
        return kontakInstansi;
    }

    public String getLogoInstansi() {
        return logoInstansi;
    }

    public String getAlamatInstansi() {
        return alamatInstansi;
    }

    // Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public void setEmailInstansi(String emailInstansi) {
        this.emailInstansi = emailInstansi;
    }

    public void setNamaKepsek(String namaKepsek) {
        this.namaKepsek = namaKepsek;
    }

    public void setNipKepsek(String nipKepsek) {
        this.nipKepsek = nipKepsek;
    }

    public void setKontakInstansi(String kontakInstansi) {
        this.kontakInstansi = kontakInstansi;
    }

    public void setLogoInstansi(String logoInstansi) {
        this.logoInstansi = logoInstansi;
    }

    public void setAlamatInstansi(String alamatInstansi) {
        this.alamatInstansi = alamatInstansi;
    }
}

