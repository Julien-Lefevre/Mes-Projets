--Liste par ordre alphabétique des titres de contenu dont le thème principal comprend le mot Anime ou Fantasy.
SELECT DISTINCT titre
FROM Contenu
WHERE idTheme IN (SELECT idtheme FROM theme WHERE nomtheme LIKE '%Anime%' OR nomtheme LIKE '%Fantasy%')
ORDER BY titre ASC;


--Liste (titre et thème) des contenus empruntés par Durand entre janvier et mars 2024
SELECT co.titre, t.nomtheme
FROM Personnes p
                join Client cl on p.idPersonne = cl.idPersonne
                join Location l on cl.idClient = l.idClient
                join Contenu co on l.idContenu = co.idContenu
                join Theme t on co.idTheme = t.idTheme
WHERE p.nom = 'Durand'
AND   l.datedebut BETWEEN TO_DATE ('01/2024', 'MM/YYYY') AND TO_DATE ('03/2024', 'MM/YYYY')
ORDER BY co.titre ASC;



--Pour chaque abonné, son nombre de contenus empruntés et le nombre qu’il peut encore emprunter compte tenu de son abonnement.
SELECT A.idClient, A.nbLocEncours, (TA.nbmaxcontenusloues - A.nbLocEncours) as locationsRestantes
FROM Abonnement A join TypeAbonnement TA on A.idTypeAbonnement = TA.idTypeAbonnement 
WHERE A.datefin IS null
OR    A.datefin > SYSDATE;


--Liste (sans doublon) des acteurs ayant tourné un film réalisé par Martin Scorsese ; pour chaque acteur vous indiquerez le nombre de films tournés avec ce réalisateur.
SELECT p.nom, COUNT(*) AS nb_realisations
FROM Contenu c 
            join Realisateur r on c.idcontenu = r.idcontenu
            join acteur a on r.idcontenu = a.idcontenu
            join personnes p on a.idpersonne = p.idpersonne
WHERE r.idpersonne = (select idpersonne from personnes where nom = 'Martin Scorsese')
AND   c.idtypecontenu = (select idtypecontenu from typecontenu where nomtypecontenu = 'Cinema')
GROUP BY p.nom;



--Le ou les réalisateurs ayant réalisé le plus de contenus.
SELECT p.nom, COUNT(*) AS nb_contenus_realises
FROM Realisateur r
JOIN Personnes p ON r.idpersonne = p.idpersonne
GROUP BY p.nom
HAVING COUNT(*) = (
    SELECT MAX(nb_contenus)
    FROM (
        SELECT COUNT(*) AS nb_contenus
        FROM Realisateur
        GROUP BY idpersonne
    ) max_counts
);


--Liste des acteurs ayant joué dans plus de 10 films.
SELECT DISTINCT p.nom, COUNT(*) AS nb_films
FROM Contenu c 
            join Acteur a on c.idcontenu = a.idcontenu 
            join Personnes p on a.idPersonne = p.idPersonne
WHERE c.idtypecontenu = (select idtypecontenu from typecontenu where nomtypecontenu = 'Cinema')
GROUP BY p.nom
HAVING COUNT(*) > 10;

--Pour chaque contenu, nombre de fois où il a été loué.
SELECT DISTINCT l.idcontenu, c.titre, COUNT(*) AS nb_location
FROM Location l
             join Contenu c on l.idcontenu = c.idcontenu
GROUP BY l.idcontenu, c.titre;


--Liste des réalisateurs ayant réalisé plus de deux contenus moins de 16 ou moins de 18 ans.
SELECT p.nom
FROM Realisateur r
                join Contenu c on r.idcontenu = c.idContenu
                join Personnes p on r.idpersonne = p.idpersonne 
WHERE c.idclassification = (select idclassification from classification where niveau = '-16')
OR    c.idclassification = (select idclassification from classification where niveau = '-18')
GROUP BY p.nom
HAVING COUNT(*) > 2;


--Liste des clients habitant la même ville que Durand.
SELECT c.idclient, p.nom, p.prenom
FROM Client c JOIN Personnes p on c.idpersonne = p.idpersonne
WHERE c.Ville = (SELECT Ville from client where idpersonne = (select idpersonne from personnes where nom = 'Durand'))
and   p.nom != 'Durand';


