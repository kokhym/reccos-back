package com.reccos.admin.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "matches")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer idd_match;

	@Column(name = "match_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate match_date;

	@ManyToOne
	@JsonIgnoreProperties("contract")
	private Team home;

	@ManyToOne
	@JsonIgnoreProperties("contract")
	private Team visiting;

	@ManyToOne
	private Refree head_referee;

	@ManyToOne
	private Refree assistant_referee;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL, CascadeType.MERGE }, mappedBy = "matches")
	@JsonIgnore
	private Set<Round> rounds = new HashSet<>();

	public Match() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Match(Long id, Integer idd_match, LocalDate match_date, Team home, Team visiting, Refree head_referee,
			Refree assistant_referee, Set<Round> rounds) {
		super();
		this.id = id;
		this.idd_match = idd_match;
		this.match_date = match_date;
		this.home = home;
		this.visiting = visiting;
		this.head_referee = head_referee;
		this.assistant_referee = assistant_referee;
		this.rounds = rounds;
	}

	public Integer getIdd_match() {
		return idd_match;
	}

	public void setIdd_match(Integer idd_match) {
		this.idd_match = idd_match;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getMatch_date() {
		return match_date;
	}

	public void setMatch_date(LocalDate match_date) {
		this.match_date = match_date;
	}

	public Team getHome() {
		return home;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public Team getVisiting() {
		return visiting;
	}

	public void setVisiting(Team visiting) {
		this.visiting = visiting;
	}

	public Set<Round> getRounds() {
		return rounds;
	}

	public void setRounds(Set<Round> rounds) {
		this.rounds = rounds;
	}

	public Refree getHead_referee() {
		return head_referee;
	}

	public void setHead_referee(Refree head_referee) {
		this.head_referee = head_referee;
	}

	public Refree getAssistant_referee() {
		return assistant_referee;
	}

	public void setAssistant_referee(Refree assistant_referee) {
		this.assistant_referee = assistant_referee;
	}

}
