package pt.ulisboa.tecnico.learnjava.sibs.state;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.sibs.domain.TransferOperation;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public class Deposited implements TransferState {
	@Override
	public void process(TransferOperation operation) throws OperationException, AccountException {
//		// Do the deposit in the account
//		operation.getService().deposit(operation.getTargetIban(), operation.getValue());
		
		// Set the next state
		operation.setState(new Completed());
	}
	
	@Override
	public void cancel(TransferOperation operation) throws OperationException {
		operation.setState(new Canceled());
	}
}
