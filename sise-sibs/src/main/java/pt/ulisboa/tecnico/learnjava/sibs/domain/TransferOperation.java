package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.state.Registered;
import pt.ulisboa.tecnico.learnjava.sibs.state.TransferState;

public class TransferOperation extends Operation {
	private final String sourceIban;
	private final String targetIban;
	
	private TransferState currentState;

	public TransferOperation(String sourceIban, String targetIban, int value) throws OperationException {
		super(Operation.OPERATION_TRANSFER, value);

		if (invalidString(sourceIban) || invalidString(targetIban)) {
			throw new OperationException();
		}

		this.sourceIban = sourceIban;
		this.targetIban = targetIban;
		
		this.currentState = new Registered();
	}

	private boolean invalidString(String name) {
		return name == null || name.length() == 0;
	}
	
	public void setState(TransferState state) {
		this.currentState = state;
	}
	
	public TransferState getState() {
		return this.currentState;
	}

	@Override
	public int commission() {
		return (int) Math.round(super.commission() + getValue() * 0.05);
	}
	
	public void process(Services service) throws OperationException, AccountException {
		currentState.process(this, service);
	}
	
	public void cancel(Services service) throws OperationException, AccountException {
		currentState.cancel(this, service);
	}

	public String getSourceIban() {
		return this.sourceIban;
	}

	public String getTargetIban() {
		return this.targetIban;
	}

}
