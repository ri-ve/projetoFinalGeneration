import { InscricaoGrupo } from "./InscricaoGrupo"
import { PostagemQuiz } from "./PostagemQuiz"
import { Usuario } from "./Usuario"

export class Grupo{
    public id: number
    public nome: string
    public descricao: string
    public foto: string
    public icone: string
    public listaInscricaoGU: InscricaoGrupo[] //é set
    public listaPostQuiz: PostagemQuiz[]
    public criador: Usuario

}