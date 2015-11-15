package storm.buleprints.OutBreakDetection;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.trident.spout.ITridentSpout.BatchCoordinator;

public class DefaultCoordinator implements BatchCoordinator<Long>, Serializable {

	private static final long serialVersionUID=1L;
	private static final Logger LOG=LoggerFactory.getLogger(DefaultCoordinator.class);
	
	
	public void close() {		
	}

	public Long initializeTransaction(long txid, Long prevMetadata, Long currentMetadata) {
		LOG.info("Initializing Transction["+txid+"]");
		return null;
	}

	public boolean isReady(long txid) {
		return true;
	}

	public void success(long txid) {
		LOG.info("Successful Transaction ["+txid+"]");
	}
	

}
