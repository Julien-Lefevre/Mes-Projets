--Insertion dans la Table Personnes
INSERT INTO Personnes(prenom, nom)
VALUES
('norris','poulin'),
('soren','despins'),
('baptiste','grandpre'),
('astrid','bisson'),
('ferragus','lanteigne'),
('cher','cressac'),
('jay','lagace'),
('archard','bourassa'),
('verney','plouffe'),
('sibyla','leroy'),
('frederique','rossignol'),
('belisarda','blais'),
('philipa','salmons'),
('claudette','lemieux'),
('talon','Durand');


--Insertion dans la table Client
INSERT INTO Client(idPersonne, adresseClient,codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES 
((SELECT idPersonne
FROM Personnes
WHERE nom = 'poulin'
AND prenom = 'norris'),'62 boulevard Riviere', 86000, 'Poitiers',  'norris.poulin@gmail.com', 0719537003, 'M0tDeP@ss1');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'despins'
AND prenom = 'soren'),'45 rue Sébastopol', 17100, 'Saintes', 'soren.despins@gmail.com', 0622444702,'Password12!');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'grandpre'
AND prenom = 'baptiste'),'43 rue des Lacs', 95220, 'Herblay', 'baptiste.grandpre@gmail.com', 0760522590,'Secur!ty2023');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'bisson'
AND prenom = 'astrid'),'56 rue Victor Hugo', 78700, 'Conflans-sainte-honorine', 'astrid.bisson@gmail.com', 0780290882 ,'P@ssw0rd!');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'lanteigne'
AND prenom = 'ferragus'),'65 quai des Belges', 59600, 'MAUBEUGE', 'ferragus.lanteigne@gmail.com', 0653536044,'9aBcdEfg!');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'cressac'
AND prenom = 'cher'),'5 rue petite fuserie', 38300, 'Bourgoin-jallieu', 'cher.cressac@gmail.com', 0549670475,'Ch@ng3Me');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'lagace'
AND prenom = 'jay'),'69 rue Descartes', 67200, 'Strasbourg', 'jay.lagace@gmail.com', 0700016676,'P@55sw0rd');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'bourassa'
AND prenom = 'archard'),'47 rue Léon Dierx', 93190, 'Livry-Gargan', 'archard.bourassa@gmail.com', 0738612525,'Examp!e123');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'plouffe'
AND prenom = 'verney'),'7 avenue de l’Amendier', 41000, 'Blois', 'verney.plouffe@gmail.com', 0749111602,'B!gP@ssw0rd');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'leroy'
AND prenom = 'sibyla'),'4 place Napoléon', 02000, 'Laon', 'sibyla.leroy@gmail.com', 0633695745,'7estP@ssword');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'rossignol'
AND prenom = 'frederique'),'28 avenue Ferdinand de Lesseps', 06130, 'Grasse', 'frederique.rossignol@gmail.com', 0707656239,'H@ckM3e!');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'blais'
AND prenom = 'belisarda'),'11 rue Jean Vilar', 60000, 'Villeurbanne', 'belisarda.blais@gmail.com', 0653427513,'1234@bcD');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'salmons'
AND prenom = 'philipa'),'23 boulevard Amiral Courbet', 45100, 'Orleans', 'philipa.salmons@gmail.com', 0556145280,'5tr0ngP@ss');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'lemieux'
AND prenom = 'claudette'),'9 rue de Genève', 80000, 'Amiens', 'claudette.lemieux@gmail.com', 0698770462,'L0ngP@ssword');

INSERT INTO Client(idPersonne, adresseClient, codepostal, ville, mail, NumeroTelephone, MotdePasse)
VALUES
((SELECT idPersonne
FROM Personnes
WHERE nom = 'Durand'
AND prenom = 'talon'),'59 rue Margeurite', 69100, 'Villeurbanne', 'talon.durand@gmail.com', 0723144036,'H!ghSecur1ty'); 




