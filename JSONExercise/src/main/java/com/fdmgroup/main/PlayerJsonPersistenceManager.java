package com.fdmgroup.main;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fdmgroup.serialization.game.HealthPack;

public class PlayerJsonPersistenceManager extends StdSerializer<Wizard> {
	
	protected PlayerJsonPersistenceManager(Class<Wizard> t) {
		super(t);
		// TODO Auto-generated constructor stub
	}


	private ObjectMapper mapper = new ObjectMapper();
	private File file = new File("Wizard.json");
	private JsonFactory factory = new JsonFactory();
//	private JsonGenerator jgen; 
	
	public void serialize(Wizard wiz, JsonGenerator jgen, SerializerProvider provider) throws IOException {
			
			jgen = factory.createGenerator(file, JsonEncoding.UTF8);
			jgen.setCodec(mapper);
			jgen.writeStartObject();
			jgen.writeStringField("Name", wiz.getName());
			jgen.writeNumberField("HP", wiz.getHealthPoints());
			jgen.writeFieldName("Shield");
			jgen.writeObject(wiz.getShield());
			jgen.writeFieldName("Power");
			jgen.writeObject(wiz.getPower());
			jgen.writeFieldName("Backpack");
			jgen.writeStartArray();
			int i = 0;
		    while (wiz.getBackpack().getNumPacks() > i)
		    {
		        jgen.writeObject(wiz.getBackpack().useHealthPack());
		    }
//			jgen.writeObject(wiz.getBackpack().useHealthPack());
			jgen.writeEndArray();
			
			jgen.close();
		
//			wiz.getBackpack().addHealthPack(new HealthPack(wiz.getBackpack().useHealthPack().getHealthPoints()));
			
	}
	
	
	public Wizard deserialize(String fileName) {
		try {
			File file = new File(fileName);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Wizard newWiz = mapper.readValue(file, Wizard.class);
			return newWiz;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
