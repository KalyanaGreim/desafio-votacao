import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { CreatePautaComponent } from './components/create-pauta/create-pauta.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SessaoVotacaoComponent } from './components/sessao-votacao/sessao-votacao.component'; // Import HttpClientModule


@NgModule({
  declarations: [AppComponent, HomeComponent, CreatePautaComponent, SessaoVotacaoComponent],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
