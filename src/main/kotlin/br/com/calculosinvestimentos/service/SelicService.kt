package br.com.calculosinvestimentos.service

import br.com.calculosinvestimentos.dto.SelicDTO

interface SelicService {

    fun dadosDiariosSelic(selicDTO: SelicDTO?): Any
}