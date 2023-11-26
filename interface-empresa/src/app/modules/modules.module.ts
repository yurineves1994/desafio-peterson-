import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './pages/home/home.component';
import { ListEmpresasComponent } from './components/list-empresas/list-empresas.component';
import { HeaderComponent } from './components/header/header.component';
import { AddEmpresaComponent } from './pages/add-empresa/add-empresa.component';
import { FormAddEmpresaComponent } from './components/form-add-empresa/form-add-empresa.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './pages/login/login.component';
import { FormLoginComponent } from './components/form-login/form-login.component';
import { HttpClientModule } from '@angular/common/http';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
import { ContatoComponent } from './pages/contato/contato.component';
import { FormContatoComponent } from './components/form-contato/form-contato.component';
import { AddAddressModalComponent } from './components/add-address-modal/add-address-modal.component';
import { ListAddressesModalComponent } from './components/list-addresses-modal/list-addresses-modal.component';

@NgModule({
  declarations: [
    HomeComponent,
    ListEmpresasComponent,
    HeaderComponent,
    AddEmpresaComponent,
    FormAddEmpresaComponent,
    LoginComponent,
    FormLoginComponent,
    ContatoComponent,
    FormContatoComponent,
    AddAddressModalComponent,
    ListAddressesModalComponent,
  ],
  providers: [provideNgxMask()],
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule, NgxMaskDirective,
    NgxMaskPipe],
})
export class ModulesModule {}
