package com.fdmgroup.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fdmgroup.serialization.game.Backpack;
import com.fdmgroup.serialization.game.HealthPack;

public class WizardDeserializer extends StdDeserializer<Wizard> {

	protected WizardDeserializer(Class<?> vc) {
		super(vc);
	}

	private File file = new File("Wizard.json");
	
	@Override
	public Wizard deserialize(JsonParser jp, DeserializationContext deserializer)
			throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		
		JsonNode node = mapper.readTree(fileReader);
		
		String name = node.get("Name").asText();
		int hp = (Integer) node.get("HP").numberValue();
		Shield shield = mapper.treeToValue(node.get("Shield"), Shield.class);
		Powers power = mapper.treeToValue(node.get("Power"), Powers.class);
		HealthPack[] pack = mapper.treeToValue(node.get("Backpack"), HealthPack[].class);
		
		Backpack backpack = new Backpack();
		for(HealthPack p : pack) {
			backpack.addHealthPack(p);
		}
		
		return new Wizard(name, hp, shield, power, backpack);
		
	}

}
