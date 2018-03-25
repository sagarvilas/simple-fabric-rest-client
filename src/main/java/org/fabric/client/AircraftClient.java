package org.fabric.client;

import java.io.IOException;
import java.util.List;

import org.fabric.domain.Aircraft;
import org.fabric.domain.ServiceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AircraftClient {

	private static final String SERVICE_URL = "http://localhost:3000/api/org.example.airline.aircraft.Aircraft";

	private static final Logger log = LoggerFactory.getLogger(AircraftClient.class);

	@Autowired
	RestTemplate restTemplate;

	public List<Aircraft> getAllAircraft() {
		ResponseEntity<List<Aircraft>> responseEntity = restTemplate.exchange(SERVICE_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Aircraft>>() {
				});
		List<Aircraft> response = responseEntity.getBody();
		for (Aircraft aircraft : response) {
			log.debug(aircraft.toString());
		}
		return response;
	}

	public Aircraft getAircragtById(String id) {
		Aircraft response = restTemplate.getForObject(SERVICE_URL + "/" + id, Aircraft.class);
		log.debug(response.toString());
		return response;
	}

	public ResponseEntity<String> addNewAircraft(Aircraft aircraft) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String request = mapper.writeValueAsString(aircraft);
			System.out.println(request);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(request, headers);
			return restTemplate.postForEntity(SERVICE_URL, entity, String.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (HttpStatusCodeException exception) {
			try {
				ServiceError error = mapper.readValue(exception.getResponseBodyAsString(), ServiceError.class);

				log.debug("error---->>>" + error.getError().getMessage());
				throw new RestClientException(
						"[" + error.getError().getStatusCode() + "] " + error.getError().getMessage());
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void deleteAircraft(String id) {
		restTemplate.delete(SERVICE_URL + "/" + id);
	}

	public void updateAircraft(Aircraft aircraft) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String request = mapper.writeValueAsString(aircraft);
			System.out.println(request);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<String> entity = new HttpEntity<String>(request, headers);
			restTemplate.put(SERVICE_URL + "/" + aircraft.getAircraftId(), entity, String.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (HttpStatusCodeException exception) {
			try {
				ServiceError error = mapper.readValue(exception.getResponseBodyAsString(), ServiceError.class);

				log.debug("error---->>>" + error.getError().getMessage());
				throw new RestClientException(
						"[" + error.getError().getStatusCode() + "] " + error.getError().getMessage());
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
