package br.com.igti.edugame.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igti.edugame.domain.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long>{

}
