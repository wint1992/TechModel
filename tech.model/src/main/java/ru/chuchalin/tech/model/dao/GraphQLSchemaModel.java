package ru.chuchalin.tech.model.dao;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;

import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

public class GraphQLSchemaModel {
	public static GraphQLObjectType CityType = newObject().name("City")
			.field(newFieldDefinition().name("cityID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("name").type(Scalars.GraphQLString)).build();

	public static GraphQLObjectType EventMusicStyleType = newObject().name("EventMusicStyle")
			.field(newFieldDefinition().name("styleID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("style").type(Scalars.GraphQLString)).build();

	public static GraphQLObjectType EventAddressType = newObject().name("EventAddress")
			.field(newFieldDefinition().name("eventAddressID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("place").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("city").type(CityType))
			.field(newFieldDefinition().name("street").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("house").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("building").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("construction").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("fullAddress").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("gps").type(Scalars.GraphQLString)).build();

	public static GraphQLObjectType EventType = newObject().name("Event")
			.field(newFieldDefinition().name("eventID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("eventName").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("eventAddress").type(EventAddressType))
			.field(newFieldDefinition().name("beginDateTime").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("endDateTime").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("description").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("comment").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("uri").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("backgroundPhoto").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("cost").type(Scalars.GraphQLBigDecimal))
			.field(newFieldDefinition().name("priority").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("eventMusicStyles").type(list(EventMusicStyleType))).build();

	public static GraphQLObjectType queryType = newObject().name("QueryType").field(newFieldDefinition()
			.name("event").type(EventType).argument(newArgument().name("id").type(Scalars.GraphQLInt)))
			.build();

	public static GraphQLSchema starWarsSchema = GraphQLSchema.newSchema().query(queryType).build();
}
