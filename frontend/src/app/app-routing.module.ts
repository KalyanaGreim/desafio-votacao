import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { CreatePautaComponent } from './components/create-pauta/create-pauta.component';
import { SessaoVotacaoComponent } from './components/sessao-votacao/sessao-votacao.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'create-pauta', component: CreatePautaComponent },
  { path: 'sessao-votacao/:id', component: SessaoVotacaoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
