package br.com.igti.edugame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.igti.edugame.domain.Inventario;
import br.com.igti.edugame.domain.InventarioId;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, InventarioId>{

}