--Insertion et création d'Abonnements
INSERT INTO Abonnement(idClient, idTypeAbonnement, dateDebut, dateFin)
VALUES
( 
    (SELECT idClient FROM Personnes p join Client c on p.idPersonne = c.idPersonne WHERE nom = 'Durand' AND prenom = 'talon'),
    (SELECT idTypeAbonnement FROM TypeAbonnement WHERE nomAbonnement = 'Basique'),
    TO_DATE('28/04/2024', 'DD/MM/YYYY'),
    NULL
);

INSERT INTO Abonnement(idClient, idTypeAbonnement, dateDebut, dateFin)
VALUES
(
    (SELECT idClient FROM Personnes p join Client c on p.idPersonne = c.idPersonne WHERE nom = 'lemieux' AND prenom = 'claudette'),
    (SELECT idTypeAbonnement FROM TypeAbonnement WHERE nomAbonnement = 'Basique'),
    TO_DATE('01/01/2024', 'DD/MM/YYYY'),
    NULL
);

INSERT INTO Abonnement(idClient, idTypeAbonnement, dateDebut, dateFin)
VALUES
(
    (SELECT idClient FROM Personnes p join Client c on p.idPersonne = c.idPersonne WHERE nom = 'salmons' AND prenom = 'philipa'),
    (SELECT idTypeAbonnement FROM TypeAbonnement WHERE nomAbonnement = 'Basique'),
    TO_DATE('01/01/2024', 'DD/MM/YYYY'),
    NULL
);

INSERT INTO Abonnement(idClient, idTypeAbonnement, dateDebut, dateFin)
VALUES
(
    (SELECT idClient FROM Personnes p join Client c on p.idPersonne = c.idPersonne WHERE nom = 'plouffe' AND prenom = 'verney'),
    (SELECT idTypeAbonnement FROM TypeAbonnement WHERE nomAbonnement = 'Premium'),
    TO_DATE('01/01/2024', 'DD/MM/YYYY'),
    TO_DATE('30/06/2024', 'DD/MM/YYYY')
);

INSERT INTO Abonnement(idClient, idTypeAbonnement, dateDebut, dateFin)
VALUES
(
    (SELECT idClient FROM Personnes p join Client c on p.idPersonne = c.idPersonne WHERE nom = 'rossignol' AND prenom = 'frederique'),
    (SELECT idTypeAbonnement FROM TypeAbonnement WHERE nomAbonnement = 'Premium'),
    TO_DATE('01/01/2024', 'DD/MM/YYYY'),
    TO_DATE('27/04/2024', 'DD/MM/YYYY')
);

INSERT INTO Abonnement(idClient, idTypeAbonnement, dateDebut, dateFin)
VALUES
(
    (SELECT idClient FROM Personnes p join Client c on p.idPersonne = c.idPersonne WHERE nom = 'lanteigne' AND prenom = 'ferragus'),
    (SELECT idTypeAbonnement FROM TypeAbonnement WHERE nomAbonnement = 'Premium'),
    TO_DATE('01/01/2024', 'DD/MM/YYYY'),
    TO_DATE('31/05/2024', 'DD/MM/YYYY')
);

INSERT INTO Abonnement(idClient, idTypeAbonnement, dateDebut, dateFin)
VALUES
(
    (SELECT idClient FROM Personnes p join Client c on p.idPersonne = c.idPersonne WHERE nom = 'poulin' AND prenom = 'norris'),
    (SELECT idTypeAbonnement FROM TypeAbonnement WHERE nomAbonnement = 'VIP'),
    TO_DATE('01/01/2024', 'DD/MM/YYYY'),
    TO_DATE('30/06/2024', 'DD/MM/YYYY')
);





--Insertion d'achats par les clients
INSERT INTO Achat(idClient, idContenu, dateAchat, idVersion, prix)
VALUES
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'poulin' AND prenom = 'norris'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'poulin' AND prenom = 'norris'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('21/02/2024', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'poulin' AND prenom = 'norris'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('14/08/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
),
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'despins' AND prenom = 'soren'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'despins' AND prenom = 'soren'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'despins' AND prenom = 'soren'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
),
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'grandpre' AND prenom = 'baptiste'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'grandpre' AND prenom = 'baptiste'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'grandpre' AND prenom = 'baptiste'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
),
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'bisson' AND prenom = 'astrid'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'bisson' AND prenom = 'astrid'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'bisson' AND prenom = 'astrid'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
),
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'lanteigne' AND prenom = 'ferragus'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'lanteigne' AND prenom = 'ferragus'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'lanteigne' AND prenom = 'ferragus'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
),
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'cressac' AND prenom = 'cher'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'cressac' AND prenom = 'cher'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'cressac' AND prenom = 'cher'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
),
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'lagace' AND prenom = 'jay'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'lagace' AND prenom = 'jay'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'lagace' AND prenom = 'jay'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
),
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'bourassa' AND prenom = 'archard'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'bourassa' AND prenom = 'archard'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
),
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'bourassa' AND prenom = 'archard'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixAchat FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
);




