DROP TABLE ASSOC_Contenu_Format;
DROP TABLE Evaluation;
DROP TABLE Interprete;
DROP TABLE MetteurEnScene;
DROP TABLE Compositeur;
DROP TABLE Scenariste;
DROP TABLE Realisateur;
DROP TABLE Acteur;
DROP TABLE ASSOC_Contenu_MotCle;
DROP TABLE Location;
DROP TABLE Achat;
DROP TABLE Version;
DROP TABLE Saison;
DROP TABLE Serie;
DROP TABLE Cinema;
DROP TABLE Jeunesse;
DROP TABLE Divertissement;
DROP TABLE Contenu;
DROP TABLE Abonnement;
DROP TABLE TypeAbonnement;
DROP TABLE Logiciels;
DROP TABLE TypeMIME;
DROP TABLE Extensions;
DROP TABLE Format;
DROP TABLE Client;
DROP TABLE Personnes;
DROP TABLE Theme;
DROP TABLE Motcle;
DROP TABLE Classification;
DROP TABLE Pays;
DROP TABLE TypeContenu;


CREATE TABLE Personnes (
idPersonne INTEGER CONSTRAINT PK_Personnes PRIMARY KEY,
nom VARCHAR(100),
prenom VARCHAR(100) DEFAULT NULL
);

CREATE TABLE Theme (
idTheme INTEGER CONSTRAINT PK_Theme PRIMARY KEY,
nomTheme VARCHAR(50) UNIQUE
);

CREATE TABLE Motcle(
idMotcle INTEGER CONSTRAINT PK_Motcle PRIMARY KEY,
mot VARCHAR(50) UNIQUE
);

CREATE TABLE Classification(
idClassification INTEGER CONSTRAINT PK_Classification PRIMARY KEY,
niveau VARCHAR(26)
);

CREATE TABLE Pays (
idPays INTEGER CONSTRAINT PK_Pays PRIMARY KEY,
nomPays VARCHAR(50)
);


CREATE TABLE TypeContenu (
idTypeContenu INTEGER CONSTRAINT PK_TypeContenu PRIMARY KEY,
nomTypeContenu VARCHAR (50)
);


CREATE TABLE Client (
idClient INTEGER CONSTRAINT PK_Client PRIMARY KEY,
idPersonne INTEGER UNIQUE,
adresseClient VARCHAR (255),
CodePostal NUMBER(5,0),
Ville VARCHAR (100),
mail VARCHAR(255),
NumeroTelephone NUMBER (10,0),
MotdePasse VARCHAR (255), 
CONSTRAINT FK_Client_Personnes
FOREIGN KEY (idPersonne) REFERENCES Personnes(idPersonne)
);

CREATE TABLE TypeAbonnement(
idTypeAbonnement INTEGER CONSTRAINT PK_TypeAbonnement PRIMARY KEY,
nomAbonnement VARCHAR(15) CONSTRAINT CU_TypeAbonnement_nom UNIQUE,
prixAbonnement NUMBER(4,2),
nbmaxcontenusloues INTEGER,
nbCinema NUMBER(2,0),
nbSeries NUMBER(2,0),
nbJeunesse NUMBER(2,0),
nbDivertissement NUMBER(2,0)
);

CREATE TABLE Abonnement(
idAbonnement INTEGER CONSTRAINT PK_Abonnement PRIMARY KEY,
idClient INTEGER,
idTypeAbonnement INTEGER,
dateDebut DATE,
dateFin DATE DEFAULT NULL,
nblocencours INTEGER DEFAULT 0,
CONSTRAINT FK_Abonnement_TypeAbonnement FOREIGN KEY (idTypeAbonnement) REFERENCES TypeAbonnement(idTypeAbonnement),
CONSTRAINT FK_Abonnement_Client FOREIGN KEY (idClient) REFERENCES Client (idClient)
);

CREATE TABLE Format(
idFormat INTEGER CONSTRAINT PK_Format PRIMARY KEY,
nom VARCHAR(10) CONSTRAINT CU_Format_nom UNIQUE
);

