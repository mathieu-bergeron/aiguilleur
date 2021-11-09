package ca.ntro.jj.files;

public class RemoteTextFileNull implements RemoteTextFile {

	@Override
	public String read() {
		return null;
	}
}
