package ca.ntro.core.graph_writer;

import ca.ntro.core.stream.Stream;

public interface RecordSpec extends RecordItemSpec, NodeSpec {
	
	Stream<RecordItemSpec> items();

}
