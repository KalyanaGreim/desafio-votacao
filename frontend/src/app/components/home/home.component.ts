import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PautaService } from 'src/app/services/pauta.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  pautas: any[]=[];
  sessoes: any[] = [];
  sessaoEncerrada: boolean = false;
  mostrarResultado: boolean = false;
  resultadoVotos: any = {};

  constructor(private pautaService: PautaService, private router: Router) {}

  ngOnInit(): void {
    this.carregarPautas();
  }

  carregarPautas(): void {
    this.pautaService.listarPautas().subscribe(
      (pautas: any[]) => {
        this.pautas = pautas;
        this.verificarStatusSessoes();
      },
      (error) => {
        console.error('Erro ao carregar pautas', error);
      }
    );
  }

  irParaSessaoVotacao(pautaId: number): void {
    this.router.navigate(['/sessao-votacao', pautaId]);
  }

  iniciarVotacao(pauta: any): void {
    const pautaId = pauta.id;
    const dataInicio = pauta.dataInicio
      ? new Date(pauta.dataInicio)
      : undefined;
    const dataFim = pauta.dataFim ? new Date(pauta.dataFim) : undefined;

    if (pauta.votacaoAberta) {
      this.irParaSessaoVotacao(pautaId);
    } else {
      this.pautaService.iniciarSessao(pautaId, dataInicio, dataFim).subscribe(
        () => {
          console.log('Votação iniciada com sucesso');
          this.router.navigate(['/sessao-votacao', pautaId]);
        },
        (error) => {
          console.error('Erro ao iniciar votação', error);
        }
      );
    }
  }

  verificarStatusSessoes(): void {
    this.pautas.forEach((pauta) => {
      if (pauta.sessaoId) {
        this.pautaService.verificarStatusSessao(pauta.sessaoId).subscribe(
          (status) => {
            pauta.sessao.encerrada = status === 'A sessão de votação foi encerrada';
            pauta.votacaoAberta = !pauta.sessao.encerrada;
          },
          (error) => {
            console.error('Erro ao verificar status da sessão', error);
          }
        );
      }
    });
  }

  exibirResultadoVotacao(pautaId: number): void {
    this.pautaService.contarVotos(pautaId).subscribe(
      (resultado) => {
        this.resultadoVotos = resultado;
        this.mostrarResultado = true;
      },
      (error) => {
        console.error('Erro ao contar votos', error);
      }
    );
  }
}
