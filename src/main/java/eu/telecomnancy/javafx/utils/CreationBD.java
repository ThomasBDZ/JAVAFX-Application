package eu.telecomnancy.javafx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreationBD {

    public static void main(String[] args)
    {
        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:BDD/TNRDV.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists connection");
            statement.executeUpdate("drop table if exists admin");
            statement.executeUpdate("drop table if exists eleve");
            statement.executeUpdate("drop table if exists prof");
            statement.executeUpdate("drop table if exists rdv");
            statement.executeUpdate("drop table if exists archive");
            statement.executeUpdate("drop table if exists classe");
            statement.executeUpdate("drop table if exists matiere");
            statement.executeUpdate("drop table if exists availableRDV");
            statement.executeUpdate("CREATE TABLE connection(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  mail TEXT NOT NULL,\n" +
                    "  MDP TEXT NOT NULL,\n" +
                    "  typeUser TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE admin(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  nom TEXT NOT NULL,\n" +
                    "  prenom TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE eleve(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  nom TEXT NOT NULL,\n" +
                    "  prenom TEXT NOT NULL,\n" +
                    "  sexe TEXT NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  adresse TEXT NOT NULL,\n" +
                    "  mail TEXT NOT NULL,\n" +
                    "  telephone TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE prof(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  nom TEXT NOT NULL,\n" +
                    "  prenom TEXT NOT NULL,\n" +
                    "  sexe TEXT NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  adresse TEXT NOT NULL,\n" +
                    "  mail TEXT NOT NULL,\n" +
                    "  telephone TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE archive(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  nom TEXT NOT NULL,\n" +
                    "  prenom TEXT NOT NULL,\n" +
                    "  sexe TEXT NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  adresse TEXT NOT NULL,\n" +
                    "  mail TEXT NOT NULL,\n" +
                    "  telephone TEXT NOT NULL,\n" +
                    "  typeUser TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE rdv(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  id_prof INTEGER NOT NULL,\n" +
                    "  id_dispo INTEGER NOT NULL,\n" +
                    "  id_eleve INTEGER NOT NULL,\n" +
                    "  status BOOLEAN NOT NULL,\n" +
                    "  archive BOOLEAN NOT NULL,\n" +
                    "  lieu TEXT NOT NULL,\n" +
                    "  libelle TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id),\n" +
                    "  FOREIGN KEY (id_prof) REFERENCES prof(id),\n" +
                    "  FOREIGN KEY (id_eleve) REFERENCES eleve(id),\n" +
                    "  FOREIGN KEY (id_dispo) REFERENCES availableRDV(id))");
            statement.executeUpdate("CREATE TABLE availableRDV(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  id_prof INTEGER NOT NULL,\n" +
                    "  indice INTEGER NOT NULL,\n" +
                    "  date TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id),\n" +
                    "  FOREIGN KEY (id_prof) REFERENCES prof(id))");
            statement.executeUpdate("CREATE TABLE classe(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  className TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("CREATE TABLE matiere(\n" +
                    "  id INTEGER NOT NULL,\n" +
                    "  disciplineName TEXT NOT NULL,\n" +
                    "  PRIMARY KEY (id))");
            statement.executeUpdate("insert into admin values(1,'admin' ,'admin')");
            statement.executeUpdate("insert into prof values(1, 'Oster', 'Gerald', 'M', '26/04/1980', '23 rue des collines', 'geroster@gmail.com', '0612343338')");
            statement.executeUpdate("insert into prof values(2, 'Dada', 'Seb', 'M', '01/01/1985', '23 rue des montagnes', 'sebdada@gmail.com', '0612345678')");
            statement.executeUpdate("insert into prof values(3, 'Suzi', 'Collin', 'F', '11/12/1975', '23 rue des plaines', 'suzicoco@gmail.com', '0671029384')");
            statement.executeUpdate("insert into prof values(4, 'Waye', 'Anthony', 'M', '12/02/1988', '99 av de la Marne', 'wayeanthony@gmail.com', '0629384710')");
            statement.executeUpdate("insert into prof values(5, 'Splet', 'Alan', 'M', '28/02/1964', '2 avenue Tamame', 'alanlesplet@gmail.com', '0734775320')");
            statement.executeUpdate("insert into prof values(6, 'Lynch', 'David', 'M', '30/08/1957', '9 rue des manèges', 'davidlynch@gmail.com', '0624399871')");
            statement.executeUpdate("insert into prof values(7, 'Cartwright', 'Bob', 'M', '22/09/1998', '12 rue Harcet', 'bobycartwright@gmail.com', '0729944209')");
            statement.executeUpdate("insert into prof values(8, 'Kayé', 'Stephanie', 'F', '26/05/1979', '89 rue Eder', 'stephkaye@gmail.com', '0629643018')");
            statement.executeUpdate("insert into prof values(9, 'Dubois', 'Monique', 'F', '16/11/1959', '20 rue de la bergerie', 'moniqueDubois66@gmail.com', '0690053836')");
            statement.executeUpdate("insert into prof values(10, 'Wells', 'Terry', 'M', '16/07/1973', '14 avenue Carnot', 'terryWells63@gmail.com', '0634288651')");
            statement.executeUpdate("insert into prof values(11, 'Gillespie', 'Paula', 'F', '09/01/1967', '6 carrefour Helianthe', 'paula64gillet@gmail.com', '0726778272')");
            statement.executeUpdate("insert into eleve values(1, 'Nicholls', 'Tiny', 'F', '30/05/1995', '62 avenue de Bayonne', 'tinyNichols@gmail.com', '0664443772 ')");
            statement.executeUpdate("insert into eleve values(2, 'Ford', 'Graham', 'M', '13/06/1979', '58 allée Aguilera', 'fordisme@gmail.com', '0757281975')");
            statement.executeUpdate("insert into eleve values(3, 'Sel', 'Lucie', 'F', '26/04/2000', '23 rue des ecoles', 'luciegoutsel@gmail.com', '0638293339')");
            statement.executeUpdate("insert into eleve values(4, 'Moore', 'Ghislaine', 'F', '05/02/2001', '82 avenue Espagne', 'MOOREGigiD4rk@gmail.com', '0610098701')");
            statement.executeUpdate("insert into eleve values(5, 'Ordever', 'Randy', 'F', '15/09/1999', '27 allée Cout', 'randyyyyyy@gmail.com', '0629900651')");
            statement.executeUpdate("insert into eleve values(6, 'Milnes', 'Bernadette', 'F', '28/03/2001', '60 rue Labouheyre', 'bernadette64200@gmail.com', '0682380772')");
            statement.executeUpdate("insert into eleve values(7, 'Quirke', 'Pauline', 'F', '28/08/2000', '19 rue Lister', 'paulineQuirkee@gmail.com', '0628855220')");
            statement.executeUpdate("insert into eleve values(8, 'Costello', 'Philippe', 'M', '10/03/1995', '15 rue des jardins', 'phiphicostel@gmail.com', '0628877330')");
            statement.executeUpdate("insert into eleve values(9, 'Dwarf', 'Kenny', 'M', '13/12/2000', '19 avenue AlphonseXII', 'kennyyyyhack@gmail.com', '0629095660')");
            statement.executeUpdate("insert into eleve values(10, 'Dwight', 'Office', 'M', '30/09/1999', '15 impasse Heeribi', 'iloveyoudwight@gmail.com', '0612066398')");

            statement.executeUpdate("insert into archive values(1, 'Greener', 'Chris', 'M', '05/02/2002', '15 rue Estagnas', 'chrislegreen@gmail.com', '0628986501', 'prof')");
            statement.executeUpdate("insert into archive values(2, 'Powell', 'Marcus', 'M', '01/09/2000', '01 allée des Champions', 'imawinner@gmail.com', '0632299664','prof')");
            statement.executeUpdate("insert into archive values(3, 'Cohen', 'Gilda', 'F', '15/05/2000', '5 rue Duler', 'GildaCohen@gmail.com', '0680297376','eleve')");
            statement.executeUpdate("insert into archive values(4, 'Cohen', 'Teri', 'M', '15/05/2000', '5 rue Duler', 'TeriCohen@gmail.com', '0622976194','eleve')");
            statement.executeUpdate("insert into archive values(5, 'Day', 'Robert', 'M', '24/10/1950', '41 avenue de la commanderie', 'lebarondu54@gmail.com', '0629904518', 'eleve')");

            statement.executeUpdate("insert into availableRDV values(1, 1, 4, '06/06/2022')");
            statement.executeUpdate("insert into availableRDV values(2, 1, 5, '06/06/2022')");
            statement.executeUpdate("insert into availableRDV values(3, 1, 6, '06/06/2022')");
            statement.executeUpdate("insert into availableRDV values(4, 1, 7, '06/06/2022')");
            statement.executeUpdate("insert into availableRDV values(5, 1, 8, '06/06/2022')");
            statement.executeUpdate("insert into availableRDV values(6, 1, 9, '06/06/2022')");
            statement.executeUpdate("insert into availableRDV values(7, 1, 10, '06/06/2022')");

            statement.executeUpdate("insert into availableRDV values(8, 4, 1, '03/06/2022')");
            statement.executeUpdate("insert into availableRDV values(9, 4, 2, '03/06/2022')");
            statement.executeUpdate("insert into availableRDV values(10, 4, 3, '03/06/2022')");
            statement.executeUpdate("insert into availableRDV values(11, 4, 4, '03/06/2022')");
            statement.executeUpdate("insert into availableRDV values(12, 4, 5, '03/06/2022')");
            statement.executeUpdate("insert into availableRDV values(13, 4, 6, '03/06/2022')");

            statement.executeUpdate("insert into availableRDV values(14, 8, 20, '13/06/2022')");
            statement.executeUpdate("insert into availableRDV values(15, 8, 21, '13/06/2022')");
            statement.executeUpdate("insert into availableRDV values(16, 8, 22, '13/06/2022')");
            statement.executeUpdate("insert into availableRDV values(17, 8, 23, '13/06/2022')");
            statement.executeUpdate("insert into availableRDV values(18, 8, 24, '13/06/2022')");
            statement.executeUpdate("insert into availableRDV values(19, 8, 25, '13/06/2022')");
            statement.executeUpdate("insert into availableRDV values(20, 8, 26, '13/06/2022')");
            statement.executeUpdate("insert into availableRDV values(21, 8, 27, '13/06/2022')");
            statement.executeUpdate("insert into availableRDV values(22, 8, 28, '13/06/2022')");

            statement.executeUpdate("insert into availableRDV values(23, 1, 10, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(24, 1, 11, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(25, 1, 12, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(26, 1, 13, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(27, 1, 14, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(28, 1, 15, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(29, 1, 16, '25/01/2022')");

            statement.executeUpdate("insert into availableRDV values(30, 2, 4, '10/02/2022')");
            statement.executeUpdate("insert into availableRDV values(31, 2, 5, '10/02/2022')");
            statement.executeUpdate("insert into availableRDV values(32, 2, 6, '10/02/2022')");
            statement.executeUpdate("insert into availableRDV values(33, 2, 7, '10/02/2022')");
            statement.executeUpdate("insert into availableRDV values(34, 2, 8, '10/02/2022')");
            statement.executeUpdate("insert into availableRDV values(35, 2, 9, '10/02/2022')");
            statement.executeUpdate("insert into availableRDV values(36, 2, 10, '10/02/2022')");

            statement.executeUpdate("insert into availableRDV values(37, 5, 15, '11/06/2022')");
            statement.executeUpdate("insert into availableRDV values(38, 5, 16, '11/06/2022')");
            statement.executeUpdate("insert into availableRDV values(39, 5, 17, '11/06/2022')");
            statement.executeUpdate("insert into availableRDV values(40, 5, 18, '11/06/2022')");
            statement.executeUpdate("insert into availableRDV values(41, 5, 19, '11/06/2022')");
            statement.executeUpdate("insert into availableRDV values(42, 5, 20, '11/06/2022')");
            statement.executeUpdate("insert into availableRDV values(43, 5, 21, '11/06/2022')");
            statement.executeUpdate("insert into availableRDV values(44, 5, 22, '11/06/2022')");
            statement.executeUpdate("insert into availableRDV values(45, 5, 23, '11/06/2022')");
            statement.executeUpdate("insert into availableRDV values(46, 5, 24, '11/06/2022')");
            statement.executeUpdate("insert into availableRDV values(47, 5, 25, '11/06/2022')");

            statement.executeUpdate("insert into availableRDV values(48, 10, 26, '15/06/2022')");
            statement.executeUpdate("insert into availableRDV values(49, 10, 27, '15/06/2022')");
            statement.executeUpdate("insert into availableRDV values(50, 10, 28, '15/06/2022')");
            statement.executeUpdate("insert into availableRDV values(51, 10, 29, '15/06/2022')");
            statement.executeUpdate("insert into availableRDV values(52, 10, 30, '15/06/2022')");
            statement.executeUpdate("insert into availableRDV values(53, 10, 31, '15/06/2022')");

            statement.executeUpdate("insert into availableRDV values(54, 9, 1, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(55, 9, 2, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(56, 9, 3, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(57, 9, 4, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(58, 9, 5, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(59, 9, 6, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(60, 9, 7, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(61, 9, 8, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(62, 9, 9, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(63, 9, 10, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(64, 9, 11, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(65, 9, 12, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(66, 9, 16, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(67, 9, 17, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(68, 9, 18, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(69, 9, 19, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(70, 9, 20, '20/04/2022')");
            statement.executeUpdate("insert into availableRDV values(71, 9, 21, '20/04/2022')");


            statement.executeUpdate("insert into availableRDV values(72, 7, 16, '28/03/2022')");
            statement.executeUpdate("insert into availableRDV values(73, 7, 17, '28/03/2022')");
            statement.executeUpdate("insert into availableRDV values(74, 7, 18, '28/03/2022')");
            statement.executeUpdate("insert into availableRDV values(75, 7, 19, '28/03/2022')");
            statement.executeUpdate("insert into availableRDV values(76, 7, 20, '28/03/2022')");
            statement.executeUpdate("insert into availableRDV values(77, 7, 21, '28/03/2022')");
            statement.executeUpdate("insert into availableRDV values(78, 7, 22, '28/03/2022')");

            statement.executeUpdate("insert into availableRDV values(79, 8, 6, '07/02/2022')");
            statement.executeUpdate("insert into availableRDV values(80, 8, 7, '07/02/2022')");
            statement.executeUpdate("insert into availableRDV values(81, 8, 8, '07/02/2022')");
            statement.executeUpdate("insert into availableRDV values(82, 8, 9, '07/02/2022')");
            statement.executeUpdate("insert into availableRDV values(83, 8, 10, '07/02/2022')");
            statement.executeUpdate("insert into availableRDV values(84, 11, 4, '25/01/2022')");

            statement.executeUpdate("insert into availableRDV values(85, 11, 4, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(86, 11, 5, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(87, 11, 6, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(88, 11, 7, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(89, 11, 8, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(90, 11, 9, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(91, 11, 10, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(92, 11, 11, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(93, 11, 12, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(94, 11, 13, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(95, 11, 14, '25/01/2022')");

            statement.executeUpdate("insert into availableRDV values(96, 1, 14, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(97, 2 ,14, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(98, 3, 14, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(99, 4, 14, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(100, 5, 14, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(101, 6, 14, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(102, 7, 14, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(103, 8, 14, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(104, 9, 14, '25/01/2022')");
            statement.executeUpdate("insert into availableRDV values(105, 10, 14, '25/01/2022')");



            statement.executeUpdate("insert into rdv values(1, 1, 1, 1, 1, 0, 'Le lieu est Nancy', 'Rdv reorientation')");
            statement.executeUpdate("insert into rdv values(2, 1, 2, 2, 1, 0, 'Le lieu est Nancy', 'Projet')");
            statement.executeUpdate("insert into rdv values(3, 2, 5, 5, 1, 1, 'Le lieu est Nancy', 'Consolidation')");
            statement.executeUpdate("insert into rdv values(4, 2, 10, 7, 1, 0, 'Le lieu est Nancy', 'Rdv pédagogique')");
            statement.executeUpdate("insert into rdv values(5, 3, 15, 1, 1, 0, 'Le lieu est Nancy', 'Conseil des élèves')");
            statement.executeUpdate("insert into rdv values(6, 4, 23, 1, 1, 0, 'Le lieu est Nancy', 'Rencontre parent')");
            statement.executeUpdate("insert into rdv values(7, 1, 37, 2, 1, 0, 'Le lieu est Nancy', 'Journée du Numérique')");
            statement.executeUpdate("insert into rdv values(8, 1, 45, 10, 1, 0, 'Le lieu est Nancy', 'Rdv business')");
            statement.executeUpdate("insert into rdv values(9, 1, 50, 9, 1, 0, 'Le lieu est Nancy', 'Avant projet')");
            statement.executeUpdate("insert into rdv values(10, 1, 58, 8, 1, 0, 'Le lieu est Nancy', 'Préparation exam')");
            statement.executeUpdate("insert into rdv values(11, 1, 69, 1, 1, 0, 'Le lieu est Nancy', 'Examen')");
            statement.executeUpdate("insert into rdv values(12, 1, 70, 3, 1, 0, 'Le lieu est Nancy', 'Réunion pédagogique')");
            statement.executeUpdate("insert into rdv values(13, 1, 75, 4, 1, 0, 'Le lieu est Nancy', 'RDV mobilité académique')");
            statement.executeUpdate("insert into rdv values(14, 1, 3, 5, 1, 0, 'Le lieu est Nancy', 'BBQ entre prof chez Suzanne')");

            statement.executeUpdate("insert into rdv values(15, 1, 96, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(17, 2, 97, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(18, 3, 98, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(19, 4, 99, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(20, 5, 100, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(21, 6, 101, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(22, 7, 102, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(23, 8, 103, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(24, 9, 104, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(25, 10, 105, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");


            statement.executeUpdate("insert into rdv values(27, 11, 85, 1, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(28, 11, 86, 2, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(29, 11, 87, 3, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(30, 11, 88, 4, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(31, 11, 89, 5, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(32, 11, 90, 6, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(33, 11, 91, 7, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(34, 11, 92, 8, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(35, 11, 93, 9, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(36, 11, 94, 10, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");
            statement.executeUpdate("insert into rdv values(37, 11, 95, 11, 1, 0, 'Hotel de ville - Nancy', 'Cérémonie des diplômes')");


            ResultSet rs = statement.executeQuery("select * from admin");
            while(rs.next())
            {
                // read the result set
                System.out.println("name = " + rs.getString("nom"));
                System.out.println("id = " + rs.getInt("prenom"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
