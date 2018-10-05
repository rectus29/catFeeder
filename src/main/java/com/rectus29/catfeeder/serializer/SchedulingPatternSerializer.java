package com.rectus29.catfeeder.serializer;


import com.google.gson.*;
import com.rectus29.catfeeder.utils.SchedulingPattern;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalTime;

import java.lang.reflect.Type;

public class SchedulingPatternSerializer implements JsonSerializer<SchedulingPattern>, JsonDeserializer<SchedulingPattern> {

	@Override
	public SchedulingPattern deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		SchedulingPattern out = new SchedulingPattern();
		out.setDayNumber(jsonElement.getAsJsonObject().get("day").getAsInt());
		out.setUid(jsonElement.getAsJsonObject().get("uid").getAsString());
		String hour = jsonElement.getAsJsonObject().get("hour").getAsString();
		out.setStartTime(new LocalTime(Integer.parseInt(hour.split(":")[0]), Integer.parseInt(hour.split(":")[1])));
		out.setEndTime(new LocalTime(00, 00));
		return out;
	}

	@Override
	public JsonElement serialize(SchedulingPattern schedulingPattern, Type type, JsonSerializationContext jsonSerializationContext) {
		JsonObject out = new JsonObject();
		out.addProperty("day", schedulingPattern.getDayNumber());
		out.addProperty("hour", StringUtils.leftPad(schedulingPattern.getStartTime().getHourOfDay()+"", 2, '0') + ":" + StringUtils.leftPad(schedulingPattern.getStartTime().getMinuteOfHour()+"", 2, '0')); // ugly to rebluid
		out.addProperty("uid", schedulingPattern.getUid());
		return out;
	}
}
