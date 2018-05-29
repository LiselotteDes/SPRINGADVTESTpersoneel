package be.vdab.personeel.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jobtitels")
@NamedEntityGraph(name = Jobtitel.MET_WERKNEMERS, attributeNodes = @NamedAttributeNode("werknemers"))
public class Jobtitel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String MET_WERKNEMERS = "Jobtitel.metWerknemers";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@OneToMany(mappedBy = "jobtitel")
	private Set<Werknemer> werknemers;
	private long versie;
	
	public long getId() {
		return id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public Set<Werknemer> getWerknemers() {
		return Collections.unmodifiableSet(werknemers);
	}
	
	public long getVersie() {
		return versie;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.toUpperCase().hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Jobtitel))
			return false;
		Jobtitel other = (Jobtitel) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equalsIgnoreCase(other.naam))
			return false;
		return true;
	}
}
