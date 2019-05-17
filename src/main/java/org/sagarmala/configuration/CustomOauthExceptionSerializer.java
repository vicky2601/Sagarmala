package org.sagarmala.configuration;

import java.io.IOException;
import java.util.Map;

import org.sagarmala.enumtype.constants.Status;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/*
 * @author Vicky 
*/

public class CustomOauthExceptionSerializer extends StdSerializer<UserOauthException> {

	private static final long serialVersionUID = 1L;

	public CustomOauthExceptionSerializer() {
		super(UserOauthException.class);
	}

	@Override
	public void serialize(UserOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		if (value.getMessage().equalsIgnoreCase("Block Users")) {
			gen.writeStringField("error", "" + Status.BLOCKED_USER.getResponseCode());
			gen.writeStringField("error_description", "" + Status.BLOCKED_USER.getResponseDescription());
		} else if (value.getMessage().equalsIgnoreCase("Deactivate Users")) {
			gen.writeStringField("error", "" + Status.DEACTIVATE_USER.getResponseCode());
			gen.writeStringField("error_description", "" + Status.DEACTIVATE_USER.getResponseDescription());
		} else if (value.getMessage().equalsIgnoreCase("Assign Permission")) {
			gen.writeStringField("error", "" + Status.ASSIGN_USER.getResponseCode());
			gen.writeStringField("error_description", "" + Status.ASSIGN_USER.getResponseDescription());
		} else {
			gen.writeStringField("error", "" + Status.INVALID_USER_NAME_PASSWORD.getResponseCode());
			gen.writeStringField("error_description",
					"" + Status.INVALID_USER_NAME_PASSWORD.getResponseDescription());
		}
		if (value.getAdditionalInformation() != null) {
			for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
				String key = entry.getKey();
				String add = entry.getValue();
				gen.writeStringField(key, add);
			}
		}
		gen.writeEndObject();
	}
}
