package jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

/**
 * Rule that creates an embedded H2 database
 * DatabaseRule.java
 * Created on 10/10/2013 by Nikolay Chorniy / Andrey Chorniy
 */
public class DatabaseRule implements MethodRule {
    private static ThreadLocal<Session> session = new ThreadLocal<Session>();
    private static final Logger logger = LoggerFactory.getLogger(DatabaseRule.class);
    private SessionFactory sessionFactory;

    private List<Class> entities;

    public DatabaseRule(Class ... entities) {
        this(Arrays.asList(entities));
    }

    public DatabaseRule(List<Class> entities) {
        this.entities = entities;
    }

    static Session getSession() {
        return session.get();
    }
    static Connection getConnection() {
        return getSession().connection();
    }

    @Override
    public Statement apply(final Statement statement, FrameworkMethod frameworkMethod, Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                AnnotationConfiguration configuration = new AnnotationConfiguration();
                for (Class entityClass : entities) {
                    configuration.addAnnotatedClass(entityClass);
                }

                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
                configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
                configuration.setProperty("hibernate.connection.url", "jdbc:h2:mem");
                configuration.setProperty("hibernate.hbm2ddl.auto", "create");
                sessionFactory = configuration.buildSessionFactory();
                Session _session = sessionFactory.openSession();
                session.set(_session);

                try {
                    statement.evaluate();
                } finally {
                    try {
                        session.get().close();
                        sessionFactory.close();
                    } catch (Exception e) {
                        logger.info("Failed to close database connection", e);
                    }
                }

            }
        };
    }
}