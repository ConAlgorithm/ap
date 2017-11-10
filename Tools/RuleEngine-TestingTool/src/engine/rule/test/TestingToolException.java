package engine.rule.test;

import catfish.base.exception.BaseExceptionModel;
import catfish.base.exception.ExceptionCode;

public class TestingToolException {

	public final static ExceptionCode NO_HEADER_ERROR = new BaseExceptionModel("NoHeaderError");
	
	public final static ExceptionCode SHUTDOWN_TESTER_ERROR = new BaseExceptionModel("ShutdownTesterError");
	
	public final static ExceptionCode TESTER_ALREADY_RUNNING = new BaseExceptionModel("TesterAlreadyRunning");
	
	public final static ExceptionCode STORE_TESTCASE_ERROR = new BaseExceptionModel("StoreTestCaseError");
	
	public final static ExceptionCode NO_TESTRESULT_ERROR = new BaseExceptionModel("NoTestResultError");
	
	public final static ExceptionCode NO_TESTCASES_ERROR = new BaseExceptionModel("NoTestCasesError");
}
