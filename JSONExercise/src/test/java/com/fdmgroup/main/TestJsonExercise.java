package com.fdmgroup.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fdmgroup.serialization.game.Backpack;
import com.fdmgroup.serialization.game.HealthPack;

public class TestJsonExercise {

	private WizardSerializer jsonSerialize;
	private WizardDeserializer jsonDeserialize;
	private Shield shield;
	private Powers power;
	private Wizard wiz;
	private HealthPack healthPack50;
	private HealthPack healthPack100;
	private HealthPack healthPack150;
	private HealthPack healthPack250;
	private HealthPack healthPack350;
	private HealthPack healthPack450;
	private HealthPack healthPack550;
	private Backpack backpack;
	private File file;
	private ObjectMapper mapper;
	private SimpleModule module;

	@Before
	public void setUp() throws Exception {
		file = new File("Wizard.json");
		jsonSerialize = new WizardSerializer(Wizard.class);
		jsonDeserialize = new WizardDeserializer(Wizard.class);
		shield = new Shield("Shield", "Circle", "Metal");
		power = new Powers("Force Jump", 50);
		backpack = new Backpack();
		healthPack50 = new HealthPack(50);
		healthPack100 = new HealthPack(100);
		healthPack150 = new HealthPack(150);
		healthPack250 = new HealthPack(250);
		healthPack350 = new HealthPack(350);
		healthPack450 = new HealthPack(450);
		healthPack550 = new HealthPack(550);
		backpack.addHealthPack(healthPack50);
		backpack.addHealthPack(healthPack100);
		backpack.addHealthPack(healthPack150);
		backpack.addHealthPack(healthPack250);
		backpack.addHealthPack(healthPack350);
		backpack.addHealthPack(healthPack450);
		backpack.addHealthPack(healthPack550);
		wiz = new Wizard("Wizard", 100, shield, power, backpack);
		mapper = new ObjectMapper();
		module = new SimpleModule("PlayerJsonPersistenceManager", new Version(2, 1, 3, null, null, null));
		module.addSerializer(Wizard.class, jsonSerialize);
		module.addDeserializer(Wizard.class, jsonDeserialize);
		mapper.registerModule(module);

	}

	@Test
	public void testShieldIsSerializable() {
		// Act
		Wizard newWiz;
		try {
			newWiz = mapper.readValue(file, Wizard.class);
			String actual = newWiz.getShield().getName();
			String expected = wiz.getShield().getName();
			// Assert
			assertEquals(expected, actual);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPowerIsSerializable() {
		// Act
		Wizard newWiz;
		try {
			newWiz = mapper.readValue(file, Wizard.class);
			String actual = newWiz.getPower().getName();
			String expected = wiz.getPower().getName();
			// Assert
			assertEquals(expected, actual);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNameIsSerializable() {
		// Act
		Wizard newWiz;
		try {
			newWiz = mapper.readValue(file, Wizard.class);
			String actual = newWiz.getName();
			String expected = wiz.getName();
			// Assert
			assertEquals(expected, actual);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHealthPointsIsSerializable() {
		// Act
		Wizard newWiz;
		try {
			newWiz = mapper.readValue(file, Wizard.class);
			int actual = newWiz.getHealthPoints();
			int expected = wiz.getHealthPoints();
			// Assert
			assertEquals(expected, actual);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNumberOfHealthPacksInBackpackSerializable() {
		// Act
		Wizard newWiz;
		try {
			newWiz = mapper.readValue(file, Wizard.class);
			int actual = newWiz.getBackpack().getNumPacks();
			int expected = wiz.getBackpack().getNumPacks();
			// Assert
			assertEquals(expected, actual);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHealthPointsInHealthPacksInBackpackIsSerializable() {
		// Act
		Wizard newWiz;
		try {
			newWiz = mapper.readValue(file, Wizard.class);
			int actual = newWiz.getBackpack().useHealthPack().getHealthPoints();
			int expected = wiz.getBackpack().useHealthPack().getHealthPoints();
			// Assert
			assertEquals(expected, actual);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testSerializableDoesNotDestroyState() {
		// Act
		Wizard newWiz;
		try {
			newWiz = mapper.readValue(file, Wizard.class);
			Wizard actual = newWiz;
			// Assert
			assertNotNull(actual);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testToSerializeWizard() {
		// Act
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule module = new SimpleModule("WizardSerializer", new Version(2, 1, 3, null, null, null));
		module.addSerializer(Wizard.class, jsonSerialize);

		mapper.registerModule(module);

		try {
			String wizJson = mapper.writeValueAsString(wiz);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void randomTest() {

		try {
			Wizard wizzy = mapper.readValue(file, Wizard.class);
			System.out.println(wizzy);
			System.out.println(wizzy.getBackpack().useHealthPack().getHealthPoints());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
