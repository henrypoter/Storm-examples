package storm.blueprints.BoardGame;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.trident.Stream;
import storm.trident.TridentTopology;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.tuple.Fields;


public class RecursiveTopology {
	
	private static final Logger LOG = LoggerFactory.getLogger(RecursiveTopology.class);
	
	public static StormTopology buildTopology(){
		
		TridentTopology topology =new TridentTopology();
		
		//work queue spout
		LocalQueueEmitter<GameState> workSpoutEmitter = new LocalQueueEmitter<GameState>("WorkQueue");
		LocalQueueSpout<GameState> workSpout = new LocalQueueSpout<GameState>(workSpoutEmitter);
		GameState initialState = new GameState(new Board(), new ArrayList<Board>(),"X");
		
		//scring queue spout
		LocalQueueEmitter<GameState> scoringSpoutEmitter = new LocalQueueEmitter<GameState>("ScoringQueue");
		
		Stream inputStream = topology.newStream("gamestate",workSpout);
		
		inputStream.each(new Fields("gamestate"),new isEndGame())
				   .each(new Fields("gamestate"),
						 new LocalQueuerFunction<GameState>(scoringSpoutEmitter), 
						 new Fields(""));
		
		inputStream.each(new Fields("gamestate"), new GenerateBoards(), new Fields("children"))
		           .each(new Fields("children"), new LocalQueuerFunction<GameState>(workSpoutEmitter), new Fields());
		
		return topology.build();
		
	}
	
	public static void main(String[] args) throws Exception{
		final Config conf = new Config();
		final LocalCluster cluster = new LocalCluster();
		
		LOG.info("submitting topology.");
		cluster.submitTopology("recursiveTopology", conf, RecursiveTopology.buildTopology());
		LOG.info("Topology submitted.");
		Thread.sleep(600000);
	}

}
