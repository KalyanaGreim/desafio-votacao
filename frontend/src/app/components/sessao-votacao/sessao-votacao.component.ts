import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PautaService } from 'src/app/services/pauta.service';

@Component({
  selector: 'app-sessao-votacao',
  templateUrl: './sessao-votacao.component.html',
  styleUrls: ['./sessao-votacao.component.scss'],
})
export class SessaoVotacaoComponent implements OnInit {
  pauta: any = {}; // Objeto que representa a pauta
  voto: boolean | undefined;
  cpfAssociado?: string;
  nomeAssociado?: string;

  constructor(
    private route: ActivatedRoute,
    private pautaService: PautaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const pautaId = this.route.snapshot.params['id']; // Obtém o id da pauta da rota
    this.carregarDetalhesPauta(pautaId);
  }

  carregarDetalhesPauta(pautaId: number): void {
    this.pautaService.obterPauta(pautaId).subscribe(
      (pauta) => {
        this.pauta = pauta;
      },
      (error) => {
        console.error('Erro ao carregar detalhes da pauta', error);
      }
    );
  }

  registrarVoto(): void {
    if (this.voto !== undefined && this.cpfAssociado && this.nomeAssociado) {
        const pautaId = this.pauta.id;
        this.pautaService
            .registrarVotoNaPauta(
                this.cpfAssociado,
                pautaId,
                this.voto,
                this.nomeAssociado
            )
            .subscribe(
                (resposta) => {
                    console.log('Voto registrado com sucesso:', resposta);
                    alert(resposta.message);
                    this.router.navigate(['/home']);
                },
                (erro) => {
                    console.error('Erro ao registrar voto:', erro);
                    let errorMessage = 'Ocorreu um erro ao registrar o voto. Por favor, tente novamente.';
                    if (erro.status === 400) {
                        errorMessage = 'Associado informado já votou nesta pauta!';
                    } else if (erro.error && erro.error.message) {
                        errorMessage = erro.error.message;
                    }
                    alert(errorMessage);
                    this.router.navigate(['/home']);
                }
            );
    } else {
        console.error('Selecione uma opção de voto e informe o CPF');
    }
}

}
