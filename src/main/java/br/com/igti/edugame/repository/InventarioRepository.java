package br.com.igti.edugame.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.igti.edugame.domain.Inventario;
import br.com.igti.edugame.domain.InventarioId;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, InventarioId>{
	
	@Query("SELECT i FROM Inventario i WHERE i.inventarioId.avatarId = ?1")
	List<Inventario> findAllByAvatarId(Long avatarId);
	
	@Query("SELECT i FROM Inventario i WHERE i.inventarioId.avatarId = ?1 and i.isEquipado = true")
	List<Inventario> findAllEquippedById(Long avatarId);

}
