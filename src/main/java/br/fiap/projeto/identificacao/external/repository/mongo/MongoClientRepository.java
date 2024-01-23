package br.fiap.projeto.identificacao.external.repository.mongo;

import br.fiap.projeto.identificacao.external.repository.mongo.collection.ClienteDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoClientRepository extends MongoRepository<ClienteDocument, String> {
    List<ClienteDocument> findAllByDataExclusaoIsNull();
    Optional<ClienteDocument> findByCpfAndDataExclusaoIsNull(String cpf);
    Optional<ClienteDocument> findByEmailAndDataExclusaoIsNull(String email);
    Optional<ClienteDocument> findByCodigoAndDataExclusaoIsNull(String codigo);
}