--Insertion de Locations par les clients
INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'plouffe' AND prenom = 'verney'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
);

INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'plouffe' AND prenom = 'verney'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('30/04/2024', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
);

INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'plouffe' AND prenom = 'verney'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('14/08/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'leroy' AND prenom = 'sibyla'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'leroy' AND prenom = 'sibyla'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
);

INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'leroy' AND prenom = 'sibyla'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'rossignol' AND prenom = 'frederique'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'rossignol' AND prenom = 'frederique'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
);



INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'rossignol' AND prenom = 'frederique'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'blais' AND prenom = 'belisarda'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
);



INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'blais' AND prenom = 'belisarda'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
);



INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'blais' AND prenom = 'belisarda'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'salmons' AND prenom = 'philipa'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'salmons' AND prenom = 'philipa'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
);



INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'salmons' AND prenom = 'philipa'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'lemieux' AND prenom = 'claudette'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'lemieux' AND prenom = 'claudette'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'lemieux' AND prenom = 'claudette'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
);



INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
( 
    	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'Durand' AND prenom = 'talon'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema' AND v.qualite = 'HD')
);


INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'Durand' AND prenom = 'talon'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						INNER JOIN Saison s ON c.idContenu = s.idContenu WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND s.numSaison = 2),
    	TO_DATE('28/01/2023', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						INNER JOIN Saison s ON c.idContenu = s.idContenu
    						WHERE c.titre = 'Love on the Spectrum' AND t.nomTypeContenu = 'Saison' AND v.qualite = 'SD' AND s.numSaison = 2)
);



INSERT INTO Location(idClient, idContenu, dateDebut, dateFin, idVersion, prix)
VALUES
(
	(SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'Durand' AND prenom = 'talon'),
    	(SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie'),
    	TO_DATE('28/01/2024', 'DD/MM/YYYY'),
        NULL,
    	(SELECT v.idVersion FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD'),
    	(SELECT v.prixLocation FROM Version v INNER JOIN TypeContenu t ON v.idTypeContenu = t.idTypeContenu 
    						INNER JOIN Contenu c ON t.idTypeContenu = c.idTypeContenu 
    						WHERE c.titre = 'Resurrection: Ertugrul' AND t.nomTypeContenu = 'Serie' AND v.qualite = 'SD')
);




--Insertion de quelques évaluations
INSERT INTO Evaluation(idClient, idContenu, note)
VALUES
(
    (SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'plouffe' AND prenom = 'verney'),
    (SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    1
);
INSERT INTO Evaluation(idClient, idContenu, note)
VALUES
(
    (SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'leroy' AND prenom = 'sibyla'),
    (SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    1
);
INSERT INTO Evaluation(idClient, idContenu, note)
VALUES
(
    (SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'rossignol' AND prenom = 'frederique'),
    (SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    1
);
INSERT INTO Evaluation(idClient, idContenu, note)
VALUES
(
    (SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'blais' AND prenom = 'belisarda'),
    (SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    4
);
INSERT INTO Evaluation(idClient, idContenu, note)
VALUES
(
    (SELECT idClient FROM Personnes p INNER JOIN  Client c ON p.idPersonne = c.idPersonne WHERE nom = 'salmons' AND prenom = 'philipa'),
    (SELECT c.idContenu FROM Contenu c INNER JOIN TypeContenu t ON c.idTypeContenu = t.idTypeContenu WHERE c.titre = 'Intrusion' AND t.nomTypeContenu = 'Cinema'),
    2
);


