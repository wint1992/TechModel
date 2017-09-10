package ru.chuchalin.tech.model.dao;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;

import com.google.gson.Gson;

import graphql.ExecutionInput;
import graphql.GraphQL;
import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
public class GraphQLSchemaModel {
	private static GraphQLObjectType CityType = newObject().name("City")
			.field(newFieldDefinition().name("cityID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("name").type(Scalars.GraphQLString)).build();
	
	private static GraphQLObjectType ProfileType = newObject().name("Profile")
			.field(newFieldDefinition().name("profileID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("firstName").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("lastName").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("username").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("nickname").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("city").type(CityType))
			.field(newFieldDefinition().name("age").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("birthDate").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("email").type(Scalars.GraphQLString)).build();

	private static GraphQLObjectType EventMusicStyleType = newObject().name("EventMusicStyle")
			.field(newFieldDefinition().name("styleID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("style").type(Scalars.GraphQLString)).build();

	private static GraphQLObjectType EventAddressType = newObject().name("EventAddress")
			.field(newFieldDefinition().name("eventAddressID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("place").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("city").type(CityType))
			.field(newFieldDefinition().name("street").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("house").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("building").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("construction").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("fullAddress").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("gps").type(Scalars.GraphQLString)).build();

	private static GraphQLObjectType EventType = newObject().name("Event")
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

	private static GraphQLObjectType queryType = newObject().name("QueryType")
			.field(newFieldDefinition().name("event").type(list(EventType))
					.argument(newArgument().name("id").type(Scalars.GraphQLInt)).dataFetcher(GraphQLSchemaDataFetch.eventFetcher))
			.field(newFieldDefinition().name("profile").type(list(ProfileType))
					.argument(newArgument().name("id").type(Scalars.GraphQLInt)).dataFetcher(GraphQLSchemaDataFetch.profileFetcher))
			.field(newFieldDefinition().name("city").type(list(CityType))
					.argument(newArgument().name("id").type(Scalars.GraphQLInt)).dataFetcher(GraphQLSchemaDataFetch.cityFetcher))
			.field(newFieldDefinition().name("musicStyle").type(list(EventMusicStyleType))
					.argument(newArgument().name("id").type(Scalars.GraphQLInt)).dataFetcher(GraphQLSchemaDataFetch.musicStyleFetcher))
			.field(newFieldDefinition().name("eventAddress").type(list(EventAddressType))
					.argument(newArgument().name("id").type(Scalars.GraphQLInt)).dataFetcher(GraphQLSchemaDataFetch.eventAddressFetcher))
			.build();

	private static GraphQLSchema modelSchema = GraphQLSchema.newSchema().query(queryType).build();

	private static GraphQL modelGraphQL = GraphQL.newGraphQL(modelSchema).build();

	public static String jsonResult(String queryStr) {
		return new Gson().toJson(
				modelGraphQL.execute(ExecutionInput.newExecutionInput().query(queryStr).build()).toSpecification());
	}

}
