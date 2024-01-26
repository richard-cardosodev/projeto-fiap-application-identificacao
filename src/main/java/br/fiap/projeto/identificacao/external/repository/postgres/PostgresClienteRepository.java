package br.fiap.projeto.identificacao.external.repository.postgres;

import br.fiap.projeto.identificacao.external.repository.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostgresClienteRepository
        extends JpaRepository<ClienteEntity, String> {
    List<ClienteEntity> findAllByDataExclusaoIsNull();
    Optional<ClienteEntity> findByCpfAndDataExclusaoIsNull(String cpf);
    Optional<ClienteEntity> findByEmailAndDataExclusaoIsNull(String email);
    Optional<ClienteEntity> findByCodigoAndDataExclusaoIsNull(String codigo);
}
