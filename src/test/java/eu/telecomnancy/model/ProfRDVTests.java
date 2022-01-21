package eu.telecomnancy.model;

import eu.telecomnancy.javafx.model.Creneau;
import eu.telecomnancy.javafx.model.Enseignant;
import eu.telecomnancy.javafx.model.Etudiant;
import eu.telecomnancy.javafx.model.GestionnaireDB.*;
import eu.telecomnancy.javafx.model.RDV;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProfRDVTests {

    // @BeforeAll

    @Test
    void addition() {
        assertEquals(2, 2);
    }

    @Test
    void testPickuser() {

        PickUser picker = new PickUser();
        int a = picker.Pick("eleve");
        assertEquals(1, a);
    }

    @Test
    void testGetterRdv_getInfoRdv() {

        GetterRdv getterRdv = new GetterRdv();
        RDV rdv = new RDV();
        rdv = getterRdv.getInfoRdv(10);
        assertEquals("prof", rdv.enseignant.mail);
    }

    @Test
    void testGetterRdv_getAllRdvValide() throws SQLException {

        GetterRdv getterRdv = new GetterRdv();

        RDV rdv = new RDV();
        rdv = getterRdv.getInfoRdv(10);
        ArrayList<RDV> rdvValide = new ArrayList<>();
        rdvValide = getterRdv.getAllRdvValide(rdv.enseignant);
        assertEquals(4, rdvValide.size());
    }
    @Test
    void testGetterRdv_getAllRdvNonValide() throws SQLException {

        GetterRdv getterRdv = new GetterRdv();
        RDV rdv = new RDV();
        rdv = getterRdv.getInfoRdv(7);
        ArrayList<RDV> rdvNonValide = new ArrayList<>();
        rdvNonValide = getterRdv.getAllRdvNonValide(rdv.enseignant);
        assertEquals(1, rdvNonValide.size());
    }
    @Test
    void testGetterRdv_getAllRdv() throws SQLException {

        GetterRdv getterRdv = new GetterRdv();
        RDV rdv = new RDV();
        rdv = getterRdv.getInfoRdv(5);
        ArrayList<RDV> rdvAll = new ArrayList<>();
        rdvAll = getterRdv.getAllRDV(rdv.enseignant);
        assertEquals(8, rdvAll.size());
        assertEquals("fordisme@gmail.com", rdvAll.get(4).etudiant.mail);
    }
    @Test
    void testGetterRdv_getRdvWeek() throws SQLException {

        GetterRdv getterRdv = new GetterRdv();
        RDV rdv = new RDV();
        rdv = getterRdv.getInfoRdv(5);
        ArrayList<RDV> rdvWeek = new ArrayList<>();
        rdvWeek = getterRdv.getRDVWeek(rdv.enseignant, "2022-01-25");
        assertEquals(40, rdvWeek.size());
        assertEquals("Rencontre parent", rdvWeek.get(10).libelle);
    }
    @Test
    void testGetterUser_getInfo() throws SQLException {

        GetterUser getterUser = new GetterUser();

        Enseignant enseignant = new Enseignant();
        Etudiant etudiant = new Etudiant();
        enseignant = getterUser.getInfoProf(4);
        etudiant = getterUser.getInfoEleve(1);

        assertEquals("wayeanthony@gmail.com", enseignant.mail);
        assertEquals("bernadette64200@gmail.com",etudiant.mail);
    }
    @Test
    void testGetterUser_getMdp() throws SQLException {

        GetterUser getterUser = new GetterUser();

        Enseignant enseignant = new Enseignant();
        Etudiant etudiant = new Etudiant();

        enseignant = getterUser.getInfoProf(1);
        etudiant = getterUser.getInfoEleve(1);

        String mdpConnexionEtudiant = getterUser.getMdp(etudiant);
        String mdpConnexionEnseignant = getterUser.getMdp(enseignant);

        assertEquals("eleve", mdpConnexionEtudiant);
        assertEquals("prof",mdpConnexionEnseignant);
    }
    @Test
    void testGetterUser_getThemAll() throws SQLException {

        GetterUser getterUser = new GetterUser();

        ArrayList<Etudiant> listeEtudiant = new ArrayList<>();
        ArrayList<Enseignant> listeEnseignant = new ArrayList<>();

        listeEtudiant = getterUser.getAllEleves();
        listeEnseignant = getterUser.getAllProfs();

        assertEquals(10, getterUser.getAllEleves().size());
        assertEquals(11,getterUser.getAllProfs().size());
    }
    @Test
    void testDisponibilityProf_getCreneau() throws SQLException {

        GestionRDV gestionRDV = new GestionRDV();


        DisponibilityProf disponibilityProf = new DisponibilityProf();

        Creneau creneau = new Creneau();
        creneau = disponibilityProf.getCreneau(86);

        assertEquals(5, creneau.indice);
    }
    @Test
    void testGestionRDV_AddCreneau() throws Exception {

        GetterUser getterUser = new GetterUser();

        ArrayList<Enseignant> listeEnseignant;

        listeEnseignant = getterUser.getAllProfs();


        DisponibilityProf disponibilityProf = new DisponibilityProf();

        ArrayList<Creneau> avantPush;
        ArrayList<Creneau> apresPushpush;

        avantPush = disponibilityProf.getProfCreneau(listeEnseignant.get(0).mail, "2022-02-17");

        assertEquals(0, avantPush.size());

        disponibilityProf.insertCreneauProf(listeEnseignant.get(0),10,11,"2022-02-17");

        apresPushpush = disponibilityProf.getProfCreneau(listeEnseignant.get(0).mail, "2022-02-17");

        assertEquals(1, apresPushpush.size());
    }
    @Test
    void testGestionRDV_AddRdv() throws Exception {

        GestionRDV gestionRDV = new GestionRDV();
        GetterUser getterUser = new GetterUser();

        ArrayList<Enseignant> listeEnseignant;
        ArrayList<Etudiant> listeEtudiant;

        listeEnseignant = getterUser.getAllProfs();
        listeEtudiant = getterUser.getAllEleves();

        DisponibilityProf disponibilityProf = new DisponibilityProf();

        disponibilityProf.insertCreneauProf(listeEnseignant.get(0),10,8,"2022-01-30");

        Creneau creneau = disponibilityProf.getProfCreneau(listeEnseignant.get(0).mail, "2022-01-30").get(0);

        RDV rdv = new RDV(Boolean.TRUE,Boolean.FALSE,"Paris","Oral concours",listeEnseignant.get(0),listeEtudiant.get(2),creneau,"Le rendez-vous de la Coding Week");

        gestionRDV.AddRdv(rdv);

        assertEquals("Le rendez-vous de la Coding Week", rdv.description);
    }




}
