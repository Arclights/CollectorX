package com.arclight.collectorx.moviecontainers;

public class Role {

	public String roleName;
	public Person actor;

	public Role(Person actor, String roleName) {
		this.roleName = roleName;
		this.actor = actor;
	}

	public Role() {

	}

}
