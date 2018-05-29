package be.vdab.personeel.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "werknemers")
@NamedEntityGraph(name = Werknemer.MET_ONDERGESCHIKTEN_EN_CHEF, attributeNodes = {@NamedAttributeNode("ondergeschikten"), @NamedAttributeNode("chef")})
public class Werknemer implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String MET_ONDERGESCHIKTEN_EN_CHEF = "Werknemer.metOndergeschiktenEnChef";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String familienaam;
	private String voornaam;
	private String email;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "jobtitelid")
	private Jobtitel jobtitel;
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal salaris;
	private String paswoord;
	@DateTimeFormat(style = "S-")
	private LocalDate geboorte;
	@Column(unique = true)
	private long rijksregisternr;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "chefid")
	private Werknemer chef;
	@OneToMany(mappedBy = "chef")
	private Set<Werknemer> ondergeschikten;
	private long versie;
	
	public long getId() {
		return id;
	}
	
	public String getFamilienaam() {
		return familienaam;
	}
	
	public String getVoornaam() {
		return voornaam;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Jobtitel getJobtitel() {
		return jobtitel;
	}
	
	public BigDecimal getSalaris() {
		return salaris;
	}
	
	public String getPaswoord() {
		return paswoord;
	}
	
	public LocalDate getGeboorte() {
		return geboorte;
	}
	
	public long getRijksregisternr() {
		return rijksregisternr;
	}
	
	public Werknemer getChef() {
		return chef;
	}
	
	public Set<Werknemer> getOndergeschikten() {
		return Collections.unmodifiableSet(ondergeschikten);
	}
	
	public long getVersie() {
		return versie;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (rijksregisternr ^ (rijksregisternr >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Werknemer))
			return false;
		Werknemer other = (Werknemer) obj;
		if (rijksregisternr != other.rijksregisternr)
			return false;
		return true;
	}
	
	public void opslag(BigDecimal bedrag) {
		if (bedrag.compareTo(BigDecimal.ONE) <= 0) {
			throw new IllegalArgumentException();
		}
		salaris = salaris.add(bedrag);
	}
}
