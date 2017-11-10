package engine.rule.model;

import java.lang.reflect.Field;

import engine.rule.model.annotation.DBField;

public class ModelFieldOfDB {

	public static enum DBMappingType {
		FIELDNAME, ADAPTER
	}

	private DBField dbField;

	private Field modelField;

	private DBMappingType mappingType;

	public ModelFieldOfDB(DBField dbField, Field modelField) {
		this(dbField, modelField, DBMappingType.FIELDNAME);
	}

	public ModelFieldOfDB(DBField dbField, Field modelField,
			DBMappingType mappingType) {
		this.dbField = dbField;
		this.modelField = modelField;
		this.mappingType = mappingType;
	}

	public DBField getDbField() {
		return dbField;
	}

	public void setDbField(DBField dbField) {
		this.dbField = dbField;
	}

	public Field getModelField() {
		return modelField;
	}

	public void setModelField(Field modelField) {
		this.modelField = modelField;
	}

	public DBMappingType getMappingType() {
		return mappingType;
	}

	public void setMappingType(DBMappingType mappingType) {
		this.mappingType = mappingType;
	}

	public boolean isByField() {
		return this.mappingType == DBMappingType.FIELDNAME;
	}

	public boolean isByAdapter() {
		return this.mappingType == DBMappingType.ADAPTER;
	}

}
