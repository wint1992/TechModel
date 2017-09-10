package ru.chuchalin.tech.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.jpa.QueryHints;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import ru.chuchalin.tech.model.City;
import ru.chuchalin.tech.model.Event;
import ru.chuchalin.tech.model.EventAddress;

public class GraphQLSchemaDataFetch {
	private static final HibernateUtil dataSession;
	static {
		dataSession = new HibernateUtil().init("jdbc:postgresql://194.87.214.229/TechCalDB", "a.chuchalin",
				"Achprocedure1@99.2");
	}

	public static DataFetcher eventFetcher = new DataFetcher() {
		@Override
		public Object get(DataFetchingEnvironment environment) {
			List resultList = new ArrayList();
			City ct = new City();
			ct.setName("Москва");
			ct.setCityID(1);
			EventAddress addr = new EventAddress();
			addr.setCity(ct);
			Event ev = new Event();
			ev.setEventID(1);
			ev.setBeginDateTime(new Date());
			ev.setEventAddress(addr);
			Event ev2 = new Event();
			ev2.setEventID(2);
			ev2.setBeginDateTime(new Date());
			if (!environment.containsArgument("id")) {
				resultList.add(ev);
				resultList.add(ev2);
			} else
				resultList.add(ev);
			return resultList;
		}
	};

	public static DataFetcher profileFetcher = new DataFetcher() {
		@Override
		public Object get(DataFetchingEnvironment environment) {
			List resultList = new ArrayList();
			return resultList;
		}
	};

	public static DataFetcher cityFetcher = new DataFetcher() {
		@Override
		public Object get(DataFetchingEnvironment environment) {
			List resultList = new ArrayList();
			if (dataSession != null) {
				Session sf = null;
				try {
					sf = dataSession.getSessionFactory().openSession();
					resultList = sf.createQuery("from City") // sf.createQuery("from
																// City where ID
																// = " + cityID)
							.setHint(QueryHints.HINT_FETCH_SIZE, 10).list();
				} catch (Exception e) {
				} finally {
					if (sf != null && sf.isOpen()) {
						sf.close();
					}
				}
			}

			return resultList;
		}
	};

	public static DataFetcher musicStyleFetcher = new DataFetcher() {
		@Override
		public Object get(DataFetchingEnvironment environment) {
			List resultList = new ArrayList();
			return resultList;
		}
	};

	public static DataFetcher eventAddressFetcher = new DataFetcher() {
		@Override
		public Object get(DataFetchingEnvironment environment) {
			List resultList = new ArrayList();
			return resultList;
		}
	};
}
