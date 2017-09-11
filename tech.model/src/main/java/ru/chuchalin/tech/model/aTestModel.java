package ru.chuchalin.tech.model;

import ru.chuchalin.tech.model.dao.GraphQLSchemaModel;

public class aTestModel {

	public static void main(String[] args) {
		//try{
		//	Class.forName("org.postgresql.Driver");
		//	Connection conn = DriverManager.getConnection("jdbc:postgresql://194.87.214.229/TechCalDB", "a.chuchalin", "Achprocedure1@99.2");
		//	System.out.println("true");
		//	conn.close();
		//}catch (Exception e) {
		//	e.printStackTrace();
		//}
		//HibernateUtil hu = new HibernateUtil().init("194.87.214.229/TechCalDB", "a.chuchalin", "Achprocedure1@99.2");
		GraphQLSchemaModel ql = new GraphQLSchemaModel(null);
		System.out.println(ql.jsonResult("query {city {name}}"));
	}

}
