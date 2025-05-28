import jakarta.persistence.Query;
import model.Polaznik;
import model.ProgramObrazovanja;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
//unosPolaznika(sc);
//unosPO(sc);
//upisPolaznikaNaPO(sc);
//izmjenaPO(sc);

        ispisPolaznikaIPO(sc);
    }

    private static void unosPolaznika(Scanner sc){
        System.out.print("Unesite ime polaznika: ");
        String ime = sc.nextLine();
        System.out.print("Unesite prezime polaznika: ");
        String prezime = sc.nextLine();

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Polaznik polaznik = new Polaznik(ime, prezime);
            session.save(polaznik);
            transaction.commit();

        }

    }
    private static void ispisPolaznikaIPO(Scanner sc){

        String hql = "SELECT p.ime, p.prezime, po.naziv, po.csvet " +
                "FROM Polaznik p " +
                "JOIN p.programObrazovanja po " +
                "WHERE p.id = :polaznikId";


        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List<Polaznik> polaznici = session.createQuery("from Polaznik").list();
            for (Polaznik p : polaznici) {
                System.out.println(p.getId()+" "+ p.getIme() + " " + p.getPrezime());
            }
            System.out.print("Izaberite polaznika po ID-u:");
            int id = Integer.parseInt(sc.nextLine());
            List<Object[]> results = session.createQuery(hql, Object[].class)
                    .setParameter("polaznikId", id)
                    .list();

            for (Object[] result : results) {
                System.out.printf("Ime: %s, Prezime: %s, Program: %s, CSVET: %s%n",
                        result[0], result[1], result[2], result[3]);
            }



            transaction.commit();
        }
    }
    private static void unosPO(Scanner sc){
        System.out.print("Unesite naziv Programa Obrazovanja: ");
        String naziv = sc.nextLine();
        System.out.print("Unesite CSVET: ");
        int csvt =Integer.parseInt(sc.nextLine());

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            ProgramObrazovanja po = new ProgramObrazovanja(naziv, csvt);
            session.save(po);
            transaction.commit();

        }

    }
    private static void upisPolaznikaNaPO(Scanner sc){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List<Polaznik> polaznici = session.createQuery("from Polaznik").list();
            for (Polaznik p : polaznici) {
                System.out.println(p.getId()+" "+ p.getIme() + " " + p.getPrezime());
            }
            System.out.print("Izaberite polaznika po ID-u:");
            int id = Integer.parseInt(sc.nextLine());

            List<ProgramObrazovanja> programObrazovanja = session.createQuery("from ProgramObrazovanja").list();
            for(ProgramObrazovanja po : programObrazovanja){
                System.out.println(po.getId()+" "+ po.getNaziv() + " " + po.getCsvet());
            }
            System.out.print("Izaberite Program Obrazovanja po ID-u:");
            int idPO = Integer.parseInt(sc.nextLine());

            Polaznik polaznik = session.get(Polaznik.class, id);
            ProgramObrazovanja programObrazovanja1 = session.get(ProgramObrazovanja.class, idPO);

          polaznik.getProgramObrazovanja().add(programObrazovanja1);
          session.save(polaznik);
            transaction.commit();

        }
    }
    private static void izmjenaPO(Scanner sc){
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List<Polaznik> polaznici = session.createQuery("from Polaznik").list();
            for (Polaznik p : polaznici) {
                System.out.println(p.getId()+" "+ p.getIme() + " " + p.getPrezime());
            }
            System.out.print("Izaberite polaznika po ID-u:");
            int id = Integer.parseInt(sc.nextLine());

            List<ProgramObrazovanja> programObrazovanja = session.createQuery("from ProgramObrazovanja").list();
            for(ProgramObrazovanja po : programObrazovanja){
                System.out.println(po.getId()+" "+ po.getNaziv() + " " + po.getCsvet());
            }
            System.out.print("Izaberite Program Obrazovanja po ID-u:");
            int idPO = Integer.parseInt(sc.nextLine());

            Polaznik polaznik = session.get(Polaznik.class, id);
            ProgramObrazovanja programObrazovanja1 = session.get(ProgramObrazovanja.class, idPO);

            polaznik.getProgramObrazovanja().clear();
            polaznik.getProgramObrazovanja().add(programObrazovanja1);

            transaction.commit();

        }
    }
}
