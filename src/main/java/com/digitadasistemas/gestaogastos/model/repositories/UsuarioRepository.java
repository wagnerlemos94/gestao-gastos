package com.digitadasistemas.gestaogastos.model.repositories;

import com.digitadasistemas.gestaogastos.model.params.LancamentoUpdateStatusParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.digitadasistemas.gestaogastos.model.entities.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByLogin(String login);

	@Modifying
	@Transactional
	@Query(value = "update usuario u set ativo = :ativo " +
			" where u.id = :id ", nativeQuery = true)
	int atualizarStatus(boolean ativo, Long id);
}
