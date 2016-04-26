package es.us.lsi.dp.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

@Component
public class TxHandler {

	@Autowired
	private PlatformTransactionManager transactionManager;

	private static final ThreadLocal<TransactionStatus> txStatus = new ThreadLocal<>();

	private TransactionStatus status() {
		return txStatus.get();
	}

	public boolean hasBegun() {
		return status() != null;
	}

	public void begin() {
		begin(false);
	}

	public void begin(final boolean readOnly) {
		Assert.isTrue(!hasBegun());

		DefaultTransactionDefinition definition;

		definition = getTransactionDefinition(readOnly);

		txStatus.set(transactionManager.getTransaction(definition));
	}

	public void commit() {
		Assert.isTrue(hasBegun());

		TransactionStatus status;

		status = status();

		try {
			transactionManager.commit(status);
			close();
		} catch (final Throwable oops) {
			throw oops;
		}
	}

	public void rollback() {
		Assert.isTrue(hasBegun());

		TransactionStatus status;

		status = status();

		try {
			if (!status.isCompleted())
				transactionManager.rollback(status);
			close();
		} catch (final Throwable oops) {
			throw oops;
		}
	}

	public void close() {
		txStatus.set(null);
	}

	private DefaultTransactionDefinition getTransactionDefinition(final boolean readOnly) {
		DefaultTransactionDefinition result;

		result = new DefaultTransactionDefinition();
		result.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		result.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		result.setReadOnly(readOnly);

		return result;
	}

	public TransactionTemplate getTransactionTemplate() {
		return new TransactionTemplate(transactionManager, getTransactionDefinition(false));
	}

}
