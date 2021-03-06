package com.scoring.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "indicateur")
public class Indicateur {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scoring_generator")
    @SequenceGenerator(name = "scoring_generator", sequenceName = "indicateur_seq", allocationSize = 1000)
	private Long id;
	private double bkActifCirculant;
	private double btTresorerieActif;
	private double dpPassifCirculant;
	private double dtTresoreriePassif;
	private double xiResultatNet;
	private double xbChiffresDaffaires;
	private double biCreanceClient;
	private double caf;
	private double caCapitauxPropres;
	private double dfTotalResources;
	private double djDettesFournisseurs;
	private double raAchats;
	private int annee;
	@Column(columnDefinition = "INT DEFAULT 0")
	private int derniereAnnee;
	private boolean actif;
	@Column(name = "xd_excedent_brut", nullable=true)
	private double xdExcedentBrutExploit;
	@Column(name = "rm_charges_financieres", nullable=true)
	private double rmChargesFinancieres;
	@Column(name = "da_emprunts_dettes", nullable=true)
	private double daEmpruntsDettes;
	@Column(name = "db_dettes_acquisitions", nullable=true)
	private double dbDettesAcquisitions;
	@Column(name = "tk_revenus_financiers", nullable=true)
	private double tkRevenusFinanciers;
	@Column(name = "tl_reprises_depreciations", nullable=true)
	private double tlReprisesDepreciations;
	@Column(name = "tm_transfert_charges", nullable=true)
	private double tmTransfertCharges;
	@Column(name = "rq_participations", nullable=true)
	private double rqParticipations;
	@Column(name = "rs_impot", nullable=true)
	private double rsImpot;
	@Column(name = "ef022_endettement_struct", nullable=true)
	private double endettement_struct;
	@Column(name = "aa071_produit_finan", nullable=true)
	private double produit_financier;

	@ManyToOne
	@JoinColumn(name = "id_demande")
	private DemandeScoring  demandeScoring;
		
}
