package eu.openreq.mulperi.models.kumbang;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Constraint extends AbstractPersistable<Long> {
	
	private static final long serialVersionUID = -4884066864162001583L;
	
	private String ifPresent;
	private String thenRequired;
	private boolean negated;
	
	public Constraint() {
		
	}
	
	public Constraint(String name1, String name2) {
		ifPresent = name1;
		thenRequired = name2;
		negated = false;
	}
	
	public Constraint(String ifPresent, String thenRequired, boolean negated) {
		this.ifPresent = ifPresent;
		this.thenRequired = thenRequired;
		this.negated = negated;
	}

	public String getIfPresent() {
		return ifPresent;
	}

	public void setIfPresent(String ifPresent) {
		if (ifPresent!=null)
			ifPresent = ifPresent.replaceAll(" ", "_").replaceAll("-", "_");
		this.ifPresent = ifPresent;
	}

	public String getThenRequired() {
		return thenRequired;
	}

	public void setThenRequired(String thenRequired) {
		if (thenRequired!=null)
			thenRequired = thenRequired.replaceAll(" ", "_").replaceAll("-", "_");
		this.thenRequired = thenRequired;
	}
	
	@Override
	public String toString() {
		String negation = "";
		if(this.negated) {
			negation = "not ";
		}
		return "present(" + ifPresent + ") => " + negation + "present(" + thenRequired + ")";
	}
}
