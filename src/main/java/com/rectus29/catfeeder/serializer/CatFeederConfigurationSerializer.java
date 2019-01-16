package com.rectus29.catfeeder.serializer;


import com.google.gson.*;
import com.rectus29.catfeeder.CatFeederConfiguration;
import com.rectus29.catfeeder.CatFeederScheduleEntry;
import com.rectus29.catfeeder.utils.SchedulingPattern;

import java.lang.reflect.Type;

public class CatFeederConfigurationSerializer implements JsonSerializer<CatFeederConfiguration>, JsonDeserializer<CatFeederConfiguration> {

	public CatFeederConfigurationSerializer() {
	}

	@Override
	public CatFeederConfiguration deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject jsonObject = jsonElement.getAsJsonObject();

		CatFeederConfiguration out = new CatFeederConfiguration();
		out.setBuildNumber(jsonObject.get("buildNumber").getAsString());
		out.setOpeningTime(jsonObject.get("openingTime").getAsInt());
		out.setVersion(jsonObject.get("version").getAsString());
		jsonObject.getAsJsonArray("scheduledTask").forEach(jsonElement1 -> out.getScheduledTask().add(jsonDeserializationContext.deserialize(jsonElement1, CatFeederScheduleEntry.class)));
		return out;
	}

	@Override
	public JsonElement serialize(CatFeederConfiguration catFeederConfiguration, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject out = new JsonObject();
		out.addProperty("name", "catFeederConfiguration");
		out.addProperty("buildNumber", catFeederConfiguration.getBuildNumber());
		out.addProperty("version", catFeederConfiguration.getVersion());
		out.addProperty("openingTime", catFeederConfiguration.getOpeningTime());
		JsonArray jsonArray = new JsonArray();
		catFeederConfiguration.getScheduledTask().forEach((el) -> jsonArray.add(jsonSerializationContext.serialize(el)));
		out.add("scheduledTask", jsonArray);
		return out;
	}
}
