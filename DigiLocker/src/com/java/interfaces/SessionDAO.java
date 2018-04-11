package com.java.interfaces;

public interface SessionDAO {

	public boolean createActiveSession(String session_id, String username);

	public boolean deleteActiveSession(String session_id);
}
