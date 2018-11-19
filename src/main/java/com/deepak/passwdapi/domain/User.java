package com.deepak.passwdapi.domain;


import java.io.Serializable;
import java.util.Objects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * A user.
 */

public class User  {

   
    private String name;
    private long uid;
    private long gid;
    private String comment;
    private String home;
    private String shell;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public String getShell() {
		return shell;
	}
	public void setShell(String shell) {
		this.shell = shell;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + (int) (gid ^ (gid >>> 32));
		result = prime * result + ((home == null) ? 0 : home.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((shell == null) ? 0 : shell.hashCode());
		result = prime * result + (int) (uid ^ (uid >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User user = (User) obj;
		
		return !(user.getName() == null || getName() == null) && Objects.equals(getName(), user.getName());
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", uid=" + uid + ", gid=" + gid + ", comment=" + comment + ", home=" + home
				+ ", shell=" + shell + "]";
	}

   
}