CREATE TABLE Extensions(
idExtension INTEGER CONSTRAINT PK_Extensions PRIMARY KEY,
ext VARCHAR(10) ,
idFormat INTEGER,
CONSTRAINT FK_Extensions_Format FOREIGN KEY (idFormat) REFERENCES Format(idFormat) 
);


CREATE TABLE TypeMIME(
idTypeMIME INTEGER CONSTRAINT PK_TypeMIME PRIMARY KEY,
type VARCHAR(30) CONSTRAINT CU_TypeMIME_type UNIQUE,
idFormat INTEGER,
CONSTRAINT FK_TypeMIME_Format FOREIGN KEY (idFormat) REFERENCES Format(idFormat) 
);

CREATE TABLE Logiciels(
idLogiciel INTEGER CONSTRAINT PK_Logiciels PRIMARY KEY,
nomLogiciel VARCHAR(30) CONSTRAINT CU_Logiciels_nomLogiciel UNIQUE,
idFormat INTEGER,
CONSTRAINT FK_Logiciel_Format FOREIGN KEY (idFormat) REFERENCES Format(idFormat) 
);



CREATE TABLE Contenu (
idContenu INTEGER CONSTRAINT PK_Contenu PRIMARY KEY,
idTypeContenu INTEGER,
titre VARCHAR (208), --c'est le titre le plus long
description VARCHAR2(256),
idTheme INTEGER,
evaluation NUMBER(3,2) DEFAULT NULL,
idClassification INTEGER,
bandeAnnonce VARCHAR (500) DEFAULT NULL, --au cas où l'url est long
idPays INTEGER,
annee NUMBER (4,0),
CONSTRAINT FK_Contenu_Pays
FOREIGN KEY (idPays) REFERENCES Pays (idPays),
CONSTRAINT FK_Contenu_Classification
FOREIGN KEY (idClassification) REFERENCES Classification (idClassification),
CONSTRAINT FK_Contenu_TypeContenu
FOREIGN KEY (idTypeContenu) REFERENCES TypeContenu (idTypeContenu),
CONSTRAINT FK_Contenu_Theme
FOREIGN KEY (idTheme) REFERENCES Theme (idTheme)
);


CREATE TABLE Cinema (
--idCinema INTEGER CONSTRAINT PK_Cinema PRIMARY KEY,
idContenu INTEGER CONSTRAINT PK_Cinema PRIMARY KEY,
duree INTERVAL DAY TO SECOND,
CONSTRAINT FK_Cinema_Contenu
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu)
);



CREATE TABLE Jeunesse (
--idJeunesse INTEGER CONSTRAINT PK_Jeunesse PRIMARY KEY,
idContenu INTEGER CONSTRAINT PK_Jeunesse PRIMARY KEY,
duree INTERVAL DAY TO SECOND,
CONSTRAINT FK_Jeunesse_Contenu
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu)
);

CREATE TABLE Divertissement (
--idDivertissement INTEGER CONSTRAINT PK_Divertissement PRIMARY KEY,
idContenu INTEGER CONSTRAINT PK_Divertissement PRIMARY KEY,
duree INTERVAL DAY TO SECOND,
CONSTRAINT FK_Divertissement_Contenu
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu)
);

CREATE TABLE Serie (
--idSerie INTEGER CONSTRAINT PK_Serie PRIMARY KEY,
idContenu INTEGER CONSTRAINT PK_Serie PRIMARY KEY,
nbrSaisons INTEGER DEFAULT 0,
CONSTRAINT FK_Serie_Contenu
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu)
);

CREATE TABLE Saison (
--idSaison INTEGER CONSTRAINT PK_Saison PRIMARY KEY,
idContenu INTEGER CONSTRAINT PK_Saison PRIMARY KEY,
idSerie INTEGER,
NumSaison INTEGER,
nbrEpisodes INTEGER DEFAULT NULL,
CONSTRAINT FK_Saison_Contenu
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu),
CONSTRAINT FK_Saison_Serie
FOREIGN KEY (idSerie) REFERENCES Serie (idContenu)
);



