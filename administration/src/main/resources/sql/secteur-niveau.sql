--SECTEURS ACTIVITE

DELETE FROM secteur_activite;

INSERT INTO secteur_activite(id, libelle, actif) VALUES (1, 'Agriculture, sylviculture et pêche', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (2, 'Activités extractives', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (3, 'Activités de fabrication', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (4, 'Production et distribution d''électricité et de gaz', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (5, 'Production et distribution d''eau, assainissement', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (6, 'Traitement des déchets et dépollutions', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (7, 'Construction', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (8, 'Commerce', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (9, 'Transports et entreposage', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (10, 'Hébergement et restauration', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (11, 'Information et communication', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (12, 'Activités financières et d''assurance', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (13, 'Activités immobilières', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (14, 'Activités spécialisées, Scientifiques et techniques', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (15, 'Activités de services, de soutien et de Bureau', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (16, 'Activités d’administration publique', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (17, 'Enseignement', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (18, 'Activités pour la santé humaine et l’action sociale', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (19, 'Activités artistiques, sportives et récréatives', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (20, 'Autres activités de services N.C.A', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (21, 'Activités spéciales des ménages', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (22, 'Activités des organisations extraterritoriales', 1);


-- Niveau d'etude --

DELETE FROM niveau_etude;

INSERT INTO niveau_etude(id, libelle, actif) VALUES (1, 'Aucune scolarité formelle', 1);
INSERT INTO niveau_etude(id, libelle, actif) VALUES (2, 'École primaire', 1);
INSERT INTO niveau_etude(id, libelle, actif) VALUES (3, 'Secondaire (premier cycle)', 1);
INSERT INTO niveau_etude(id, libelle, actif) VALUES (4, 'Secondaire supérieur (second cycle)', 1);
INSERT INTO niveau_etude(id, libelle, actif) VALUES (5, 'Université, études de premier cycle', 1);
INSERT INTO niveau_etude(id, libelle, actif) VALUES (6, 'Université, études supérieures', 1);
INSERT INTO niveau_etude(id, libelle, actif) VALUES (7, 'Ne sais pas', 1);