package ca.ntro.core.files;

public class RemoteTextFileNull implements RemoteTextFile {

	@Override
	public String read() {
		return null;
	}
}