CREATE TABLE Version(
idVersion INTEGER CONSTRAINT PK_Version PRIMARY KEY,
idTypeContenu INTEGER,
qualite VARCHAR(3),
prixLocation NUMBER(4,2),
prixAchat NUMBER(4,2),
CONSTRAINT FK_Version_TypeContenu FOREIGN KEY (idTypeContenu) REFERENCES TypeContenu(idTypeContenu)
);


CREATE TABLE Achat(
idAchat INTEGER CONSTRAINT PK_Achat PRIMARY KEY,
idClient INTEGER,
idContenu INTEGER,
dateAchat DATE,
idVersion INTEGER DEFAULT NULL,
prix NUMBER(4,2),
CONSTRAINT FK_Achat_Client FOREIGN KEY (idClient) REFERENCES Client(idClient),
CONSTRAINT FK_Achat_Contenu FOREIGN KEY (idContenu) REFERENCES Contenu(idContenu),
CONSTRAINT FK_Achat_Version FOREIGN KEY (idVersion) REFERENCES Version(idVersion)
--CONSTRAINT FK_Achat_Version_prix FOREIGN KEY (prix) REFERENCES Version(prixAchat)
);




CREATE TABLE Location(
idLocation INTEGER CONSTRAINT PK_Location PRIMARY KEY,
idClient INTEGER,
idContenu INTEGER,
dateDebut DATE,
dateFin DATE,
idVersion INTEGER,
prix NUMBER(4,2),
CONSTRAINT FK_Location_Client FOREIGN KEY (idClient) REFERENCES Client(idClient),
CONSTRAINT FK_Location_Contenu FOREIGN KEY (idContenu) REFERENCES Contenu(idContenu),
CONSTRAINT FK_Location_Version FOREIGN KEY (idVersion) REFERENCES Version(idVersion)
--CONSTRAINT FK_Location_Version_prix FOREIGN KEY (prix) REFERENCES Version(prixLocation)
);

CREATE TABLE Evaluation(
idEvaluation INTEGER CONSTRAINT PK_Evaluation PRIMARY KEY,
idClient INTEGER,
idContenu INTEGER,
note NUMBER(1,0),
CONSTRAINT FK_Evaluation_Client FOREIGN KEY (idClient) REFERENCES Client(idClient),
CONSTRAINT FK_Evaluation_Contenu FOREIGN KEY (idContenu) REFERENCES Contenu(idContenu)
);

CREATE TABLE ASSOC_Contenu_MotCle (
idContenu INTEGER,
idMotCle INTEGER,
CONSTRAINT PK_ASSOC_Contenu_MotCle
PRIMARY KEY (idContenu, idMotCle),
CONSTRAINT FK_ASSOC_Contenu_MotCle_Contenu
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu),
CONSTRAINT FK_ASSOC_Contenu_MotCle_MotCle
FOREIGN KEY (idMotCle) REFERENCES MotCle (idMotCle)
);

CREATE TABLE Acteur (
idPersonne INTEGER,
idContenu INTEGER,
CONSTRAINT PK_Acteur PRIMARY KEY (idPersonne, idContenu),
CONSTRAINT FK_Acteur_Personne 
FOREIGN KEY (idPersonne) REFERENCES Personnes (idPersonne),
CONSTRAINT FK_Acteur_Contenu 
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu)
);

CREATE TABLE Realisateur (
idPersonne INTEGER,
idContenu INTEGER,
CONSTRAINT PK_realisateur PRIMARY KEY (idPersonne, idContenu),
CONSTRAINT FK_Realisateur_Personne 
FOREIGN KEY (idPersonne) REFERENCES Personnes (idPersonne),
CONSTRAINT FK_Realisateur_Contenu 
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu)
);

CREATE TABLE Scenariste (
idPersonne INTEGER,
idContenu INTEGER,
CONSTRAINT PK_Scenariste PRIMARY KEY (idPersonne, idContenu),
CONSTRAINT FK_Scenariste_Personne 
FOREIGN KEY (idPersonne) REFERENCES Personnes (idPersonne),
CONSTRAINT FK_Scenariste_Contenu 
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu)
);


