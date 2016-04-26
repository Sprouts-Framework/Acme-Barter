package es.us.lsi.dp.converters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import es.us.lsi.dp.domain.DomainEntity;

@Component
public class DomainEntityConverter implements ConditionalGenericConverter {

	@PersistenceContext
	private EntityManager entityManager;

	private static final String BASE64_PREFIX = "base64:";

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> result;

		result = new HashSet<ConvertiblePair>();
		result.add(new ConvertiblePair(String.class, DomainEntity.class));
		result.add(new ConvertiblePair(DomainEntity.class, String.class));

		return result;
	}

	@Override
	public Object convert(final Object source, final TypeDescriptor sourceType, final TypeDescriptor targetType) {
		Object result;

		try {
			if (sourceType.getObjectType().equals(String.class))
				result = decode((String) source, targetType);
			else
				result = encode(source);
		} catch (final Throwable oops) {
			return new RuntimeException(oops);
		}

		return result;
	}

	private Object decode(final String source, final TypeDescriptor targetType) throws ClassNotFoundException, IOException {
		Object result;

		if (isBase64(source))
			result = decodeBase64(withoutBase64Prefix(source));
		else
			result = decodeId(Integer.valueOf(source), targetType.getObjectType());

		return result;
	}

	protected String encode(final Object object) throws ClassNotFoundException, IOException {
		String result;
		ByteArrayOutputStream outputStream;
		ObjectOutputStream objectStream;

		outputStream = new ByteArrayOutputStream();
		objectStream = new ObjectOutputStream(outputStream);
		objectStream.writeObject(object);
		objectStream.close();

		result = new String(Base64.encode(outputStream.toByteArray()));

		return withBase64Prefix(result);
	}

	private Object decodeBase64(final String source) throws IOException, ClassNotFoundException {
		Object result;

		byte[] data;
		InputStream inputStream;
		ObjectInputStream objectStream;

		data = Base64.decode(source.getBytes());
		inputStream = new ByteArrayInputStream(data);
		objectStream = new ObjectInputStream(inputStream);
		result = objectStream.readObject();
		objectStream.close();

		return result;
	}

	@SuppressWarnings("unchecked")
	private Object decodeId(final int id, final Class<?> cls) {
		Object result;

		SimpleJpaRepository<? extends DomainEntity, Integer> repository;

		repository = new SimpleJpaRepository<DomainEntity, Integer>((Class<DomainEntity>) cls, entityManager);
		result = repository.findOne(id);

		return result;
	}

	private boolean isBase64(final String source) {
		String prefix;
		int prefixLength;

		prefixLength = BASE64_PREFIX.length();

		if (source.length() < prefixLength)
			return false;

		prefix = source.substring(0, prefixLength);

		return prefix.equals(BASE64_PREFIX);
	}

	private String withBase64Prefix(final String target) {
		return BASE64_PREFIX + target;
	}

	private String withoutBase64Prefix(final String source) {
		return source.substring(BASE64_PREFIX.length());
	}

	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		sourceType.getAnnotations();
		targetType.getAnnotations();

		return true;
	}

}
