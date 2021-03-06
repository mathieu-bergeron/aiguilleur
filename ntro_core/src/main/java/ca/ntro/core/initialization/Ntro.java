package ca.ntro.core.initialization;

import ca.ntro.core.graph_writer.GraphWriter;
import ca.ntro.core.graph_writer.GraphWriterNull;
import ca.ntro.core.services.Asserter;
import ca.ntro.core.services.AsserterNull;
import ca.ntro.core.services.Collections;
import ca.ntro.core.services.CollectionsNull;
import ca.ntro.core.services.ExceptionThrower;
import ca.ntro.core.services.ExceptionThrowerNull;
import ca.ntro.core.services.Random;
import ca.ntro.core.services.RandomNull;
import ca.ntro.core.services.ReflectionService;
import ca.ntro.core.services.ReflectionServiceNull;
import ca.ntro.core.services.Time;
import ca.ntro.core.services.TimeNull;
import ca.ntro.core.services.StackAnalyzer;
import ca.ntro.core.services.StackAnalyzerNull;

public class Ntro {
	
	/* <ExceptionThrower> */
	
	private static ExceptionThrower exceptionThrower = new ExceptionThrowerNull();
	
	static void registerExceptionThrower(ExceptionThrower exceptionThrower){
		Ntro.exceptionThrower = exceptionThrower;
	}

	public static ExceptionThrower exceptions(){
		return Ntro.exceptionThrower;
	}

	/* </ExceptionThrower> */
	
	
	
	
	/* <Asserter> */
	
	private static Asserter asserter = new AsserterNull();
	
	static void registerAsserter(Asserter asserter){
		Ntro.asserter = asserter;
	}

	public static Asserter asserter(){
		return Ntro.asserter;
	}

	/* </Asserter> */
	

	
	
	/* <ReflectionService> */
	
	private static ReflectionService reflectionService = new ReflectionServiceNull();
	
	static void registerReflectionService(ReflectionService reflectionService){
		Ntro.reflectionService = reflectionService;
	}

	public static ReflectionService reflectionService(){
		return Ntro.reflectionService;
	}

	/* </ReflectionService> */
	
	
	
	
	
	/* <StackAnalyzer> */
	
	private static StackAnalyzer stackAnalyzer = new StackAnalyzerNull();
	
	static void registerStackAnalyzer(StackAnalyzer stackAnalyzer){
		Ntro.stackAnalyzer = stackAnalyzer;
	}

	public static StackAnalyzer stackAnalyzer(){
		return Ntro.stackAnalyzer;
	}

	/* </StackAnalyzer> */
	
	
	

	/* <GraphWriter> */
	
	private static Class<? extends GraphWriter> graphWriterClass = GraphWriterNull.class;

	static void registerGraphWriter(Class<? extends GraphWriter> graphWriterClass){
		Ntro.graphWriterClass = graphWriterClass;
	}

	public static GraphWriter graphWriter(){
		return Factory.newInstance(graphWriterClass);
	}

	/* </GraphWriter> */
	
	
	

	/* <Collections> */
	
	private static Collections collections = new CollectionsNull();

	static void registerCollections(Collections collections){
		Ntro.collections = collections;
	}

	public static Collections collections(){
		return Ntro.collections;
	}

	/* </Collections> */

	
	
	
	/* <Time> */
	
	private static Time time = new TimeNull();

	static void registerTime(Time time){
		Ntro.time = time;
	}

	public static Time time(){
		return Ntro.time;
	}

	/* </Time> */
	
	
	
	
	/* <Random> */
	
	private static Random random = new RandomNull();

	static void registerRandom(Random random){
		Ntro.random = random;
	}

	public static Random random(){
		return Ntro.random;
	}

	/* </Random> */
	
	
	
}
