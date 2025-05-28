package model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Polaznik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PolaznikID")
    private long id;
    @Column(name = "Ime")
    private String ime;
    @Column(name = "Prezime")
    private String prezime;

    @ManyToMany
    @JoinTable(name = "Upis",
            joinColumns = @JoinColumn(name = "PolaznikID"),
            inverseJoinColumns = @JoinColumn(name = "ProgramObrazovanjaID"))
    private Set<ProgramObrazovanja> programObrazovanja = new HashSet<>();

    public Polaznik() {
    }

    public Polaznik(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Set<ProgramObrazovanja> getProgramObrazovanja() {
        return programObrazovanja;
    }

    public void setProgramObrazovanja(Set<ProgramObrazovanja> programObrazovanja) {
        this.programObrazovanja = programObrazovanja;
    }
}
