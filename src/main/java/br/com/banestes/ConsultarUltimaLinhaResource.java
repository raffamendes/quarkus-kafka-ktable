package br.com.banestes;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api")
public class ConsultarUltimaLinhaResource {
	
	@Inject
	KafkaStreams streams;
	
	@GET
	@Path("/hello")
	public String hello() {
		return "hello";
	}
	
	@GET
	@Path("/consulta/{nomeArquivo}")
	public Integer consultaUltimaLinha(String nomeArquivo) {
		return getStateStore().get(nomeArquivo);
	}
	
	
	public ReadOnlyKeyValueStore<String, Integer> getStateStore() {
		return streams.store(StoreQueryParameters.fromNameAndType(UltimaLinhaStore.STORE_NAME, QueryableStoreTypes.keyValueStore()));
	}

}
