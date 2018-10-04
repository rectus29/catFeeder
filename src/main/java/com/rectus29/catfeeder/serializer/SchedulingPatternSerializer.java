package com.rectus29.catfeeder.serializer;

/*-----------------------------------------------------*/
/*      _____           _               ___   ___      */
/*     |  __ \         | |             |__ \ / _ \     */
/*     | |__) |___  ___| |_ _   _ ___     ) | (_) |    */
/*     |  _  // _ \/ __| __| | | / __|   / / \__, |    */
/*     | | \ \  __/ (__| |_| |_| \__ \  / /_   / /     */
/*     |_|  \_\___|\___|\__|\__,_|___/ |____| /_/      */
/*                                                     */
/*                Date: 04/10/2018 17:54                */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

import com.google.gson.*;
import com.rectus29.catfeeder.utils.SchedulingPattern;

import java.lang.reflect.Type;

public class SchedulingPatternSerializer implements JsonSerializer<SchedulingPattern>, JsonDeserializer<SchedulingPattern> {

	@Override
	public SchedulingPattern deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		return null;
	}

	@Override
	public JsonElement serialize(SchedulingPattern schedulingPattern, Type type, JsonSerializationContext jsonSerializationContext) {
		return null;
	}
}
