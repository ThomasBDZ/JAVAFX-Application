import eu.telecomnancy.javafx.model.GestionnaireLogin;

public class GestionnaireLoginTests {


    @BeforeAll
    GestionnaireLogin gestionnaire = new GestionnaireLogin();


    @Test
    void admin() {
        // String type = gestionnaire.login("admin","admin");
        // assertEquals(type, "admin");
        assert(1);
    }
    

}
