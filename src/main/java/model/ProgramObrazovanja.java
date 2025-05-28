package model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class ProgramObrazovanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgramObrazovanjaID")
    private long id;

    @Column(name = "Naziv")
    private String naziv;

    @Column(name = "CSVET")
    private int csvet;
    @ManyToMany(mappedBy = "programObrazovanja")
    private Set<Polaznik> polaznici;
    public ProgramObrazovanja() {
    }
    public ProgramObrazovanja(String naziv, int csvet) {
        this.naziv = naziv;
        this.csvet = csvet;
    }

    public int getCsvet() {
        return csvet;
    }

    public void setCsvet(int csvet) {
        this.csvet = csvet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<Polaznik> getPolaznici() {
        return polaznici;
    }

    public void setPolaznici(Set<Polaznik> polaznici) {
        this.polaznici = polaznici;
    }
}
