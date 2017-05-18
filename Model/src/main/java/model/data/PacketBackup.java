package model.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.connection.Packet;
import model.utils.GsonSerializer;
import model.utils.JacksonMapper;
import model.utils.MainLogger;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.IOException;

@Data
@EqualsAndHashCode(of = {"hash"})
@NoArgsConstructor
@Entity
@Table(name = "packets_backups")
public class PacketBackup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Lob
	private String json;
	
	@Column
	private Integer hash;
	
	public PacketBackup(Packet packet) {
		try {
			this.json = JacksonMapper.toJson(packet);
		} catch (JsonProcessingException e) {
			MainLogger.log(this).fatal(e);
			this.json = "";
		}
		this.hash = packet.hashCode();
	}
	
	public Packet getPacket(){
		try {
			return JacksonMapper.fromJson(json, Packet.class);
		} catch (IOException e) {
			MainLogger.log(this).fatal(e);
			return null;
		}
	}
}
