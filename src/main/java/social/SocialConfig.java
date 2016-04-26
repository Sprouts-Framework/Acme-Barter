package social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

	private static String TWITTER_CONSUMER_KEY = "";
	private static String TWITTER_CONSUMER_SECRET = "";

	private static String GOOGLE_CLIENT_ID = "";
	private static String GOOGLE_CLIENT_SECRET = "";

	@Autowired
	DataSource dataSource;

	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		ConnectController controller = new ConnectController(connectionFactoryLocator, connectionRepository);
		return controller;
	}

	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
		cfConfig.addConnectionFactory(new TwitterConnectionFactory(TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET));

		GoogleConnectionFactory googleConnection = new GoogleConnectionFactory(GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET);
		googleConnection.setScope("profile");
		googleConnection.setScope("email");
		cfConfig.addConnectionFactory(googleConnection);

	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Override
	@Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		InMemoryUsersConnectionRepository repository = new InMemoryUsersConnectionRepository(connectionFactoryLocator);
		return repository;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Twitter twitter(ConnectionRepository repository) {
		Connection<Twitter> connection = repository.findPrimaryConnection(Twitter.class);
		return connection != null ? connection.getApi() : null;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Google google(ConnectionRepository repository) {
		Connection<Google> connection = repository.findPrimaryConnection(Google.class);
		return connection != null ? connection.getApi() : null;
	}

}
