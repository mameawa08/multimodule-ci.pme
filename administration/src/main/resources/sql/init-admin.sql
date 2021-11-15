--PROFILS
insert into profils(profil_id, actif, code, libelle) VALUES (0, 1, 'ROLE_ADMIN', 'Administrateur Technique');
insert into profils(profil_id, actif, code, libelle) VALUES (1, 1, 'ROLE_ADMIN_FONC', 'Administrateur Fonctionnel');
insert into profils(profil_id, actif, code, libelle) VALUES (2, 1, 'ROLE_ENTR', 'Entrepreneur');
insert into profils(profil_id, actif, code, libelle) VALUES (3, 1, 'ROLE_EXP_FIN', 'Expert Financier');
insert into profils(profil_id, actif, code, libelle) VALUES (4, 1, 'ROLE_EXP_PME', 'Expert PME');

--HABILITATIONS
INSERT INTO habilitations(id, code, libelle) VALUES (1, 'VOIR_PME', 'Voir liste des PMEs');

--UTILISATEURS
insert into users(user_id, actif, email, identifiant, mot_de_passe, nom, prenom, profil_id, confirme, mdpamodifier)
values (1, 1, 'admin@um.com', 'admin', '$2a$12$fEw1QzwhsSWmp6J9jzGwmu65OcCl6QAwDZnTo8PmeWt1zsyXnqSB.', 'admin', 'admin', 0, 0, 0);