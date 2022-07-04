package br.com.calculosinvestimentos.dto

import lombok.Data

@Data
data class SelicDTO(
    var dataInicial: String?,
    var dataFinal: String?
)