
--QUESTIONS ELIGIBILITE
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (1, 'QE1', 'La société a-t-elle sa résidence fiscale en côte d''ivoire?', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (2, 'QE2', 'L''entreprise a au moins deux années d''existence par rapport à la date de début d''activité?', 1, null);

--PARAMETRE
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (1, 'P1', 'Gestion des Opérations', 8, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (2, 'P2', 'Marketing et Gestion Commerciale', 8, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (3, 'P3', 'Innovation', 9, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (4, 'P4', 'Gestion des Ressources Humaines', 8, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (5, 'P5', 'Capacité Managériale', 5, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (6, 'P6', 'Solvabilité', 2, 1);

--QUESTIONS QUALITATIVES 
--POUR LE PARAMETRE 1
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (10, 'Q1P1', 'Pourcentage de travailleurs qualifiés sur effectif total?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (11, 'Q2P1', 'Existence d''un manuel des procédures?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (12, 'Q3P1', 'Sous quelle forme existe le manuel?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (13, 'Q4P1', 'Quel est le niveau d''utilisation du manuel?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (14, 'Q5P1', 'En général, comment évaluez vous la livraison de vos produits et services?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (15, 'Q6P1', 'En général, le coût de votre projet respecte-t-il le budget alloué?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (16, 'Q7P1', 'Votre organisation applique-t-elle des normes de gestion de projet?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (17, 'Q8P1', 'Disposez vous d''un service d''assistance? Si oui sous quelle forme?', 1, 1);

--REPONSES
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (1, 'R1Q1P1', '<20%', 5, 1, 10);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (2, 'R2Q1P1', '>= 20% et < 40%', 4, 1, 10);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (3, 'R3Q1P1', '>= 40% et 60%', 3, 1, 10);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (4, 'R4Q1P1', '>= 60% et < 80%', 2, 1, 10);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (5, 'R5Q1P1', '>= 80%', 1, 1, 10);

--RATIOS
INSERT INTO ratio(id, code, libelle, formule, ponderation, unite, actif) VALUES (1, 'R1', 'Ratio de liquidité (bceao)', '(actif circulant+trésorerie actif)/(passif circulant+trésorerie passif) % (env 100%)', 15, '%', 1);
INSERT INTO ratio(id, code, libelle, formule, ponderation, unite, actif) VALUES (2, 'R2', 'Ratio de rentabilité (bceao)', 'Résultat net/CA % (+- 5%)', 20, '%', 1);

--CALIBRAGE
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (1, null, 0.30, 1, 1, 1);

--PONDERATION
INSERT INTO ponderation(id, code_score, ponderation, actif, id_parametre) VALUES (1, 'SF', 20, 1, null);

DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE SEQUENCE hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 25
  CACHE 1;
GRANT ALL PRIVILEGES ON SEQUENCE hibernate_sequence TO postgres;