CREATE TABLE Compositeur (
idPersonne INTEGER,
idContenu INTEGER,
CONSTRAINT PK_Compositeur PRIMARY KEY (idPersonne, idContenu),
CONSTRAINT FK_Compositeur_Personne 
FOREIGN KEY (idPersonne) REFERENCES Personnes (idPersonne),
CONSTRAINT FK_Compositeur_Contenu 
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu)
);


CREATE TABLE MetteurEnScene (
idPersonne INTEGER,
idContenu INTEGER,
CONSTRAINT PK_MetteurEnScene PRIMARY KEY (idPersonne, idContenu),
CONSTRAINT FK_MetteurEnScene_Personne 
FOREIGN KEY (idPersonne) REFERENCES Personnes (idPersonne),
CONSTRAINT FK_MetteurEnScene_Contenu 
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu)
);



CREATE TABLE Interprete (
idPersonne INTEGER,
idContenu INTEGER,
CONSTRAINT PK_Interprete PRIMARY KEY (idPersonne, idContenu),
CONSTRAINT FK_Interprete_Personne 
FOREIGN KEY (idPersonne) REFERENCES Personnes (idPersonne),
CONSTRAINT FK_Interprete_Contenu 
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu)
);


CREATE TABLE ASSOC_Contenu_Format (
idContenu INTEGER,
idFormat INTEGER,
CONSTRAINT PK_ASSOC_Contenu_Format
PRIMARY KEY (idContenu, idFormat),
CONSTRAINT FK_ASSOC_Contenu_Format_Contenu
FOREIGN KEY (idContenu) REFERENCES Contenu (idContenu),
CONSTRAINT FK_ASSOC_Contenu_Format
FOREIGN KEY (idFormat) REFERENCES Format(idFormat)
);


--Création des séquences et triggers pour une Auto-incrémentation des Identifiants de certaines tables 
DROP SEQUENCE seq_Personnes;
CREATE SEQUENCE seq_Personnes
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Personnes 
BEFORE INSERT ON Personnes
FOR EACH ROW
BEGIN
    SELECT seq_Personnes.NEXTVAL INTO :NEW.idPersonne FROM dual;
END;
/


DROP SEQUENCE seq_Theme;
CREATE SEQUENCE seq_Theme
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Theme
BEFORE INSERT ON Theme
FOR EACH ROW
BEGIN
    SELECT seq_Theme.NEXTVAL INTO :NEW.idTheme FROM dual;
END;
/

DROP SEQUENCE seq_MotCle;
CREATE SEQUENCE seq_MotCle
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_MotCle
BEFORE INSERT ON MotCle
FOR EACH ROW
BEGIN
    SELECT seq_MotCle.NEXTVAL INTO :NEW.idMotCle FROM dual;
END;
/

DROP SEQUENCE seq_Classification;
CREATE SEQUENCE seq_Classification
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Classification
BEFORE INSERT ON Classification
FOR EACH ROW
BEGIN
    SELECT seq_Classification.NEXTVAL INTO :NEW.idClassification FROM dual;
END;
/


DROP SEQUENCE seq_Pays;
CREATE SEQUENCE seq_Pays
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Pays
BEFORE INSERT ON Pays
FOR EACH ROW
BEGIN
    SELECT seq_Pays.NEXTVAL INTO :NEW.idPays FROM dual;
END;
/

DROP SEQUENCE seq_TypeContenu;
CREATE SEQUENCE seq_TypeContenu
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_TypeContenu
BEFORE INSERT ON TypeContenu
FOR EACH ROW
BEGIN
    SELECT seq_TypeContenu.NEXTVAL INTO :NEW.idTypeContenu FROM dual;
END;
/


DROP SEQUENCE seq_Client;
CREATE SEQUENCE seq_Client
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Client
BEFORE INSERT ON Client
FOR EACH ROW
BEGIN
    SELECT seq_Client.NEXTVAL INTO :NEW.idClient FROM dual;
