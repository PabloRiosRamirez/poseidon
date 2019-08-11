package online.grisk.poseidon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ScoreController {

	@PostMapping(value = "/api/poseidon/score")
	public ResponseEntity<?> getScore(@RequestBody JsonNode payload) throws IOException {
		JsonNode values = payload.get("dataintegration").get("values");
		JsonNode ratiosConfiguration = payload.get("riskScore").get("configuration");
		JsonNode ratiosCollections = ratiosConfiguration.get("scoreRangeCollection");
		String nombreVariable = ratiosConfiguration.get("variable").asText();
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> jsonMap = mapper.convertValue(payload, Map.class);
		
		Map<String, Object> nuevosDatos = new HashMap<String,Object>();
		
		
		List<JsonNode> listaValues = new ArrayList<JsonNode>();
		if (values.isArray()) {
			for (int i = 0; i < values.size(); i++) {
				listaValues.add(values.get(i));
			}
		}
		
		List<JsonNode> res = listaValues.stream().filter(a -> a.get("code").asText().equalsIgnoreCase(nombreVariable)).collect(Collectors.toList());
		
		if(!res.isEmpty()) {
			if(res.get(0).get("type").asText().equalsIgnoreCase("ND")) {
				nuevosDatos.put("score", Double.parseDouble(res.get(0).get("value").asText()));
			} else {
				nuevosDatos.put("score", Integer.parseInt(res.get(0).get("value").asText()));
			}
		} else {
			nuevosDatos.put("score", -1);
			//return 500 variable no encontrada
		}
		
		nuevosDatos.put("variable", nombreVariable);
		nuevosDatos.put("ranges", ratiosCollections);
		
		((Map)jsonMap.get("riskScore")).put("values", nuevosDatos);
		return new ResponseEntity<Map<String, Object>>(jsonMap, HttpStatus.OK);
	}
}
