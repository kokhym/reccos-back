package com.reccos.admin.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "groups")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name_group")
	private String name_group;

	@OneToMany
	@JoinColumn(name = "groups_id")
	private List<Round> rounds;

	@OneToMany(mappedBy = "group")
//	@JsonIgnoreProperties({ "teams", "rounds" })
	private List<Statistics> statistics;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "groups")
	@JsonIgnore
	private Set<League> league = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinTable(name = "group_teams", joinColumns = { @JoinColumn(name = "group_id") }, inverseJoinColumns = {
			@JoinColumn(name = "team_id") })
	private Set<Team> teams = new HashSet<>();

	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Group(long id, String name_group, List<Round> rounds, List<Statistics> statistics, Set<League> league,
			Set<Team> teams) {
		super();
		this.id = id;
		this.name_group = name_group;
		this.rounds = rounds;
		this.statistics = statistics;
		this.league = league;
		this.teams = teams;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName_group() {
		return name_group;
	}

	public void setName_group(String name_group) {
		this.name_group = name_group;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public List<Statistics> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<Statistics> statistics) {
		this.statistics = statistics;
	}

	public Set<League> getLeague() {
		return league;
	}

	public void setLeague(Set<League> league) {
		this.league = league;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

}
