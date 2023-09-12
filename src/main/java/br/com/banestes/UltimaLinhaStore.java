package br.com.banestes;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class UltimaLinhaStore {

	private static final String TOPIC_FILE_LINE_CONTROL = "topico.controle.linha";
	
	public static final String STORE_NAME = "controleLinha"; 
	
	@Produces
	public Topology buildStore() {
		StreamsBuilder builder = new StreamsBuilder();
		KeyValueBytesStoreSupplier store = Stores.persistentKeyValueStore(STORE_NAME);
		builder.table(TOPIC_FILE_LINE_CONTROL, Consumed.with(Serdes.String(), Serdes.Integer()), Materialized.<String, Integer>as(store));
		return builder.build();
	}
	
	
	
	
	
	
	
    
}
