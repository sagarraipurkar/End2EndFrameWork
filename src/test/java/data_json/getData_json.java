package data_json;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.helpers.Util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getData_json {

	// convert the file into string
	public List<HashMap<String, String>> getdata_Reader(String filePath) throws IOException {
		String jsoncontent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "src\\test\\java\\data_json\\purchaseorder_json_credentials"),
				StandardCharsets.UTF_8);

//		Convert the String into HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

}
