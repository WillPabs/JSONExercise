package com.fdmgroup.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fdmgroup.serialization.game.HealthPack;

public class WizardSerializer extends StdSerializer<Wizard>{

	private static final long serialVersionUID = -3707933813669232689L;

	protected WizardSerializer(Class<Wizard> t) {
		super(t);
	}

	
	private ObjectMapper mapper = new ObjectMapper();
	private File file = new File("Wizard.json");
	private JsonFactory factory = new JsonFactory();
	
	@Override
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
			List<HealthPack> hps = new ArrayList<HealthPack>();
		    while (wiz.getBackpack().getNumPacks() > i)
		    {
		    	HealthPack hp = wiz.getBackpack().useHealthPack();
		        jgen.writeObject(hp);
		        hps.add(hp);		        
		    }
		    // repack the backpack
		    for (int j = 0; j < hps.size(); j++) {
		    	HealthPack hp = hps.get(j);
		    	wiz.getBackpack().addHealthPack(hp);
		    }
			jgen.writeEndArray();
			jgen.close();		
	}

}
