import { Component, Input, OnInit } from '@angular/core';
import { IEmpresa, IEndereco } from 'src/app/interfaces/IEmpresa';
import { EmpresaService } from 'src/app/services/empresa.service';

@Component({
  selector: 'app-list-addresses-modal',
  templateUrl: './list-addresses-modal.component.html',
  styleUrls: ['./list-addresses-modal.component.scss'],
})
export class ListAddressesModalComponent implements OnInit {
  @Input() empresaId: number = 0;
  @Input() empresas: any = [];
  enderecos: IEndereco[] = [];

  constructor(private empresaService: EmpresaService) {}

  ngOnInit(): void {
    this.enderecos = this.empresas[this.empresaId - 1].enderecos;
  }
}
