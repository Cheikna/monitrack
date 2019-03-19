package com.monitrack.enumeration;

public enum JSONField {
	
	ENTITY("entity"),
	
	DATAS("datas"),
	
	IS_LIST("is_list_of_entities"),
	
	REQUEST_INFO("request_info"),
	
	REQUEST_TYPE("request_type"),
	
	REQUESTED_FIELDS("requested_fields"),
	
	REQUIRED_VALUES("required_values"),
	
	REQUESTED_ENTITY("requested_entity"),
	
	SERIALIZED_OBJECT("serialized_object"),
	
	//The message fields will allow us to know if the serialization or deserialization happened without failures
	MESSAGE("message");
	
	
	private String label;
	
	JSONField(String label)
	{
		this.label = label;
	}
	
	public String getLabel()
	{
		return this.label;
	}

}
