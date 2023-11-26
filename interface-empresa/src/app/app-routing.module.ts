import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/pages/home/home.component';
import { AddEmpresaComponent } from './modules/pages/add-empresa/add-empresa.component';
import { LoginComponent } from './modules/pages/login/login.component';
import { ContatoComponent } from './modules/pages/contato/contato.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'cadastro',
    component: AddEmpresaComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'contato',
    component: ContatoComponent,
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