--Nombre de réalisateurs n’ayant pas réalisé de contenus sur le thème Comedies
SELECT COUNT(DISTINCT p.nom)
from personnes p join realisateur r on p.idpersonne = r.idpersonne
where r.idpersonne NOT IN (
                            SELECT re.idpersonne
                            FROM contenu c
                                        join realisateur re on c.idcontenu = re.idcontenu
                            WHERE c.idtheme = (select idtheme from theme where nomtheme = 'Comedies'));



--Liste des clients n’ayant jamais loué de contenus.
SELECT cl.idclient, p.nom, p.prenom
FROM client cl join personnes p on cl.idpersonne = p.idpersonne
WHERE cl.idclient NOT IN (
                            SELECT DISTINCT l.idclient
                            FROM Location l
);


--Liste des contenus français pour la jeunesse n’ayant jamais été empruntés.
SELECT c.idcontenu, c.titre
FROM contenu c
WHERE c.idtypecontenu = (select idtypecontenu from typecontenu where nomtypecontenu = 'Jeunesse')
AND   c.idpays = (select idpays from pays where nompays = 'France')
AND   c.idcontenu NOT IN (
                            SELECT DISTINCT l.idcontenu
                            FROM Location l
);


--Liste des abonnés VIP ayant loué au moins une fois un divertissement au cours des 3 derniers mois.
SELECT DISTINCT a.idabonnement, p.nom, p.prenom
FROM Location l
            join Client cl on l.idclient = cl.idclient
            join Abonnement a on cl.idclient = a.idclient
            join Personnes p on cl.idpersonne = p.idpersonne
            join Contenu ct on l.idcontenu = ct.idcontenu
WHERE ct.idtypecontenu = (select idtypecontenu from typecontenu where nomtypecontenu = 'Divertissement')
AND   a.idtypeabonnement = (select idtypeabonnement from typeabonnement where nomabonnement = 'VIP')
AND   l.datedebut >= ADD_MONTHS(SYSDATE, -3);



--Liste des réalisateurs dont le nombre de réalisation est supérieur au nombre moyen de réalisations.
SELECT p.idpersonne ,p.nom
FROM realisateur r
            join personnes p on r.idPersonne = p.idPersonne
GROUP BY p.idpersonne, p.nom
HAVING COUNT (*) > (
                    SELECT AVG(nb_contenus_realises) AS nombre_moyen_contenus_realises
                    FROM (
                        SELECT COUNT(*) AS nb_contenus_realises
                        FROM Realisateur
                        GROUP BY idpersonne
                    )
);




--Liste des réalisateurs ayant réalisé des contenus de thèmes Sci-Fi & Fantasy et TV Action & Adventure (ceux qui ont réalisé les deux).
SELECT DISTINCT re.idpersonne, p.nom
FROM realisateur re join Personnes p on re.idpersonne = p.idpersonne
WHERE re.idpersonne NOT IN (
                            SELECT DISTINCT rel.idpersonne
                            FROM contenu c
                                    join realisateur rel on c.idcontenu = rel.idcontenu
                                    join Personnes p on rel.idpersonne = p.idpersonne
                            WHERE c.idtheme IN (select idtheme from theme where nomtheme != CONCAT ('TV Action &', ' Adventure'))
                            )
AND re.idpersonne NOT IN (
                            SELECT DISTINCT rel.idpersonne
                            FROM contenu c
                                    join realisateur rel on c.idcontenu = rel.idcontenu
                                    join Personnes p on rel.idpersonne = p.idpersonne
                            WHERE c.idtheme IN (select idtheme from theme where nomtheme != CONCAT ('Sci-Fi &', ' Fantasy'))
                            );




--Le ou les réalisateurs ayant réalisé le ou les films les plus loués.
SELECT p.nom
FROM Realisateur r
JOIN Personnes p ON r.idpersonne = p.idpersonne
JOIN Contenu c ON r.idcontenu = c.idcontenu
WHERE r.idcontenu IN (
                        SELECT c.idcontenu
                        FROM Location l join Contenu c on l.idcontenu = c.idcontenu
                        GROUP BY c.idcontenu
                        HAVING COUNT(*) = (
                                            SELECT MAX(nb_contenus)
                                            FROM (
                                            SELECT COUNT(*) AS nb_contenus
                                            FROM Location l
                                                join Contenu c on l.idcontenu = c.idcontenu
                                            GROUP BY c.idcontenu
    )))
