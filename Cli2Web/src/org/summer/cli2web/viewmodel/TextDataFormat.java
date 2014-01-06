package org.summer.cli2web.viewmodel;


public class TextDataFormat extends AbstractDataFormat {
	public TextDataFormat(String desc) {
		super();
		this.setDesc(desc);
		this.setFormatType(DataFormatType.TEXT);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"desc\": ");
		builder.append(getDesc());
		builder.append(", \"formatType\": ");
		builder.append(getFormatType());
		builder.append("}");
		return builder.toString();
	}



	
	
}
