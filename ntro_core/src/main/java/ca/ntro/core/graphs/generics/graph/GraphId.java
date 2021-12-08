package ca.ntro.core.graphs.generics.graph;

import ca.ntro.core.identifyers.StorageId;
import ca.ntro.core.path.Path;

public class GraphId extends StorageId {
	
	public static final String CATEGORY = "graphs";
	
	private static int nextGraphNumber = 0;
	
	public static GraphId fromGraphName(String graphName) {

		GraphId id = new GraphId();

		try {

			int graphNumber = Integer.parseInt(graphName);
			if(graphNumber < nextGraphNumber) {
				graphName = formatGraphNumber(++nextGraphNumber);
			}

		}catch(NumberFormatException e) {}
		
		id.setCategoryPath(Path.fromRawPath(CATEGORY));
		id.setEntityPath(Path.fromRawPath(graphName));

		return id;
	}
	
	private static String formatGraphNumber(int graphNumber) {
		StringBuilder builder = new StringBuilder();

		if(graphNumber < 10) {
			builder.append(0);
		}

		builder.append(graphNumber);
		
		return builder.toString();
	}

	public static GraphId newGraphId() {
		return fromGraphName(formatGraphNumber(++nextGraphNumber));
	}
}
