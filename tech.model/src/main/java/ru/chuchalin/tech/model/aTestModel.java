package ru.chuchalin.tech.model;

import ru.chuchalin.tech.model.dao.GraphQLSchemaModel;

public class aTestModel {

	public static void main(String[] args) {
		System.out.println(GraphQLSchemaModel.jsonResult("query { event { eventID,  eventAddress {city {name}} } }"));
	}

}
