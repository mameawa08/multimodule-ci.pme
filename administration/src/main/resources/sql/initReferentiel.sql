--QUESTIONNAIRE ELIGIBILITE
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (1, 'QE1', 'La société a-t-elle sa résidence fiscale en côte d''ivoire?', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (2, 'QE2', 'L''entreprise a au moins deux années d''existence par rapport à la date de début d''activité?', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (3, 'QE3', 'L''entreprise respecte le critère : « n’avoir pas déposé de déclaration de cessation de paiement sur les 3 derniers mois', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (4, 'QE4', 'Conformité fiscale et sociale', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (5, 'QE5', 'L''entreprise est à jour du règlement de ses obligations fiscales sur le dernier exercice ou bénéficie d’un moratoire du paiement de ses impôts négocié avec l’administration fiscale.', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (6, 'QE6', 'L''entreprise est à jour du règlement de ses obligations sociales sur le dernier exercice ou bénéficie d’un moratoire du paiement de ses charges sociales négocié avec l’administration sociale.', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (7, 'QE7', 'Statut de l’entreprise', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (8, 'QE8', 'L''entreprise a enregistré un chiffre d’affaires annuel maximum d’un milliard (1.000.000.000) de F.CFA, sur au moins deux des trois derniers exercices', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (9, 'QE9', 'L''entreprise a enregistré : l’entreprise est au système normal de comptabilité', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (10, 'QE10', 'Micro : un chiffre d’affaires annuel inférieur à trente millions (30 000 000) FCFA sur les exercices N-1 et N-2 et effectif < 10', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (11, 'QE11', 'Petite : un chiffre d’affaires moyen annuel compris entre 30 000 001 FCFA et 150 000 000 FCFA sur les exercices N-1 et N-2 Nb employés < 50 employés', 1, null);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (12, 'QE12', 'Moyenne : un chiffre d’affaires moyen annuel compris entre 150 000 001 FCFA et 1 milliard sur les exercices N-1 et N-2  nb employés < 200 ', 1, null);

--PARAMETRE
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (1, 'P1', 'Gestion des Projets', 8, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (2, 'P2', 'Marketing et Gestion Commerciale', 10, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (3, 'P3', 'Innovation & Qualité', 10, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (4, 'P4', 'Gestion des Ressources Humaines', 10, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (5, 'P5', 'Capacité Managériale', 5, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (6, 'P6', 'Comptabilité & Finance', 9, 1);
INSERT INTO parametre(id, code, libelle, nbre_question, actif) VALUES (7, 'P7', 'Production et capacité technique', 5, 1);

--QUESTIONS QUALITATIVES 
--POUR LE PARAMETRE 1
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (13, 'Q1P1', 'Pourcentage de travailleurs qualifiés sur effectif total?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (14, 'Q2P1', 'Existence d''un manuel des procédures?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (15, 'Q3P1', 'Sous quelle forme existe le manuel?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (16, 'Q4P1', 'Quel est le niveau d''utilisation du manuel?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (17, 'Q5P1', 'En général, comment évaluez vous la livraison de vos produits et services?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (18, 'Q6P1', 'En général, le coût de votre projet respecte-t-il le budget alloué?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (19, 'Q7P1', 'Votre organisation applique-t-elle des normes de gestion de projet?', 1, 1);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (20, 'Q8P1', 'Disposez vous d''un service d''assistance? Si oui sous quelle forme?', 1, 1);

--REPONSES
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (1, 'R1Q1P1', '<20%', 5, 1, 13);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (2, 'R2Q1P1', '>= 20% et < 40%', 4, 1, 13);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (3, 'R3Q1P1', '>= 40% et 60%', 3, 1, 13);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (4, 'R4Q1P1', '>= 60% et < 80%', 2, 1, 13);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (5, 'R5Q1P1', '>= 80%', 1, 1, 13);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (6, 'R1Q2P1', 'Oui, il couvre toutes les aspects (exécution-gestion-suivi) du projet', 5, 1, 14);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (7, 'R2Q2P1', 'Oui, mais ne couvre que certains aspects du projet', 4, 1, 14);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (8, 'R3Q2P1', 'Oui, mais ne couvre que les procédures d''achat', 3, 1, 14);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (9, 'R4Q2P1', 'Il existe un manuel sommaire de gestion de projets dans l''entreprise', 2, 1, 14);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (10, 'R5Q2P1', 'Non, il n y a pas de manuel des procédures de gestion de projets dans l''entreprise', 1, 1, 14);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (11, 'R1Q3P1', 'Automatisé à travers le système d''informations', 5, 1, 15);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (12, 'R2Q3P1', 'Existe sous la forme d''outils à utiliser par le personnel', 4, 1, 15);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (13, 'R3Q3P1', 'Existe sous la forme de support électronique accessible à tout le personnel', 3, 1, 15);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (14, 'R4Q3P1', 'Existe sous la forme d''un rapport papier exploitable par le personnel', 2, 1, 15);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (15, 'R1Q4P1', 'Systématiquement appliqué car intégré au système d''information', 5, 1, 16);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (16, 'R2Q4P1', 'Entièrement appliqué – contrôlé régulièrement', 4, 1, 16);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (17, 'R3Q4P1', 'En partie appliqué', 3, 1, 16);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (18, 'R4Q4P1', 'A titre indicatif seulement', 2, 1, 16);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (19, 'R5Q4P1', 'Non utilisé', 1, 1, 16);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (20, 'R1Q5P1', 'En avance sur le programme', 5, 1, 17);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (21, 'R2Q5P1', 'Dans les temps', 3, 1, 17);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (22, 'R3Q5P1', 'En retard', 1, 1, 17);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (23, 'R1Q6P1', 'La plupart du temps', 4, 1, 18);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (24, 'R2Q6P1', 'Parfois', 3, 1, 18);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (25, 'R3Q6P1', 'Rarement', 2, 1, 18);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (26, 'R4Q6P1', 'Non', 1, 1, 18);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (27, 'R1Q7P1', 'Professionnel de la gestion de projet (PMP)', 5, 1, 19);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (28, 'R2Q7P1', 'Autre norme standard', 4, 1, 19);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (29, 'R3Q7P1', 'Logiciel de gestion de projet (par exemple, Microsoft Project, Primavera)', 3, 1, 19);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (30, 'R4Q7P1', 'Microsoft Office ou équivalent Open Source', 2, 1, 19);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (31, 'R5Q7P1', 'Rien', 1, 1, 19);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (32, 'R1Q8P1', 'Centre d''appels 24h/24', 5, 1, 20);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (33, 'R2Q8P1', 'Point de service client dédié', 4, 1, 20);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (34, 'R3Q8P1', 'Assistance Web (24/7)', 3, 1, 20);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (35, 'R4Q8P1', 'Un point focal, non dédié', 2, 1, 20);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (36, 'R5Q8P1', 'Aucun dispositif interne', 1, 1, 20);

--POUR LE PARAMETRE 2
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (21, 'Q1P2', 'Quel est le degré de connaissance du marché par le management de l''entreprise ?', 1, 2);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (22, 'Q2P2', 'Quelle est la valeur du réseau de distribution dont bénéficie l''entreprise ?', 1, 2);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (23, 'Q3P2', 'Poids des dépenses de marketing dans les dépenses totales', 1, 2);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (24, 'Q4P2', 'L''entreprise parvient-elle à réaliser ses objectifs de vente ?', 1, 2);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (25, 'Q5P2', 'Quelle est la contribution aux revenus de vos activités de marketing ?', 1, 2);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (26, 'Q6P2', 'Disposez vous d''un programme de promotion/rétention de la clientèle?', 1, 2);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (27, 'Q7P2', 'Disposez vous d''outils de gestion/rétention de la clientèle?', 1, 2);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (28, 'Q8P2', 'Nombre de retours de produits par les clients au cours des trois dernières années?', 1, 2);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (29, 'Q9P2', 'Quel est le degré d’utilisation de l’internet dans l''entreprise ?', 1, 2);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (30, 'Q10P2', 'Quel est le degré d’utilisation des réseaux sociaux dans l''entreprise ?', 1, 2);

--REPONSES
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (37, 'R1Q1P2', 'L''entreprise dispose d''informations à jour et fiables sur le marché national', 5, 1, 21);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (38, 'R2Q1P2', 'L''entreprise ne dispose pas de statistiques officielles mais a un réseau fiable pour avoir une bonne idée sur le marché', 4, 1, 21);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (39, 'R3Q1P2', 'L''entreprise ne dispose que de quelques informations incomplètes', 3, 1, 21);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (40, 'R4Q1P2', 'L''entreprise ne dispose que d''informations qualitatives sur le marché', 2, 1, 21);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (41, 'R5Q1P2', 'Le dirigeant considère que la connaissance du marché est inutile.', 1, 1, 21);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (42, 'R1Q2P2', 'L’entreprise dispose d’un réseau bien adapté à son marché et parfaitement fidélisé.', 5, 1, 22);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (43, 'R2Q2P2', 'L’entreprise dispose d’un réseau partiellement adapté à son marché.', 4, 1, 22);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (44, 'R3Q2P2', 'Le réseau utilisé n’est pas fidélisé ou bien il est commun avec des concurrents.', 3, 1, 22);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (45, 'R4Q2P2', 'L’entreprise n’a pas de réseau de vente. Elle dépend des commandes spontanées de la clientèle.', 2, 1, 22);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (46, 'R1Q3P2', '0%', 5, 1, 23);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (47, 'R2Q3P2', '> 0% et < 5%', 4, 1, 23);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (48, 'R3Q3P2', '>= 5% et 10%', 3, 1, 23);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (49, 'R4Q3P2', '= 10% et < 15%', 2, 1, 23);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (50, 'R5Q3P2', '>= 15%', 1, 1, 23);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (51, 'R1Q4P2', 'L’entreprise établit des prévisions de vente et réalise ses objectifs à + ou - 10%.', 5, 1, 24);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (52, 'R2Q4P2', 'L’entreprise établit des prévisions de vente et réalise ces chiffres à + ou - 20%.', 4, 1, 24);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (53, 'R3Q4P2', 'L’entreprise établit des prévisions mais n’arrive jamais au-delà de 60% de ses objectifs.', 3, 1, 24);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (54, 'R4Q4P2', 'L''entreprise établit de temps en temps des prévisions sans mesurer les résultats ', 2, 1, 24);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (55, 'R5Q4P2', 'L’entreprise ne fait jamais de prévisions. Elle livre sur demande.', 1, 1, 24);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (56, 'R1Q5P2', '> 10%', 5, 1, 25);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (57, 'R2Q5P2', '<=10% ; > 5%', 4, 1, 25);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (58, 'R3Q5P2', '<=5% ; > 1%', 3, 1, 25);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (59, 'R4Q5P2', '0%', 2, 1, 25);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (60, 'R5Q5P2', 'Ne sais pas', 1, 1, 25);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (61, 'R1Q6P2', 'Service après-vente', 5, 1, 26);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (62, 'R2Q6P2', 'Organisation d''événements et divertissements / Bulletins d''information', 4, 1, 26);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (63, 'R3Q6P2', 'Sponsoring', 3, 1, 26);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (64, 'R4Q6P2', 'Retours clients et Amélioration des produits/services', 2, 1, 26);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (65, 'R5Q6P2', 'Aucune', 1, 1, 26);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (66, 'R1Q7P2', 'L''entreprise dispose d''un logiciel (application) dédié à la gestion des clients (CRM)', 4, 1, 27);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (67, 'R2Q7P2', 'L''entreprise dispose d''une base de données automatisée de gestion des relations avec les clients', 3, 1, 27);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (68, 'R3Q7P2', 'L''entreprise dispose d''une base de données avec les coordoonnées des clients', 2, 1, 27);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (69, 'R4Q7P2', 'Les informations sur les clients ne sont pas centralisées', 1, 1, 27);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (70, 'R1Q8P2', 'Aucun', 5, 1, 28);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (71, 'R2Q8P2', 'Moins de 2', 4, 1, 28);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (72, 'R3Q8P2', 'Entre 2 et 5', 3, 1, 28);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (73, 'R4Q8P2', 'Entre 5 et 10', 2, 1, 28);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (74, 'R5Q8P2', 'Plus de 10', 1, 1, 28);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (75, 'R1Q9P2', 'L’entreprise utilise Internet dans le cadre de ses activités pour commercialiser ses produits et services – dispose d’un site transactionnel', 5, 1, 29);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (76, 'R2Q9P2', 'L’entreprise utilise Internet dans le cadre de ses activités uniquement pour se faire connaitre par ses clients et fournisseurs et faire sa promotion en ligne – dispose uniquement d’un site informationnel', 4, 1, 29);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (77, 'R3Q9P2', 'L’entreprise utilise Internet dans le cadre de ses activités uniquement pour se faire connaitre par ses clients et fournisseurs et faire sa promotion en ligne – ne dispose pas de site internet', 3, 1, 29);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (78, 'R4Q9P2', 'L''entreprise ne dispose pas actuellement de site internet et n’utilise pas encore internet dans le cadre de ses activités', 2, 1, 29);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (79, 'R5Q9P2', 'L''entreprise ne dispose pas de site internet et n’est pas intéressé par l’utilisation d’internet dans le cadre de ses activités', 1, 1, 29);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (80, 'R1Q10P2', 'L’entreprise utilise les réseaux sociaux tels que Facebook, Youtube, LinkedIn, etc. dans le cadre de ses activités pour commercialiser ses produits et services – ', 4, 1, 30);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (81, 'R2Q10P2', 'L’entreprise utilise les réseaux sociaux tels que Facebook, Youtube, LinkedIn, etc.  dans le cadre de ses activités uniquement pour se faire connaitre par ses clients et fournisseurs et faire sa promotion en ligne – dispose uniquement d’un site informationnel', 3, 1, 30);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (82, 'R3Q10P2', 'L’entreprise n’utilise pas  les réseaux sociaux tels que Facebook, Youtube, LinkedIn, etc.  dans le cadre de ses activités – L’entreprise souhaiterait les utiliser', 2, 1, 30);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (83, 'R4Q10P2', 'L''entreprise n’est pas intéressée à utiliser les réseaux sociaux tels que dans le cadre de ses activités', 1, 1, 30);

--POUR LE PARAMETRE 3

INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (31, 'Q1P3', 'Veuillez indiquer le nombre de droits de propriété intellectuelle (DPI) attribués à vos produits/services.', 1, 3);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (32, 'Q2P3', 'Veuillez indiquer vos activités d''amélioration des produits et des processus.', 1, 3);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (33, 'Q3P3', 'Poids des dépenses R&D dans les dépenses totales', 1, 3);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (34, 'Q4P3', 'Avez-vous été le premier à introduire sur le marché local ', 1, 3);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (35, 'Q5P3', 'Combien de produits et services avez-vous introduit au cours des 12 derniers mois', 1, 3);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (36, 'Q6P3', 'Quelle méthodologie votre entreprise adopte-t-elle pour recueillir et gérer les nouvelles idées ?', 1, 3);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (37, 'Q7P3', 'Existe-t-il un système de récompense pour ceux qui participent à l''innovation dans votre entreprise ?', 1, 3);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (38, 'Q8P3', 'La direction pense-t-elle que la formation de tous les employés est un élément clé de la stratégie d''amélioration des performances ?', 1, 3);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (39, 'Q9P3', 'Avez vous déjà reçu un prix relatif à votre innovation ?', 1, 3);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (40, 'Q10P3', 'L’entreprise dispose-elle des ressources pour mener les activités de Recherche et développement ?', 1, 3);

--REPONSES

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (84, 'R1Q1P3', 'Brevet', 5, 1, 31);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (85, 'R2Q1P3', 'Droits d''auteur', 4, 1, 31);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (86, 'R3Q1P3', 'Design industriel', 3, 1, 31);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (87, 'R4Q1P3', 'Marque déposée', 2, 1, 31);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (88, 'R5Q1P3', 'Aucun', 1, 1, 31);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (89, 'R1Q2P3', 'Acquisition de technologie ou de propriété intellectuelle', 5, 1, 32);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (90, 'R2Q2P3', 'Développement d''un nouveau produit (Au cours des 3 dernières années)', 4, 1, 32);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (91, 'R3Q2P3', 'Mise à niveau de produit(s)/service(s) existant(s) ou des processus au cours des 12 derniers mois', 3, 1, 32);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (92, 'R4Q2P3', 'Diversification des produits/services', 2, 1, 32);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (93, 'R5Q2P3', 'Aucune activité au cours des 12 derniers mois', 1, 1, 32);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (94, 'R1Q3P3', '0%', 5, 1, 33);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (95, 'R2Q3P3', '> 0% et < 5%', 4, 1, 33);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (96, 'R3Q3P3', '>= 5% et 10%', 3, 1, 33);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (97, 'R4Q3P3', '>= 10% et < 15%', 2, 1, 33);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (98, 'R1Q4P3', 'Un nouveau produit / service', 5, 1, 34);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (99, 'R2Q4P3', 'Une nouvelle technologie', 4, 1, 34);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (100, 'R3Q4P3', 'Un nouveau processus de production', 3, 1, 34);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (101, 'R4Q4P3', 'Rien', 2, 1, 34);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (102, 'R1Q5P3', 'Plus de 10', 5, 1, 35);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (103, 'R2Q5P3', 'Entre 5 et 10', 4, 1, 35);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (104, 'R3Q5P3', 'Entre 1 et 5', 3, 1, 35);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (105, 'R4Q5P3', 'Aucun', 2, 1, 35);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (106, 'R1Q6P3', 'Ateliers d''innovation', 5, 1, 36);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (107, 'R2Q6P3', 'Organisation interne avec une méthodologie et des pratiques structurées', 4, 1, 36);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (108, 'R3Q6P3', 'Référentiel d''idées, Blog', 3, 1, 36);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (109, 'R4Q6P3', 'Boîte à idées ouverte aux tiers', 2, 1, 36);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (110, 'R5Q6P3', 'Rien', 1, 1, 36);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (111, 'R1Q7P3', 'Associée à la rémunération / Partage de bénéfices', 4, 1, 37);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (112, 'R2Q7P3', 'Reconnaissance / Prix', 3, 1, 37);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (113, 'R3Q7P3', 'Incitations (par exemple, cadeaux, vacances)', 2, 1, 37);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (114, 'R4Q7P3', 'Rien', 1, 1, 37);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (115, 'R1Q8P3', 'Oui, nous avons déjà élaboré et exécuté un programme complet de formation ', 4, 1, 38);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (116, 'R2Q8P3', 'Oui, nous avons organisé des sessions de formation, mais non de manière structurée', 3, 1, 38);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (117, 'R3Q8P3', 'Oui, mais nous ne l''appliquons pas.', 2, 1, 38);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (118, 'R4Q8P3', 'Non, nous n''envisageons pas de formations pour le personnel', 1, 1, 38);


INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (119, 'R1Q9P3', 'Prix international', 5, 1, 39);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (120, 'R2Q9P3', 'Prix national', 4, 1, 39);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (121, 'R3Q9P3', 'Prix local', 3, 1, 39);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (122, 'R4Q9P3', 'Aucun', 2, 1, 39);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (123, 'R1Q10P3', 'L’entreprise a un budget pour la recherche & Développement', 5, 1, 40);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (124, 'R2Q10P3', 'L’entreprise mène des activités informelles de recherche et développement', 3, 1, 40);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (125, 'R3Q10P3', 'L’entreprise ne mène aucune activité de R-D et ne dispose d’aucun budget R-D', 1, 1, 40);

--POUR LE PARAMETRE 4 

INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (41, 'Q1P4', 'Quelle est la manière la plus préférée de votre entreprise pour rechercher des talents ?', 1, 4);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (42, 'Q2P4', 'Au point d''entrée, en général, quel est l''état de préparation de vos nouvelles recrues ?', 1, 4);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (43, 'Q3P4', 'Quel est votre taux de rotation du personnel sur une période de 3 ans ?', 1, 4);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (44, 'Q4P4', 'Votre entreprise a-t-elle un programme de rétention du personnel ? ', 1, 4);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (45, 'Q5P4', 'Votre entreprise a-t-elle un plan de développement de carrière pour le personnel ?', 1, 4);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (46, 'Q6P4', 'Poids des dépenses en formation dans les dépenses totales', 1, 4);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (47, 'Q7P4', 'Comment votre entreprise met-elle en œuvre la formation du personnel ?', 1, 4);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (48, 'Q8P4', 'Comment captez-vous et conservez-vous le capital de connaissances de votre personnel ? (maximum 3)', 1, 4);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (49, 'Q9P4', 'Quelles sont les pratiques de l''entreprise liées à l’organisation des RH, à l’environnement de travail et au droit social?', 1, 4);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (50, 'Q10P4', 'Quelles sont les autres pratiques de l''entreprise liées au recrutement des RH?', 1, 4);

-- REPONSES 

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (126, 'R1Q1P4', 'Références', 5, 1, 41);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (127, 'R2Q1P4', 'Agences de recrutement / Chasseur de têtes', 4, 1, 41);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (128, 'R3Q1P4', 'Portails d''emploi en ligne / annonce imprimée', 3, 1, 41);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (129, 'R4Q1P4', 'Collaboration (Partage des ressources)', 2, 1, 41);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (130, 'R5Q1P4', 'Salon des carrières (nouveaux diplômés)', 1, 1, 41);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (131, 'R1Q2P4', 'Prêt pour l''emploi / Expérience', 5, 1, 42);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (132, 'R2Q2P4', 'Nécessite une mise à niveau des compétences', 3, 1, 42);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (133, 'R3Q2P4', 'Recyclage total', 1, 1, 42);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (134, 'R1Q3P4', '0%', 5, 1, 43);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (135, 'R2Q3P4', '> 0% et < 5%', 4, 1, 43);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (136, 'R3Q3P4', '>= 5% et 10%', 3, 1, 43);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (137, 'R4Q3P4', '>= 10% et < 15%', 2, 1, 43);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (138, 'R5Q3P4', '>= 15%', 1, 1, 43);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (139, 'R1Q4P4', 'Participation aux bénéfices / Option d''équité', 5, 1, 44);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (140, 'R2Q4P4', 'Inventives du personnel (congés payés, etc.)', 4, 1, 44);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (141, 'R3Q4P4', 'Programmes de renforcement des capacités', 3, 1, 44);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (142, 'R4Q4P4', 'Horaires de travail flexibles', 2, 1, 44);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (143, 'R5Q4P4', 'Aucun', 1, 1, 44);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (144, 'R1Q5P4', 'Oui', 5, 1, 45);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (145, 'R2Q5P4', 'Non', 1, 1, 45);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (146, 'R1Q6P4', '0%', 5, 1, 46);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (147, 'R2Q6P4', '> 0% et < 5%', 4, 1, 46);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (148, 'R3Q6P4', '>= 5% et 10%', 3, 1, 46);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (149, 'R4Q6P4', '>= 10% et < 15%', 2, 1, 46);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (150, 'R5Q6P4', '>= 15%', 1, 1, 46);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (151, 'R1Q7P4', 'Basé sur l''évaluation des besoins de formation', 4, 1, 47);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (152, 'R2Q7P4', 'Sur la base du calendrier de formation prévu', 3, 1, 47);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (153, 'R3Q7P4', 'Sur demande de l''employé', 2, 1, 47);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (154, 'R4Q7P4', 'Ad hoc', 1, 1, 47);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (155, 'R1Q8P4', 'Système complet de gestion des connaissances (référentiel centralisé pour les modèles, formulaires, rapports, etc.)', 5, 1, 48);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (156, 'R2Q8P4', 'Base de données centralisée pour les experts de l''entreprise', 4, 1, 48);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (157, 'R3Q8P4', 'Séances de partage de connaissances', 3, 1, 48);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (158, 'R4Q8P4', 'Référentiel d''informations', 2, 1, 48);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (159, 'R5Q8P4', 'Manuel (documents imprimés)', 1, 1, 48);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (160, 'R1Q9P4', 'En cas d’accident (ou pour en éviter) de travail l’entreprise dispose d’une politique de santé et sécurité', 5, 1, 49);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (161, 'R2Q9P4', 'L’entreprise dispose des avantages pour les salariés qui vont au-delà des exigences règlementaires (exemple : prévoyance sociale, retraite mutuelle, congés de maternité, congés…)', 4, 1, 49);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (162, 'R3Q9P4', 'L’entreprise mène des enquêtes de satisfaction de tous ses salariés (enquêtes internes, entretien individuel...)', 3, 1, 49);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (163, 'R4Q9P4', 'L’entreprise possède et publie son organigramme', 2, 1, 49);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (164, 'R5Q9P4', 'L’entreprise ne dispose pas actuellement de toutes pratiques', 1, 1, 49);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (165, 'R1Q10P4', 'L’entreprise possède une politique de recrutement pour le nouveau personnel', 5, 1, 50);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (166, 'R2Q10P4', 'L’entreprise possède une description des tâches (missions) pour chaque employé', 3, 1, 50);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (167, 'R3Q10P4', 'L’entreprise possède un portefeuille de candidats en cas de licenciement ou de démission d’un employé', 1, 1, 50);

--PARAMETRE 5

INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (51, 'Q1P5', 'L''entreprise a-t-elle une vision claire et inspirante ?', 1, 5);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (52, 'Q2P5', 'L''entreprise a-t-elle une mission pour réaliser la vision ?', 1, 5);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (53, 'Q3P5', 'L''entreprise dispose-t-elle d''une équipe de direction bien structurée ?', 1, 5);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (54, 'Q4P5', 'L''entreprise dispose-t-elle d''une définition claire des missions de chaque responsable et d''une politique de délégation ?`', 1, 5);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (55, 'Q5P5', 'Quel est l''efficacité du style de management ?', 1, 5);

--REPONSES

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (168, 'R1Q1P5', 'Oui, elle est parfaitement compris dans toute l''organisation', 4, 1, 51);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (169, 'R2Q1P5', 'Oui, mais seules certaines personnes dans l''entreprise sont au courant', 3, 1, 51);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (170, 'R3Q1P5', 'Oui, elle vient d''être formulée', 2, 1, 51);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (171, 'R4Q1P5', 'Non, l''entreprise n''a pas de vision formelle', 1, 1, 51);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (172, 'R1Q2P5', 'Oui, il est parfaitement compris dans toute l''organisation', 4, 1, 52);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (173, 'R2Q2P5', 'Oui, mais seules certaines personnes dans l''entreprise sont au courant', 3, 1, 52);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (174, 'R3Q2P5', 'Oui, il vient d''être formulé', 2, 1, 52);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (175, 'R4Q2P5', 'Non, l''entreprise n''a pas de vision formelle', 1, 1, 52);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (176, 'R1Q3P5', 'Conseil d''administration, directeur général/PDG, directeurs et agents', 5, 1, 53);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (177, 'R2Q3P5', 'PDG, chefs d''équipe et agents', 4, 1, 53);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (178, 'R3Q3P5', 'Gestionnaire et agents', 3, 1, 53);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (179, 'R4Q2P5', 'Non, le propriétaire/dirigeant est le seul décideur', 2, 1, 53);


INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (180, 'R1Q4P5', 'Oui, les missions respectives du dirigeant et des cadres sont définies, connues et respectées.', 5, 1, 54);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (181, 'R2Q4P5', 'Non, les missions respectives du dirigeant et des cadres ne sont pas formalisées, mais aucune difficulté notée dans le fonctionnement', 4, 1, 54);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (182, 'R3Q4P5', 'Un système de délégation est mis en pratique de façon satisfaisante', 3, 1, 54);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (183, 'R4Q4P5', 'Les missions et les délégations  ont été définies, mais  ne sont pas respectées en général', 2, 1, 54);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (184, 'R5Q4P5', 'Les missions ne sont pas définies. Le dirigeant de la PME ne délègue pas.', 1, 1, 54);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (185, 'R1Q5P5', 'Le dirigeant a su impliquer et mobiliser ses collaborateurs directs ainsi qu’une large partie du personnel', 5, 1, 55);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (186, 'R2Q5P5', 'L’implication et la mobilisation des responsables ne sont pas généralisées, en dépit des efforts du dirigeant.', 3, 1, 55);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (187, 'R3Q5P5', 'Le moral est assez médiocre. On connaît mal les buts du dirigeant. On ne se sent ni impliqué, ni considéré.', 1, 1, 55);

--POUR LE PARAMETRE 6 

INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (56, 'Q1P6', 'L''entreprise dispose t-elle d''une organisation comptable interne efficace ?', 1, 6);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (57, 'Q2P6', 'Quel est le niveau de qualité associé aux états financiers produits par l''entreprise ?', 1, 6);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (58, 'Q3P6', 'L''entreprise dispose-t-elle d''une comptabilité analytique ou d''un contrôle de gestion?', 1, 6);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (59, 'Q4P6', 'Quelle est l''efficacité du dispositif de comptabilité analytique ?', 1, 6);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (60, 'Q5P6', 'L''entreprise dispose t-elle d’un plan ou budget de trésorerie ?', 1, 6);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (61, 'Q6P6', 'Des procédures d''encaissement et de règlement existent-elles dans l''entreprise ? Sont-elles rigoureusement respectées ?', 1, 6);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (62, 'Q7P6', 'Le mode de recouvrement de l''entreprise est-il efficace ?', 1, 6);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (63, 'Q8P6', 'L''entreprise utilise-t-elle des critères dans le choix de ces projets d’investissement ?', 1, 6);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (64, 'Q9P6', 'L’entreprise utilise-t-elle les logiciels de gestion ? (ajouter une ligne aucun ; 1)', 1, 6);

--REPONSES 

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (188, 'R1Q1P6', 'Oui, l''organisation comptable est efficace, chaque opération donne lieu à l’établissement d’une pièce comptable.', 4, 1, 56);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (189, 'R2Q1P6', 'Oui, il existe une organisation comptable, mais la compétence y est moyenne', 3, 1, 56);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (190, 'R3Q1P6', 'Il y a un comptable, mais il est à l’origine d’erreurs fréquentes.', 2, 1, 56);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (191, 'R4Q1P6', 'La comptabilité n''est pas tenue au quotidien', 1, 1, 56);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (192, 'R1Q2P6', 'Les états financiers sont certifiés', 4, 1, 57);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (193, 'R2Q2P6', 'Les états financiers ne sont pas certifiés mais sont établis par un expert comptable', 3, 1, 57);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (194, 'R3Q2P6', 'L''entreprise est accompagné par un comptable agréé', 2, 1, 57);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (195, 'R4Q2P6', 'Les états financiers sont produits par le service comptable interne de l''entreprise', 1, 1, 57);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (196, 'R1Q3P6', 'Oui, un dispositif existe pour l’ensemble des activités de l’entreprise (production, transport, commercial...).', 5, 1, 58);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (197, 'R2Q3P6', 'Oui, mais le dispositif n’existe que pour la production.', 4, 1, 58);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (198, 'R3Q3P6', 'Non, il n''y a pas de dispositif de comptabilité analytique', 3, 1, 58);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (199, 'R4Q3P6', 'Oui, un dispositif existe pour l’ensemble des activités de l’entreprise (production, transport, commercial...).', 2, 1, 58);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (200, 'R1Q4P6', 'Les éléments constitutifs du prix de revient sont rigoureusement cernés.', 5, 1, 59);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (201, 'R2Q4P6', 'Les informations sont communiquées aux responsables concernés.', 4, 1, 59);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (202, 'R3Q4P6', 'Les informations ne sont communiquées qu''au dirigeant de l''entreprise', 3, 1, 59);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (203, 'R4Q4P6', 'Les informations restent au niveau de la comptabilité, le dirigeant de la PME ne s’y intéresse pas.', 2, 1, 59);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (204, 'R5Q4P6', 'Les informations ne sont pas fiables', 1, 1, 59);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (205, 'R1Q5P6', 'Oui, il est établi chaque année avec les flux réels d’entrées et des sorties et est suivi et actualisé mensuellement.', 4, 1, 60);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (206, 'R2Q5P6', 'Oui, il est établi chaque année mais il n’est pas actualisé.', 3, 1, 60);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (207, 'R3Q5P6', 'Un plan ou le budget de trésorerie est établi chaque année, mais il est ignoré.', 2, 1, 60);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (208, 'R4Q5P6', 'Il n’y pas de plan ou de budget de trésorerie.', 1, 1, 60);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (209, 'R1Q6P6', 'Oui, elles s’effectuent selon des règles précises et soigneusement contrôlées.', 4, 1, 61);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (210, 'R2Q6P6', 'Oui, pour chaque opération un document numéroté est établi mais sans contrôle.', 3, 1, 61);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (211, 'R3Q6P6', 'Oui, un document est établi, mais sans numérotation.', 2, 1, 61);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (212, 'R4Q6P6', 'Non, les encaissements et les règlements ne donnent pas systématiquement lieu à l''établissement d''un document.', 1, 1, 61);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (213, 'R1Q7P6', 'Oui, le dispositif interne garantit 90% des règlements à 30 jours.', 5, 1, 62);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (214, 'R2Q7P6', 'Oui, le dispositif interne garantit 90% des règlements à 3 mois ou 80% à 60 jours.', 4, 1, 62);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (215, 'R3Q7P6', 'Malgré le dispositif interne, l’entreprise déplore 10% d’impayés par an.', 3, 1, 62);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (216, 'R4Q7P6', 'L’entreprise ne contrôle pas la solvabilité de ses clients et déplore plus de 30% d’impayés par an.', 2, 1, 62);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (217, 'R1Q8P6', 'Oui, l’entreprise utilise les critères de la valeur actuelle nette (VAN) dans le choix des projets d’investissement.', 5, 1, 63);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (218, 'R2Q8P6', 'Oui, l’entreprise utilise les critères de délai de récupération, taux de rentabilité comptable, etc.', 3, 1, 63);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (219, 'R3Q8P6', 'Non, l’entreprise n’utilise aucun critère. Elle choisit ses investissements de façon intuitive.', 1, 1, 63);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (220, 'R1Q9P6', 'L’entreprise utilise un Logiciel ERP ou Progiciel de Gestion intégrée  (SAP ou autre)', 5, 1, 64);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (221, 'R2Q9P6', 'L’entreprise utilise un Logiciel comptable (SAARI, SAGE, ACOMBA, etc.)', 3, 1, 64);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (222, 'R3Q9P6', 'L’entreprise utilise un Système Excel  pour la tenue de livres', 1, 1, 64);

--POUR LE PARAMETRE 7

INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (65, 'Q1P7', 'L''entreprise a-t-elle un dispositif de suivi de la qualité des approvisionnements?', 1, 7);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (66, 'Q2P7', 'Comment l''entreprise se procure ses équipements : ', 1, 7);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (67, 'Q3P7', 'L''entreprise maîtrise-elle sa production…?', 1, 7);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (68, 'Q4P7', 'Comment l''entreprise maîtrise des équipements…?', 1, 7);
INSERT INTO question(id, code, libelle, actif, id_parametre) VALUES (69, 'Q5P7', 'Sécurité et santé et hygiène au travail?', 1, 7);

--REPONSES 

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (223, 'R1Q1P7', 'Oui, elle contrôle la qualité des approvisionnements intégrant la production.', 4, 1, 65);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (224, 'R2Q1P7', 'Non, l''entreprise n''a pas de  suivi de la qualité des approvisionnements.', 1, 1, 65);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (225, 'R1Q2P7', 'L’entreprise achète ses principaux équipements neufs.', 4, 1, 66);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (226, 'R2Q2P7', 'L’entreprise achète ses principaux équipements neufs et usagés.', 3, 1, 66);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (227, 'R3Q2P7', 'L’entreprise achète ses principaux équipements usagés', 2, 1, 66);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (228, 'R1Q3P7', 'L’entreprise utilise toute sa capacité de production.', 5, 1, 67);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (229, 'R2Q3P7', 'L’entreprise effectue un contrôle en fin de production avant la mise sur le marché de ses produits.', 4, 1, 67);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (230, 'R3Q3P7', 'Les espaces de production sont nettoyés régulièrement pour permettre au personnel de circuler librement.', 3, 1, 67);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (231, 'R4Q3P7', 'L’entreprise assure un contrôle de ses coûts de production', 2, 1, 67);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (232, 'R1Q4P7', 'L’entreprise offre de la formation sur les tâches à exécuter.', 5, 1, 68);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (233, 'R2Q4P7', 'L’entreprise offre de la formation sur l’utilisation de nouvelles technologies.', 4, 1, 68);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (234, 'R3Q4P7', 'Les employés ont la formation nécessaire pour opérer efficacement les équipements de production.', 3, 1, 68);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (235, 'R4Q4P7', 'L’entreprise consulte son personnel lors de la prise de décision concernant la structure de production, les équipements, les technologies.', 2, 1, 68);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (236, 'R5Q4P7', 'L’entreprise offre de la formation sur la polyvalence et le développement de nouvelles compétences ', 1, 1, 68);

INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (237, 'R1Q5P7', 'L’entreprise offre de la formation sur la santé, sécurité et hygiène au travail.', 5, 1, 69);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (238, 'R2Q5P7', 'L’entreprise dispose d’un comité d’hygiène et sécurité.', 3, 1, 69);
INSERT INTO reponse_qualitative(id, code, libelle, score, actif, id_question) VALUES (239, 'R3Q5P7', 'L’entreprise ne dispose de rien en ce qui concerne la santé, sécurité et hygiène au travail', 1, 1, 69);

--RATIOS
INSERT INTO ratio(id, code, libelle, formule, ponderation, unite, actif) VALUES (1, 'R1', 'Ratio de liquidité (bceao)', '(actif circulant+trésorerie actif)/(passif circulant+trésorerie passif) % (env 100%)', 15, '%', 1);
INSERT INTO ratio(id, code, libelle, formule, ponderation, unite, actif) VALUES (2, 'R2', 'Ratio de rentabilité (bceao)', 'Résultat net/CA % (+- 5%)', 15, '%', 1);
INSERT INTO ratio(id, code, libelle, formule, ponderation, unite, actif) VALUES (3, 'R3', 'Capacité de remboursement (BCEAO)', 'Endettement structurel/ CAF', 15, 'années', 1);
INSERT INTO ratio(id, code, libelle, formule, ponderation, unite, actif) VALUES (4, 'R4', 'Autonomie financière (BCEAO)', 'Capitaux propres/Total ressources', 15, '%', 1);
INSERT INTO ratio(id, code, libelle, formule, ponderation, unite, actif) VALUES (5, 'R5', 'Délai client', 'Créances clients x 360/Chiffre d''affaires * 1,18', 10, 'jours', 1);
INSERT INTO ratio(id, code, libelle, formule, ponderation, unite, actif) VALUES (6, 'R6', 'Délai fournisseur', 'Dettes fournisseurs x 360/Achats*1,18', 10, 'jours', 1);
INSERT INTO ratio(id, code, libelle, formule, ponderation, unite, actif) VALUES (7, 'R7', 'Rentabilité d’exploitation', 'EBE / CA', 10, '%', 1);
INSERT INTO ratio(id, code, libelle, formule, ponderation, unite, actif) VALUES (8, 'R8', 'Poids des charges financières', 'Charges financières / EBE', 10, '%', 1);

--CALIBRAGE
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (1, 0, 0.30, 1, 1, 1);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (2, 0.30, 0.60, 2, 1, 1);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (3, 0.60, 1, 3, 1, 1);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (4, 1, 1.50, 4, 1, 1);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (5, 1.50, 0, 5, 1, 1);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (6, 0, -0.05, 1, 1, 2);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (7, -0.05, 0, 2, 1, 2);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (8, 0, 0.06, 3, 1, 2);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (9, 0.06, 0.10, 4, 1, 2);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (10, 0.10, 0, 5, 1, 2);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (11, 0, 1, 5, 1, 3);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (12, 1, 3, 4, 1, 3);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (13, 3, 5, 3, 1, 3);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (14, 5, 8, 2, 1, 3);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (15, 8, 0, 1, 1, 3);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (16, 0, 0.05, 1, 1, 4);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (17, 0.05, 0.15, 2, 1, 4);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (18, 0.15, 0.30, 3, 1, 4);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (19, 0.30, 0.50, 4, 1, 4);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (20, 0.50, 0, 5, 1, 4);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (21, 0, 6, 5, 1, 5);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (22, 6, 30, 4, 1, 5);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (23, 30, 60, 3, 1, 5);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (24, 60, 90, 2, 1, 5);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (25, 90, 0, 1, 1, 5);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (26, 0, 10, 5, 1, 6);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (27, 10, 45, 4, 1, 6);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (28, 45, 75, 3, 1, 6);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (29, 75, 90, 2, 1, 6);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (30, 90, 0, 1, 1, 6);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (31, 0, -0.05, 1, 1, 7);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (32, -0.05, 0.05, 2, 1, 7);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (33, 0.05, 0.15, 3, 1, 7);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (34, 0.15, 0.35, 4, 1, 7);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (35, 0.35, 0, 5, 1, 7);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (36, 0, 0, 1, 1, 8);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (37, 0, 2, 2, 1, 8);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (38, 2, 6, 3, 1, 8);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (39, 6, 10, 4, 1, 8);
INSERT INTO calibrage(id, min, max, classe, actif, id_ratio) VALUES (40, 10, 0, 5, 1, 8);

--PONDERATION
INSERT INTO ponderation (id, type_score, ponderation, actif, id_parametre) VALUES ( 1, 'Score financier/Solvabilité', '30', 1, null);
INSERT INTO ponderation (id, type_score, ponderation, actif, id_parametre) VALUES ( 2, 'Gestion des projets', '10', 1, 1);
INSERT INTO ponderation (id, type_score, ponderation, actif, id_parametre) VALUES ( 3, 'Marketing et gestion commerciale', '10', 1, 2);
INSERT INTO ponderation (id, type_score, ponderation, actif, id_parametre) VALUES ( 4, 'Innovation et qualité', '10', 1, 3);
INSERT INTO ponderation (id, type_score, ponderation, actif, id_parametre) VALUES ( 5, 'Gestion des ressources humaines', '10', 1, 4);
INSERT INTO ponderation (id, type_score, ponderation, actif, id_parametre) VALUES ( 6, 'Capacité Managériale', '10', 1, 5);
INSERT INTO ponderation (id, type_score, ponderation, actif, id_parametre) VALUES ( 7, 'Comptabilité et finance', '10', 1, 6);
INSERT INTO ponderation (id, type_score, ponderation, actif, id_parametre) VALUES ( 8, 'Production et capacité technique', '10', 1, 7);

--FORMES JURIDIQUES
INSERT INTO forme_juridique(id, code, libelle, actif) VALUES (1, '', 'Entreprise Individuelle', 1);
INSERT INTO forme_juridique(id, code, libelle, actif) VALUES (2, 'SURL', 'La Société Unipersonnelle à responsabilité limitée', 1);
INSERT INTO forme_juridique(id, code, libelle, actif) VALUES (3, 'SARL', 'La Société à Responsabilité Limitée', 1);
INSERT INTO forme_juridique(id, code, libelle, actif) VALUES (4, 'SA', 'La Société anonyme', 1);
INSERT INTO forme_juridique(id, code, libelle, actif) VALUES (5, 'SNC', 'La Société en Nom Collectif', 1);
INSERT INTO forme_juridique(id, code, libelle, actif) VALUES (6, 'SCS', 'La Société en Commandite Simple', 1);
INSERT INTO forme_juridique(id, code, libelle, actif) VALUES (7, 'GIE', 'Le Groupement d''Intérêt Economique', 1);

--SECTEURS ACTIVITE
INSERT INTO secteur_activite(id, libelle, actif) VALUES (1, 'Agroalimentaire', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (2, 'Informatique / Télécoms', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (3, 'Transports / Logistique', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (4, 'Communication / Multimédia', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (5, 'Banque / Finance', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (6, 'Assurance', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (7, 'BTP / Matériaux de construction', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (8, 'Électronique / Électricité', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (9, 'Industrie pharmaceutique', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (10, 'Textile / Habillement / Chaussure', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (11, 'Elevage', 1);
INSERT INTO secteur_activite(id, libelle, actif) VALUES (12, 'Machines et équipements / Automobile', 1);

DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE SEQUENCE hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 25
  CACHE 1;
GRANT ALL PRIVILEGES ON SEQUENCE hibernate_sequence TO postgres;