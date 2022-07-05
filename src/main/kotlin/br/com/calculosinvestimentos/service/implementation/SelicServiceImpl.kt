package br.com.calculosinvestimentos.service.implementation

import br.com.calculosinvestimentos.client.SelicDadosDiariosClient
import br.com.calculosinvestimentos.dto.SelicDTO
import br.com.calculosinvestimentos.service.SelicService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class SelicServiceImpl : SelicService {

    private final val dateFormatBR: String = "dd/MM/yyyy"

    @Autowired
    lateinit var selicDadosDiariosClient: SelicDadosDiariosClient

    override fun dadosDiariosSelic(selicDTO: SelicDTO?): Any {

        var page = "1"

        if (selicDTO != null) {
            val dtInicial: Date = SimpleDateFormat(dateFormatBR).parse(selicDTO?.dataInicial)
            val dtFinal: Date = SimpleDateFormat(dateFormatBR).parse(selicDTO?.dataFinal)

            if (dtInicial > dtFinal) {
                throw Throwable("Data inicial não pode ser maior que data final.")
            }

            val qtDiasEntreDatas = ChronoUnit.DAYS.between(
                dtInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                dtFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            )

            if (qtDiasEntreDatas > 3652) {
                throw Throwable("Intervalo entre datas deve ser menor que 10 anos.")
            }

            val listDadosDiariosSelic: LinkedHashSet<Any> = LinkedHashSet()
            var item: Long = 1

            while (item < qtDiasEntreDatas) {
                page = item.toString()
                val selicDadosDiarios = selicDadosDiariosClient.obterTaxaDiarias(selicDTO, page)
                if ((selicDadosDiarios["registros"] as ArrayList<*>).size > 0) {
                    listDadosDiariosSelic.add(selicDadosDiarios)
                    item += 1
                } else {
                    item = qtDiasEntreDatas
                }
            }

            return listDadosDiariosSelic
        } else {
            val localDate: LocalDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern(dateFormatBR)
            val data: String = formatter.format(localDate)
            val newSelicDTO = SelicDTO(data, data)
            return selicDadosDiariosClient.obterTaxaDiarias(newSelicDTO, page)
        }
    }
}