package com.nemanjam.ebook.lucene;

import java.util.ArrayList;

public class ObjectHelper {
	
	@SuppressWarnings("rawtypes")
	public static Boolean hasElements(Object o) {
		if(o == null) {
			return false;
		} else {		
			if(o instanceof ArrayList) {
				return ((ArrayList)o).size() > 0;
			} else if(o instanceof String){
				return (o.toString().trim()).length() > 0;
			} else {
				return true;
			}
		}
	}
	
}