END;
/

DROP SEQUENCE seq_Contenu;
CREATE SEQUENCE seq_Contenu
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Contenu
BEFORE INSERT ON Contenu
FOR EACH ROW
BEGIN
    SELECT seq_Contenu.NEXTVAL INTO :NEW.idContenu FROM dual;
END;
/


DROP SEQUENCE seq_Abonnement;
CREATE SEQUENCE seq_Abonnement
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Abonnement
BEFORE INSERT ON Abonnement
FOR EACH ROW
BEGIN
    SELECT seq_Abonnement.NEXTVAL INTO :NEW.idAbonnement FROM dual;
END;
/


DROP SEQUENCE seq_Achat;
CREATE SEQUENCE seq_Achat
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Achat
BEFORE INSERT ON Achat
FOR EACH ROW
BEGIN
    SELECT seq_Achat.NEXTVAL INTO :NEW.idAchat FROM dual;
END;
/

DROP SEQUENCE seq_Location;
CREATE SEQUENCE seq_Location
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Location
BEFORE INSERT ON Location
FOR EACH ROW
BEGIN
    SELECT seq_Location.NEXTVAL INTO :NEW.idLocation FROM dual;
END;
/


DROP SEQUENCE seq_Evaluation;
CREATE SEQUENCE seq_Evaluation
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Evaluation
BEFORE INSERT ON Evaluation
FOR EACH ROW
BEGIN
    SELECT seq_Evaluation.NEXTVAL INTO :NEW.idEvaluation FROM dual;
END;
/

DROP SEQUENCE seq_Version;
CREATE SEQUENCE seq_Version
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE OR REPLACE TRIGGER trg_Version2
BEFORE INSERT ON Version
FOR EACH ROW
BEGIN
    SELECT seq_Version.NEXTVAL INTO :NEW.idVersion FROM dual;
END;
/




--Quelques déclencheurs logiques
--Déclencheur a l'insertion d'une location pour mettre à jour la date de fin en fonction de l'abonnement ou non 
--et donc mise a jour du nombre de contenus loues actuellement par un abonné
CREATE OR REPLACE TRIGGER trg_Location_modifdatefin
BEFORE INSERT ON Location
FOR EACH ROW
DECLARE
    v_idClient INTEGER;
    v_idabonnement INTEGER;
    v_idcontenu INTEGER;
    v_nomtypecontenu VARCHAR(100);
    v_nbcinema INTEGER;
    v_nbseries INTEGER;
    v_nbdivertissement INTEGER;
    v_nbjeunesse INTEGER;
    v_datedebut DATE;
    v_nblocationsencours INTEGER;
BEGIN
    v_idclient := :NEW.idclient;
    v_datedebut := :NEW.datedebut;
    v_idcontenu := :NEW.idcontenu;
    BEGIN
        select a.idabonnement,ta.nbcinema, ta.nbseries, ta.nbjeunesse, ta.nbdivertissement  
        into v_idabonnement, v_nbcinema, v_nbseries, v_nbjeunesse, v_nbdivertissement 
        from abonnement a join typeabonnement ta on a.idtypeabonnement = ta.idtypeabonnement 
        where idclient = v_idclient
        AND   (a.datedebut <= v_datedebut AND (a.datefin IS NULL OR a.datefin >= v_datedebut));
    
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                -- Aucun abonnement trouvé pour le client
                v_idabonnement := NULL;
                v_nbcinema := NULL;
                v_nbseries := NULL;
                v_nbdivertissement := NULL;
                v_nbjeunesse := NULL;
    END;
    
    BEGIN
        select tc.nomtypecontenu 
        into v_nomtypecontenu 
        from contenu c join typecontenu tc on c.idtypecontenu = tc.idtypecontenu 
        where idcontenu = v_idcontenu;
    
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                -- Aucun abonnement trouvé pour le client
                v_nomtypecontenu := NULL;
    END;
    
    BEGIN
        select count(*) into v_nblocationsencours
        from location 
        where idclient = v_idclient and (datefin >= SYSDATE);
    
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                -- Aucun abonnement trouvé pour le client
                v_nblocationsencours := 0;
    END;
    
    IF (
        v_idabonnement IS NULL
        )
    THEN
        :NEW.datefin := :NEW.datedebut + 7;
    ELSE 
        IF (v_nomtypecontenu = 'Cinema')
        THEN :NEW.datefin := :NEW.datedebut + v_nbcinema;
        END IF;
        IF (v_nomtypecontenu = 'Serie' OR v_nomtypecontenu = 'Saison')
        THEN :NEW.datefin := :NEW.datedebut + v_nbseries;
        END IF;
        IF (v_nomtypecontenu = 'Jeunesse')
        THEN :NEW.datefin := :NEW.datedebut + v_nbjeunesse;
        END IF;
        IF (v_nomtypecontenu = 'Divertissement')
        THEN :NEW.datefin := :NEW.datedebut + v_nbdivertissement;
        END IF;
        
        IF (:NEW.datefin >= SYSDATE)
        THEN
            UPDATE abonnement SET nblocencours = (v_nblocationsencours + 1) WHERE idabonnement = v_idabonnement;
        ELSE  UPDATE abonnement SET nblocencours = (v_nblocationsencours) WHERE idabonnement = v_idabonnement;
        END IF;
    END IF;
    
