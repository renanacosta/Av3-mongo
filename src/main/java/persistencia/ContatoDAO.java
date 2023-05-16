package persistencia;

import negocio.Contato;
import negocio.Endereco;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

public class ContatoDAO {
    private String uri;
    private ConnectionString connectionString;
    private CodecRegistry pojoCodecRegistry;
    private CodecRegistry codecRegistry;
    private MongoClientSettings clientSettings;
    private String database;
    private String collection;
    // private String contatos;
    private String enderecos;

    public ContatoDAO() {

        this.uri = "mongodb://localhost:27017";
        this.connectionString = new ConnectionString(uri);

        this.pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        this.codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                pojoCodecRegistry);

        this.clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        this.database = "quatro";
        this.collection = "users";
        // this.contatos = "contatos";
        this.enderecos = "enderecos";

    }

    public void inserir(Contato contato) {

        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {

            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Contato> users = db.getCollection(this.collection,
                    Contato.class);
            users.insertOne(contato);
            // MongoCollection<Contato> contatos = db.getCollection(this.contatos,
            //         Contato.class);
            MongoCollection<Endereco> enderecos = db.getCollection(this.enderecos,
                    Endereco.class);
            // contatos.insertOne(contato);
            enderecos.insertOne(contato.getEndereco());

        }

    }

    public void deletarPorNome(String nome) {
        System.out.println("Deletando contato com o nome " + nome);
        Bson filtro = eq("Nome", nome);
        System.out.println("Filtro: " + filtro);
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {

            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Contato> users = db.getCollection(this.collection,
                    Contato.class);

            users.deleteOne(eq("nome", nome));
        }

    }

    public void update(Contato c) {

        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Contato> users = db.getCollection(this.collection,
                    Contato.class);

            users.replaceOne(eq("_id", c.getId()), c);

        }

    }

    public MongoCursor<Contato> listar() {
        MongoCursor<Contato> cursor;
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Contato> contatos = db.getCollection(this.collection,
                    Contato.class);

            cursor = contatos.find().iterator();
            return cursor;
        }

    }

    public MongoCursor<Contato> listarPorCidade(String cidade) {
        MongoCursor<Contato> cursor;
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {

            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Contato> contatos = db.getCollection(this.collection,
                    Contato.class);

            cursor = contatos.find(eq("endereco.cidade", cidade)).iterator();
            return cursor;
        }

    }

    public List<Contato> listarContatosComMaisDeUmTelefone() {
        MongoCursor<Contato> cursor;
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Contato> contatos = db.getCollection(this.collection,
                    Contato.class);

            cursor = contatos.find().iterator();
            List<Contato> meusContatos = new ArrayList<>();
            while (cursor.hasNext()) {
                Contato c = cursor.next();
                if (c.getTelefone().size() > 1) {
                    meusContatos.add(c);
                }
            }

            return meusContatos;
        }

    }

    public Contato buscarPorNome(String nome) {
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase db = mongoClient.getDatabase(this.database);
            MongoCollection<Contato> contatos = db.getCollection(this.collection, Contato.class);
            return contatos.find(eq("nome", nome)).first();
        }
    }

}
