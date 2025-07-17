--un lien vers une bande annonce doit commencer par http
ALTER TABLE Contenu
ADD CONSTRAINT check_bandeannonce_url CHECK (bandeannonce IS NULL OR SUBSTR(bandeannonce, 1, 4) = 'http');

--l’évaluation sur un contenu doit être comprise entre 0 et 5
ALTER TABLE Evaluation
ADD CONSTRAINT check_evaluation_note CHECK (note BETWEEN 0 AND 5);

--l’année de production doit être supérieure à 1900
ALTER TABLE Contenu
ADD CONSTRAINT check_annee_production CHECK (annee IS NULL or annee > 1900);


--les mots de passe doivent avoir au moins 8 caractères, contenir des lettres et des chiffres et inclure au moins une majuscule et un caractère spécial
CREATE OR REPLACE TRIGGER trg_check_password_complexity
BEFORE INSERT ON Client
FOR EACH ROW
DECLARE
    v_password VARCHAR(255);
BEGIN
    v_password := :NEW.motDePasse;
    IF NOT (
        LENGTH(v_password) >= 8 AND -- Au moins 8 caractères
        REGEXP_LIKE(v_password, '[a-zA-Z]+') AND -- Au moins une lettre
        REGEXP_LIKE(v_password, '[0-9]+') AND -- Au moins un chiffre
        REGEXP_LIKE(v_password, '[[:upper:]]+') AND -- Au moins une majuscule
        REGEXP_LIKE(v_password, '([^a-zA-Z0-9_]+)') -- Au moins un caractère spécial
    ) THEN
        -- Si la complexité du mot de passe n'est pas respectée, annuler l'insertion
        RAISE_APPLICATION_ERROR(-20001, 'Le mot de passe ne respecte pas la complexité requise.');
    END IF;
END;
/

--les contenus pour la jeunesse ne peuvent pas être des contenus moins de 16 ou moins de 18 ans
CREATE OR REPLACE TRIGGER trg_check_jeunesse
BEFORE INSERT ON Contenu
FOR EACH ROW
DECLARE
    v_idtypecontenu INTEGER;
    v_idJeunesse INTEGER;
    v_idClassification INTEGER;
    v_moins16 INTEGER;
    v_moins18 INTEGER;
BEGIN
    v_idtypecontenu := :NEW.idtypecontenu;
    select idtypecontenu into v_idJeunesse from typecontenu where nomtypecontenu = 'Jeunesse';
    v_idClassification := :NEW.idClassification;
    select idclassification into v_moins16 from Classification where niveau = ('-16');
    select idclassification into v_moins18 from Classification where niveau = ('-18');
    IF (
        v_idtypecontenu = v_idJeunesse AND (
        v_idClassification = v_moins16 OR 
        v_idClassification = v_moins18
        )
    ) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Le contenu jeunesse ne peut pas être un contenu déconseillé aux moins de 16 ou moins de 18 ans.');
    END IF;
END;
/


--le nombre de locations des abonnés doit être inférieur ou égal à celui autorisé par leurs abonnements
CREATE OR REPLACE TRIGGER trg_check_location
BEFORE INSERT ON Location
FOR EACH ROW
DECLARE
    v_max_locations INTEGER;
    v_current_locations INTEGER;
    v_datedebut DATE;
BEGIN
    -- Vérifier si le client a un abonnement
    v_datedebut := :NEW.datedebut;
    SELECT a.nbLocEnCours, ta.nbmaxcontenusloues INTO v_current_locations, v_max_locations
    FROM Abonnement a, typeAbonnement ta
    WHERE idClient = :NEW.idClient
    AND a.idtypeabonnement = ta.idtypeabonnement
    AND (a.datedebut <= v_datedebut AND (a.datefin is null or a.datefin >= v_datedebut));
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Aucun abonnement trouvé pour le client
            v_max_locations := NULL;
            v_current_locations := NULL;
    

    -- Si le client a un abonnement, vérifier le nombre maximum de locations autorisées
    IF v_max_locations IS NOT NULL THEN
        IF v_current_locations >= v_max_locations THEN
            -- Le nombre de locations en cours dépasse le nombre autorisé
            RAISE_APPLICATION_ERROR(-20001, 'Le nombre de locations en cours dépasse le nombre autorisé par l''abonnement.');
        END IF;
    END IF;
END;
/


-- un client ne peut pas avoir deux abonnements en même temps 
CREATE OR REPLACE TRIGGER trg_check_client_onlyone_abonnement
BEFORE INSERT ON Abonnement
FOR EACH ROW
DECLARE
    v_idClient INTEGER;
    v_count INTEGER;
    v_datedebut DATE;
BEGIN
    v_idClient := :NEW.idclient;
    v_datedebut := :NEW.datedebut;
    select count(*) into v_count from Abonnement where idclient = v_idClient and ((v_datedebut >= datedebut AND datefin >= v_datedebut) OR (v_datedebut >= datedebut AND datefin IS NULL));

    IF (
        v_count >= 1
        
    ) THEN
        RAISE_APPLICATION_ERROR(-20001, 'Le client a déjà un abonnement en cours.');
    END IF;
END;
/






