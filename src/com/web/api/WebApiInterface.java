package com.web.api;

import java.util.List;

public interface WebApiInterface {
	public String getSongUrlByName(String songname);
	public void importUrl(List result);
}
