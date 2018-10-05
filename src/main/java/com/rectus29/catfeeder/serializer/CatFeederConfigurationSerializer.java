package com.rectus29.catfeeder.serializer;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 05/10/2018 11:56               */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.google.gson.*;
import com.rectus29.catfeeder.CatFeederConfiguration;

import java.lang.reflect.Type;

public class CatFeederConfigurationSerializer implements JsonSerializer<CatFeederConfiguration>, JsonDeserializer<CatFeederConfiguration> {

	public CatFeederConfigurationSerializer() {
	}

	@Override
	public CatFeederConfiguration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		return null;
	}

	@Override
	public JsonElement serialize(CatFeederConfiguration catFeederConfiguration, Type type, JsonSerializationContext jsonSerializationContext) {
		return null;
	}
}
