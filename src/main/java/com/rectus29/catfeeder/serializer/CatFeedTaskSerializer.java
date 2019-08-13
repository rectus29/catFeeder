package com.rectus29.catfeeder.serializer;


import com.google.gson.*;
import com.rectus29.catfeeder.CatFeederConfiguration;
import com.rectus29.catfeeder.task.CatFeedTask;
import com.rectus29.catfeeder.utils.SchedulingPattern;

import java.lang.reflect.Type;

public class CatFeedTaskSerializer implements JsonSerializer<CatFeedTask>, JsonDeserializer<CatFeedTask> {

	public CatFeedTaskSerializer() {
	}

	@Override
	public CatFeedTask deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		CatFeedTask out = new CatFeedTask();
		out.setUid(jsonObject.get("uid").getAsString());
		out.setQuantity(jsonObject.get("quantity").getAsInt());
		jsonObject.getAsJsonArray("schedulingPatterns")
				.forEach(jsonElement1 -> out.getSchedulingPatterns().add(jsonDeserializationContext.deserialize(jsonElement1, SchedulingPattern.class)));
		return out;
	}

	@Override
	public JsonElement serialize(CatFeedTask catFeedTask, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject out = new JsonObject();
		out.addProperty("uid", catFeedTask.getUid());
		out.addProperty("quantity", catFeedTask.getQuantity());
		JsonArray jsonArray = new JsonArray();
		catFeedTask.getSchedulingPatterns().forEach((el) -> jsonArray.add(jsonSerializationContext.serialize(el)));
		out.add("schedulingPatterns", jsonArray);
		return out;
	}
}
