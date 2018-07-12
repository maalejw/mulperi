package eu.openreq.mulperi.models.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
* Dependency
* The dependency between requirements
* 
*/
public class Dependency {

	/**
	* The type of dependency between requirements
	* (Required)
	* 
	*/
	@SerializedName("dependency_type")
	@Expose
	private Dependency_type dependency_type;
	/**
	* NLP engine's estimation of the reliability of a proposed dependency
	* 
	*/
	@SerializedName("dependency_score")
	@Expose
	private float dependency_score;
	/**
	* Status of dependency that has been detected by NLP and whether or not the dependency has been approved
	* 
	*/
	@SerializedName("status")
	@Expose
	private Dependency_status status;
	/**
	* The requirement having a dependency
	* (Required)
	* 
	*/
	@SerializedName("fromId")
	@Expose
	private Requirement fromId;
	/**
	* The requirement dependent on another
	* (Required)
	* 
	*/
	@SerializedName("toId")
	@Expose
	private Requirement toId;
	
	/**
	* Creation timestamp
	* (Required)
	* 
	*/
	@SerializedName("created_at")
	@Expose
	private int created_at;

	public Dependency_type getDependency_type() {
		return dependency_type;
	}
	
	public void setDependency_type(Dependency_type dependency_type) {
		this.dependency_type = dependency_type;
	}
	
	public float getDependency_score() {
		return dependency_score;
	}
	
	public void setDependency_score(float dependency_score) {
		this.dependency_score = dependency_score;
	}
	
	public Dependency_status getStatus() {
		return status;
	}
	
	public void setStatus(Dependency_status status) {
		this.status = status;
	}
	
	public Requirement getFromId() {
		return fromId;
	}
	
	public void setFromId(Requirement fromId) {
		this.fromId = fromId;
	}
	
	public Requirement getToId() {
		return toId;
	}
	
	public void setToId(Requirement toId) {
		this.toId = toId;
	}
	
	public int getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
}