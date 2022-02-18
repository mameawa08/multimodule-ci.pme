ALTER TABLE calibrage ALTER COLUMN min TYPE numeric(20,3);

ALTER TABLE calibrage ALTER COLUMN max TYPE numeric(20,3);

ALTER TABLE valeur_ratio
ALTER COLUMN valeur TYPE numeric(20,3);

-- ALTER TABLE question ALTER COLUMN libelle TYPE character varying(700);

-- ALTER TABLE reponse_qualitative ALTER COLUMN libelle TYPE character varying(700);

alter table dirigeant alter column numeroci type  varchar(50);