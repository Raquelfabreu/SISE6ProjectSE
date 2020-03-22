package pt.ulisboa.tecnico.learnjava.sibs.state;

import pt.ulisboa.tecnico.learnjava.sibs.domain.TransferOperation;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public class Canceled implements TransferState {
	@Override
	public void process(TransferOperation operation) throws OperationException {
		throw new OperationException("Error in \"process\" method! "
				+ "You can not process a \"Canceled\" operation.");
	}
	
	@Override
	public void cancel(TransferOperation operation) throws OperationException {
		throw new OperationException("Error in \"cancel\" method! The operation "
				+ "is already \"Canceled\".");
	}
}
