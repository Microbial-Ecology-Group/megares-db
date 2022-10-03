package org.meglab;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.jdbi.v3.core.Jdbi;
import org.meglab.db.DescriptionDAO;
import org.meglab.db.SequenceDAO;
import org.meglab.resources.DescriptionResource;
import org.meglab.resources.SequenceResource;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class megaresApplication extends Application<megaresConfiguration> {

    public static void main(final String[] args) throws Exception {
        new megaresApplication().run(args);
    }

    @Override
    public String getName() {
        return "megares";
    }

    @Override
    public void initialize(final Bootstrap<megaresConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(final megaresConfiguration configuration,
                    final Environment environment) {

        //CONFIGURE CORS
        configureCors(environment, "*");

        //DATABASE
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        //DAO
        final DescriptionDAO descriptionDAO = jdbi.onDemand(DescriptionDAO.class);
        final SequenceDAO sequenceDAO = jdbi.onDemand(SequenceDAO.class);

        //RESOURCES
        environment.jersey().register(new DescriptionResource(descriptionDAO));
        environment.jersey().register(new SequenceResource(sequenceDAO));
    }

    private void configureCors(Environment environment, String allowedOrigins) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, allowedOrigins);
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // DO NOT pass a preflight request to down-stream auth filters
        // unauthenticated preflight requests should be permitted by spec
        // https://stackoverflow.com/a/43286482/2314429
        cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());
    }

}
