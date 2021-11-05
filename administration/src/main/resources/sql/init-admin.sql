insert into profils(profil_id, actif, code, libelle) VALUES (0, 1, 'ROLE_ADMIN', 'Role Administrateur');

insert into users(user_id, actif, email, identifiant, mot_de_passe, nom, prenom, profil_id, confirme, mdpamodifier)
values (1, 1, 'admin@um.com', 'admin', '$2a$12$fEw1QzwhsSWmp6J9jzGwmu65OcCl6QAwDZnTo8PmeWt1zsyXnqSB.', 'admin', 'admin', 0, 0, 0);