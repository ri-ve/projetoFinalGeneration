package com.gamificacao.projetogamificacao.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamificacao.projetogamificacao.Models.Atividades;

@Repository
public interface AtividadesRepository extends JpaRepository<Atividades, Long> {	
}
