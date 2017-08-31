package pages;

import java.util.LinkedHashMap;

public interface Page {
	
	public void processStep(LinkedHashMap<String,String> steps);

}
