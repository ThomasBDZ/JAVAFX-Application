CREATE TABLE admin(
  id INTEGER NOT NULL,
  nom TEXT NOT NULL,
  prenom TEXT NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE eleve(
  id INTEGER NOT NULL,
  nom TEXT NOT NULL,
  prenom TEXT NOT NULL,
  sexe INTEGER NOT NULL,
  date TEXT NOT NULL,
  adresse TEXT NOT NULL,
  mail TEXT NOT NULL,
  telephone TEXT NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE prof(
  id INTEGER NOT NULL,
  nom TEXT NOT NULL,
  prenom TEXT NOT NULL,
  sexe INTEGER NOT NULL,
  date TEXT NOT NULL,
  adresse TEXT NOT NULL,
  telephone TEXT NOT NULL,
  mail TEXT NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE rdv(
  id INTEGER NOT NULL,
  id_prof INTEGER NOT NULL,
  id_eleve INTEGER NOT NULL,
  salle INTEGER NOT NULL,
  date TEXT NOT NULL,
  heure TEXT NOT NULL,
  statut INTEGER NOT NULL,
  PRIMARY KEY (id_prof, id_eleve, date, heure)
);