AND c.idtypecontenu = (SELECT idtypecontenu FROM typecontenu where nomtypecontenu = 'Cinema');


--Liste des films n’ayant aucun mot clé en commun avec le film The Irishman.
SELECT DISTINCT c.idcontenu, c.titre
FROM Contenu c
WHERE c.idtypecontenu = (select idtypecontenu from typecontenu where nomtypecontenu = 'Cinema')
AND  c.idcontenu NOT IN
        (
        SELECT c.idcontenu
        FROM Contenu c join assoc_contenu_motcle acm on c.idcontenu = acm.idcontenu
        WHERE acm.idmotcle IN 
            (
            SELECT acm.idmotcle
            FROM Contenu c join assoc_contenu_motcle acm on c.idcontenu = acm.idcontenu
            WHERE c.titre = 'The Irishman'
            )
        )
;

--Liste des films ayant au moins un mot clé en commun avec le film The Irishman.
SELECT DISTINCT c.idcontenu, c.titre
FROM Contenu c
WHERE c.idtypecontenu = (select idtypecontenu from typecontenu where nomtypecontenu = 'Cinema')
AND c.titre != 'The Irishman'
AND  c.idcontenu IN
        (
        SELECT c.idcontenu
        FROM Contenu c join assoc_contenu_motcle acm on c.idcontenu = acm.idcontenu
        WHERE acm.idmotcle IN 
            (
            SELECT acm.idmotcle
            FROM Contenu c join assoc_contenu_motcle acm on c.idcontenu = acm.idcontenu
            WHERE c.titre = 'The Irishman'
            )
        )
;



--Liste des films ayant au moins les mêmes mots clés que le film The Irishman.
SELECT DISTINCT c.idcontenu, c.titre AS nom_film
FROM Contenu c
LEFT JOIN assoc_contenu_motcle mc_irishman ON c.idContenu = mc_irishman.idContenu
LEFT JOIN MotCle m_irishman ON mc_irishman.idMotCle = m_irishman.idMotCle
WHERE NOT EXISTS (
    SELECT m.idMotCle
    FROM MotCle m
    WHERE m.idMotCle IN (
        SELECT mc.idMotCle
        FROM assoc_contenu_motcle mc
        WHERE mc.idContenu = (SELECT idContenu FROM Contenu WHERE titre = 'The Irishman')
    )
    AND m.idMotCle NOT IN (
        SELECT mc.idMotCle
        FROM assoc_contenu_motcle mc
        WHERE mc.idContenu = c.idContenu
    )
)
AND c.idtypecontenu = (select idtypecontenu from typecontenu where nomtypecontenu = 'Cinema')
AND c.titre != 'The Irishman';



--Liste des films ayant exactement les mêmes mots clés que le film The Irishman.
SELECT c.idcontenu, c.titre
FROM Contenu c
WHERE idcontenu in (
    SELECT acm2.idcontenu
    from assoc_contenu_motcle acm1 left outer join assoc_contenu_motcle acm2 on acm1.idmotcle = acm2.idmotcle right outer join assoc_contenu_motcle acm3 on acm2.idmotcle = acm3.idmotcle
    where acm1.idcontenu in (
        SELECT idcontenu
        FROM contenu
        WHERE idcontenu = (SELECT idcontenu FROM contenu WHERE titre = 'The Irishman'))
    and acm3.idcontenu in (
        SELECT idcontenu
        FROM contenu
        WHERE idcontenu = (SELECT idcontenu FROM contenu WHERE titre = 'The Irishman'))
    and acm1.idmotcle = acm3.idmotcle
    group by acm2.idcontenu
    HAVING COUNT(*) = (select COUNT(*) as nbmotcle
                        from assoc_contenu_motcle acm
                        where idcontenu = (select idcontenu from contenu where titre = 'The Irishman')
                        )
)
and c.titre != 'The Irishman'
GROUP BY c.idcontenu, c.titre
HAVING (
    SELECT COUNT(*) 
    FROM assoc_contenu_motcle 
    WHERE idcontenu = c.idcontenu
) = (
    SELECT COUNT(*) 
    FROM assoc_contenu_motcle 
    WHERE idcontenu = (
        SELECT idcontenu 
        FROM contenu 
        WHERE titre = 'The Irishman'
    )
)
;







