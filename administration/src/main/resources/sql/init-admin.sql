--PROFILS
insert into profils(profil_id, actif, code, libelle) VALUES (0, 1, 'ROLE_ADMIN', 'Administrateur Technique');
insert into profils(profil_id, actif, code, libelle) VALUES (1, 1, 'ROLE_ADMIN_FONC', 'Administrateur Fonctionnel');
insert into profils(profil_id, actif, code, libelle) VALUES (2, 1, 'ROLE_ENTR', 'Entrepreneur');
insert into profils(profil_id, actif, code, libelle) VALUES (3, 1, 'ROLE_EXP_FIN', 'Expert Financier');
insert into profils(profil_id, actif, code, libelle) VALUES (4, 1, 'ROLE_EXP_PME', 'Expert PME');

--HABILITATIONS
INSERT INTO habilitations(id, code, libelle) VALUES (1, 'VOIR_PARAM', 'Voir liste des Paramètres');
INSERT INTO habilitations(id, code, libelle) VALUES (2, 'CREER_PARAM', 'Créer un paramètre');
INSERT INTO habilitations(id, code, libelle) VALUES (3, 'SUP_PARAM', 'Supprimer un Paramètre');
INSERT INTO habilitations(id, code, libelle) VALUES (4, 'VOIR_QUEST', 'Voir liste des questions');
INSERT INTO habilitations(id, code, libelle) VALUES (5, 'CREER_QUEST', 'Créer une question');
INSERT INTO habilitations(id, code, libelle) VALUES (6, 'SUP_QUEST', 'Supprimer une question');
INSERT INTO habilitations(id, code, libelle) VALUES (7, 'VOIR_REP', 'Voir liste des reponses');
INSERT INTO habilitations(id, code, libelle) VALUES (8, 'CREER_REP', 'Créer une reponse');
INSERT INTO habilitations(id, code, libelle) VALUES (9, 'SUP_REP', 'Supprimer une reponse');
INSERT INTO habilitations(id, code, libelle) VALUES (10, 'VOIR_RATIO', 'Voir liste des ratios');
INSERT INTO habilitations(id, code, libelle) VALUES (11, 'VOIR_CALIBR', 'Voir liste des calibrages');
INSERT INTO habilitations(id, code, libelle) VALUES (12, 'CREER_CALIBR', 'Calibrer');
INSERT INTO habilitations(id, code, libelle) VALUES (13, 'SUP_CALIBR', 'Supprimer un calibrage');
INSERT INTO habilitations(id, code, libelle) VALUES (14, 'VOIR_POND', 'Voir liste des pondérations de score');
INSERT INTO habilitations(id, code, libelle) VALUES (15, 'MODIF_POND', 'Modifier une pondération');
INSERT INTO habilitations(id, code, libelle) VALUES (16, 'IDENTIF_PME', 'Saisir infos PME');
INSERT INTO habilitations(id, code, libelle) VALUES (17, 'REP_QUEST_ELIG', 'Répondre au questionnaire d''eligibilité');
INSERT INTO habilitations(id, code, libelle) VALUES (18, 'SAISIR_IND', 'Saisir les indicateurs financiers');
INSERT INTO habilitations(id, code, libelle) VALUES (19, 'CALCULER_SF', 'Calculer le score final');
INSERT INTO habilitations(id, code, libelle) VALUES (20, 'EVALUER_PME', 'Evaluer PME via questionnaire qualitatif');
INSERT INTO habilitations(id, code, libelle) VALUES (21, 'GEN_RAPPORT', 'Générer rapport évaluation');


--HABILITATION PAR PROFIL
--ADMIN FONCTIONNEL
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 1);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 2);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 3);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 4);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 5);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 6);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 7);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 8);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 9);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 10);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 11);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 12);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 13);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 14);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (1, 15);
--ENTREPRENEUR
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (2, 16);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (2, 17);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (2, 18);
--EXPERT FINANCIER
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (3, 18);
--EXPERT PME
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (4, 18);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (4, 19);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (4, 20);
INSERT INTO habilitation_par_profil(role_id, habilitation_id) VALUES (4, 21);


--UTILISATEURS
insert into users(user_id, actif, email, username, mot_de_passe, nom, prenom, profil_id, confirme, mdpamodifier)
values (1, 1, 'admin@um.com', 'admin', '$2a$12$fEw1QzwhsSWmp6J9jzGwmu65OcCl6QAwDZnTo8PmeWt1zsyXnqSB.', 'admin', 'admin', 0, 1, 0);