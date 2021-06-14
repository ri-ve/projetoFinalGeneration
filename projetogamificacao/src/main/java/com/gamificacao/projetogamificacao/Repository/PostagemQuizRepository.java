package com.gamificacao.projetogamificacao.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamificacao.projetogamificacao.Models.PostagemQuiz;

@Repository
public interface PostagemQuizRepository extends JpaRepository<PostagemQuiz, Long> {
}
