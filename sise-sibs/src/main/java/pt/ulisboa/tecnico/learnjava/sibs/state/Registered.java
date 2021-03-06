package pt.ulisboa.tecnico.learnjava.sibs.state;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.TransferOperation;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public class Registered implements TransferState {
	@Override
	public void process(TransferOperation operation, Services service) throws OperationException, AccountException {
		// Do the withdraw in the account
		service.withdraw(operation.getSourceIban(), operation.getValue());
		
		// Set the state to withdraw
		operation.setState(new Withdrawn());
	}
	
	@Override
	public void cancel(TransferOperation operation, Services service) throws OperationException {
		operation.setState(new Canceled());
	}
}
