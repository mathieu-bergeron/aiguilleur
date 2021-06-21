package ca.ntro.jj.services.tracer;

public class TracedCallNull implements TracedCall {

	@Override
	public String getMethodName() {
		return "";
	}

	@Override
	public String getFileName() {
		return "";
	}

	@Override
	public int getLineNumber() {
		return 0;
	}

}
