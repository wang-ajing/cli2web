package org.summer.cli2web.viewmodel;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDataFormat {
	private String desc;
	private DataFormatType formatType;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public DataFormatType getFormatType() {
		return formatType;
	}

	public void setFormatType(DataFormatType formatType) {
		this.formatType = formatType;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public void setProperty(String key, Object value) {
		this.getAdditionalProperties().put(key, value);
	}

}