END;
/



--Déclencheur vérifiant qu'une evaluation est faite par un client ayant déjà loué ou acheté le contenu évalué
CREATE OR REPLACE TRIGGER trg_check_evaluation
BEFORE INSERT ON Evaluation
FOR EACH ROW
DECLARE
    v_achat INTEGER;
    v_loc INTEGER;
BEGIN
    -- Vérifier si le client a effectué une location ou un achat du contenu
    BEGIN
    
        SELECT idAchat INTO v_achat
        FROM Achat a, Client cl, Contenu c, Location l
        WHERE cl.idClient = :NEW.idClient
        AND c.idContenu = :NEW.idContenu
        AND a.idContenu = c.idContenu
        AND a.idClient = cl.idClient;
    
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                -- Aucune location ou achat trouvé pour le client
                v_achat := NULL;
    END;
    
    BEGIN
    
        SELECT idLocation INTO v_loc
        FROM Client cl, Contenu c, Location l
        WHERE cl.idClient = :NEW.idClient
        AND c.idContenu = :NEW.idContenu
        AND l.idContenu = c.idContenu
        AND l.idClient = cl.idClient;
    
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                -- Aucune location ou achat trouvé pour le client
                v_achat := NULL;
    END;

    -- Si le client a acheté ou loué le contenu
    IF v_achat IS NULL THEN
        IF v_loc IS NULL THEN
            -- Le nombre de locations en cours dépasse le nombre autorisé
            RAISE_APPLICATION_ERROR(-20001, 'Pour laisser une évaluation sur un produit il faut le louer ou l''acheter avant');
        END IF;
    END IF;
END;
/ 



--Déclencheur mettant à jour l'attribut evaluation d'un contenu lors de l'ajout d'une evaluation par rapport à ce contenu 
CREATE OR REPLACE TRIGGER trg_update_evaluation_moyenne
BEFORE INSERT ON Evaluation
FOR EACH ROW
DECLARE
    v_idContenu INTEGER;
    v_new_note_moyenne NUMBER(3,2);
    v_somme NUMBER;
    v_nbeval NUMBER;
BEGIN
    v_idContenu := :NEW.idContenu; 

    SELECT SUM(e.note) as somme, COUNT(*) as nbeval INTO v_somme, v_nbeval
    FROM Evaluation e
    WHERE e.idContenu = v_idContenu;
    
    IF (v_nbeval = 0) THEN 
        v_new_note_moyenne := :NEW.note;
    ELSE v_new_note_moyenne := (v_somme + :NEW.note)/(v_nbeval + 1 ); 
    END IF;
    UPDATE Contenu SET evaluation = v_new_note_moyenne WHERE idContenu = v_idContenu;
END;
/ 


