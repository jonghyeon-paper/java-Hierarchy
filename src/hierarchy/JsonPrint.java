package hierarchy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonPrint {
	
	protected final static ObjectMapper mapper = new ObjectMapper();
	public static void print(Object value){
		try {
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
