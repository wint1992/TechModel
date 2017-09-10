package ru.chuchalin.tech.model;

import ru.chuchalin.tech.model.dao.GraphQLSchemaModel;

public class aTestModel {

	public static void main(String[] args) {
		System.out.println(GraphQLSchemaModel.jsonResult("query { event(id:1) { eventID, beginDateTime,  eventAddress {city {name}} } }"));
	}

}
