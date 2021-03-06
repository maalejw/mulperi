package eu.openreq.mulperi.models.mulson;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import eu.openreq.mulperi.models.kumbang.SubFeature;
import eu.openreq.mulperi.models.mulson.Attribute;

@Entity
public class Requirement extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -8873722269641439557L;
	
	private String requirementId;
	private String name;
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Relationship> relationships;
	@OneToMany(cascade = {CascadeType.ALL})
	private List<Attribute> attributes;
	@OneToMany(cascade = {CascadeType.ALL})
	private List<SubFeature> subfeatures;
	
	public Requirement() {
		this.attributes = new ArrayList<>();
		this.subfeatures = new ArrayList<>();
		this.relationships = new ArrayList<>();
	}
	
	public String getRequirementId() {
		return requirementId;
	}
	public void setRequirementId(String requirementId) {
		if (requirementId!=null)
			requirementId = requirementId.replaceAll(" ", "_").replaceAll("-", "_");
		this.requirementId = requirementId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (name!=null)
			name = name.replaceAll(" ", "_").replaceAll("-", "_");
		this.name = name;
	}
	public List<Relationship> getRelationships() {
		return relationships;
	}
	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}		
	public List<Attribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	public List<SubFeature> getSubfeatures() {
		return subfeatures;
	}
	public void setSubfeatures(List<SubFeature> subfeatures) {
		this.subfeatures = subfeatures;
	}

	public boolean hasRelationshipType(String type) {
		if(relationships == null) {
			return false;
		}
		
		for(Relationship rel : relationships) {
			if(rel.getType().equals(type)) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getParent() {
		if(relationships == null) {
			return null;
		}
		
		for(Relationship rel : relationships) {
			if(rel.getType().equals("isa")
					|| rel.getType().equals("isoptionalpartof")
					|| rel.getType().equals("ispartof")) {
				
				return rel.getTargetId();
			}
		}
		
		return null;
	}
	
	public List<String> getRelationshipsOfType(String type) {
		List<String> requires = new ArrayList<>();
		if(relationships == null) {
			return requires;
		}
		for(Relationship rel : relationships) {
			if(rel.getType().equals(type)) {
				requires.add(rel.getTargetId());
			}
		}
		return requires;
	}
	
	/**
	 * Cardinality in Kumbang form for Parsed Model
	 * @return String
	 */
	public String getCardinality() {
		if(hasRelationshipType("isoptionalpartof")) {
			return "0-1";
		}
		return "0-1"; //Defaults to optional 
	}

	/**
	 * Automatically generated with Eclipse, used in ModelController's generateName method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((relationships == null) ? 0 : relationships.hashCode());
		result = prime * result + ((requirementId == null) ? 0 : requirementId.hashCode());
		result = prime * result + ((subfeatures == null) ? 0 : subfeatures.hashCode());
		return result;
	}

	/**
	 * Automatically generated with Eclipse
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requirement other = (Requirement) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (relationships == null) {
			if (other.relationships != null)
				return false;
		} else if (!relationships.equals(other.relationships))
			return false;
		if (requirementId == null) {
			if (other.requirementId != null)
				return false;
		} else if (!requirementId.equals(other.requirementId))
			return false;
		if (subfeatures == null) {
			if (other.subfeatures != null)
				return false;
		} else if (!subfeatures.equals(other.subfeatures))
			return false;
		return true;
	}
	
}
