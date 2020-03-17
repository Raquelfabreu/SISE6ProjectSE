package pt.ulisboa.tecnico.learnjava.sibs.operation;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.sibs.domain.TransferOperation;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public class TransferOperationStateTests {
	private static final String FIRST_NAME = "Bonifacio";
	private static final String LAST_NAME = "Jacobino";
	private static final String NIF = "123456789";
	private static final String PHONE_NUMBER = "966696669";
	private static final String ADDRESS = "Algures perdido";
	private static int AGE = 24;

	private static final String FIRST_NAME_TWO = "Aquilino";
	private static final String LAST_NAME_TWO = "Andarilho";
	private static final String NIF_TWO = "012345678";
	private static final String PHONE_NUMBER_TWO = "933393336";
	private static final String ADDRESS_TWO = "Algures procurando";
	private static int AGE_TWO = 29;
	
	private static final int VALUE = 1000;
	
	private Bank sourceBank;
	private Bank targetBank;
	private Client sourceClient;
	private Client targetClientDiffBank;
	private Client targetClientSameBank;
	private String sourceIban;
	private String targetIbanDiffBank;
	private String targetIbanSameBank;

	@Before
	public void setUp() throws BankException, ClientException, AccountException {
		this.sourceBank = new Bank("CGD");
		this.targetBank = new Bank("BPI");
		this.sourceClient = new Client(this.sourceBank, FIRST_NAME, LAST_NAME, NIF, PHONE_NUMBER, ADDRESS, AGE);
		this.targetClientDiffBank = new Client(this.targetBank, FIRST_NAME_TWO, LAST_NAME_TWO, NIF_TWO, PHONE_NUMBER_TWO, ADDRESS_TWO, AGE_TWO);
		this.targetClientSameBank = new Client(this.targetBank, FIRST_NAME_TWO, LAST_NAME_TWO, NIF_TWO, PHONE_NUMBER_TWO, ADDRESS_TWO, AGE_TWO);
		this.sourceIban = this.sourceBank.createAccount(Bank.AccountType.CHECKING, this.sourceClient, VALUE, 0);
		this.targetIbanDiffBank = this.targetBank.createAccount(Bank.AccountType.CHECKING, this.targetClientDiffBank, VALUE, 0);
		this.targetIbanSameBank = this.targetBank.createAccount(Bank.AccountType.CHECKING, this.targetClientSameBank, VALUE, 0);
	}

	
	@Test
	public void processChange2Withdrawn() throws OperationException, AccountException {
		TransferOperation operation = new TransferOperation(sourceIban, targetIbanDiffBank, 100);
		operation.process();
		
		assertEquals("Withdrawn", operation.getState());
	}
	
	
	@After
	public void tearDown() {
		Bank.clearBanks();
	}
	
}