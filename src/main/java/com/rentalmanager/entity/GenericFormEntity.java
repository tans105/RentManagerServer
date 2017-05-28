package com.rentalmanager.entity;

/**
 * @author : tanmay
 * @created : 28-May-2017
 */
public class GenericFormEntity {
	private String fieldName;
	private String fieldId;
	private String fieldType;
	private Integer[] editBy;
	private Integer isMandatory;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Integer[] getEditBy() {
		return editBy;
	}

	public void setEditBy(Integer[] editBy) {
		this.editBy = editBy;
	}

	public Integer getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(Integer isMandatory) {
		this.isMandatory = isMandatory;
	}
}
