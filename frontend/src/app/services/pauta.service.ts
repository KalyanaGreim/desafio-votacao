import { Injectable } from '@angular/core';
import { environment } from 'src/enviroments/enviroments';
import { Observable } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class PautaService {
  constructor(private http: HttpClient) {}

  api = environment.api;

  criarPauta(pauta: any): Observable<any> {
    return this.http.post<any>(`${this.api}/pautas`, pauta);
  }

  listarPautas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.api}/pautas`);
  }

  iniciarSessao(
    pautaId: number,
    dataInicio?: Date,
    dataFim?: Date
  ): Observable<any> {
    let params = new HttpParams();
    if (dataInicio) {
      params = params.append('dataInicio', dataInicio.toISOString());
    }
    if (dataFim) {
      params = params.append('dataFim', dataFim.toISOString());
    }

    return this.http.post<any>(
      `${this.api}/sessoes/${pautaId}/iniciar-sessao`,
      null,
      { params }
    );
  }

  registrarVotoNaPauta(
    cpfAssociado: string,
    pautaId: number,
    voto: boolean,
    nome: string
  ): Observable<any> {
    return this.http.post<any>(`${this.api}/votos/registrar`, {
      cpfAssociado,
      pautaId,
      voto,
      nome,
    });
  }

  contarVotosNaPauta(pautaId: number): Observable<any> {
    return this.http.get<any>(`${this.api}/votos/contar/${pautaId}`);
  }

  obterPauta(pautaId: number): Observable<any> {
    const url = `${this.api}/pautas/${pautaId}`;
    return this.http.get<any>(url);
  }

  verificarStatusSessao(sessaoId: number): Observable<string> {
    return this.http.get<string>(`${this.api}/sessoes/${sessaoId}/status`);
  }

  contarVotos(pautaId: number): Observable<any> {
    return this.http.get<any>(`${this.api}/votos/contar/${pautaId}`);
  }
}
