package ru.chuchalin.tech.model.dao;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;

import java.util.Date;

import com.google.gson.Gson;

import graphql.ExecutionInput;
import graphql.GraphQL;
import graphql.Scalars;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import ru.chuchalin.tech.model.City;
import ru.chuchalin.tech.model.Event;
import ru.chuchalin.tech.model.EventAddress;

public class GraphQLSchemaModel {
	private static DataFetcher<Event> eventFetcher = new DataFetcher<Event>() {
		@Override
		public Event get(DataFetchingEnvironment environment) {
			Event ev = new Event();
			City ct = new City();
			ct.setName("Москва");
			ct.setCityID(1);
			EventAddress addr = new EventAddress();
			addr.setCity(ct);
			ev.setEventID(1);
			ev.setBeginDateTime(new Date());
			ev.setEventAddress(addr);
			System.out.println(ev);
			return ev;
		}

	};

	private static GraphQLObjectType CityType = newObject().name("City")
			.field(newFieldDefinition().name("cityID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("name").type(Scalars.GraphQLString)).build();

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
			.field(newFieldDefinition().name("event").type(EventType)
					.argument(newArgument().name("id").type(Scalars.GraphQLInt)).dataFetcher(eventFetcher))
			.field(newFieldDefinition().name("profile").type(EventType)
					.argument(newArgument().name("id").type(Scalars.GraphQLInt)))
			.build();

	private static GraphQLSchema modelSchema = GraphQLSchema.newSchema().query(queryType).build();

	private static GraphQL modelGraphQL = GraphQL.newGraphQL(modelSchema).build();

	public static String jsonResult(String queryStr) {
		return new Gson().toJson(
				modelGraphQL.execute(ExecutionInput.newExecutionInput().query(queryStr).build()).toSpecification());
	}

}
