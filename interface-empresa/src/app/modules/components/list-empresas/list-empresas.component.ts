import { EmpresaService } from './../../../services/empresa.service';
import { Component, OnInit, Input } from '@angular/core';
import { IEmpresa } from 'src/app/interfaces/IEmpresa';
import { ModalService } from 'src/app/services/modal.service';

import * as XLSX from 'xlsx';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-list-empresas',
  templateUrl: './list-empresas.component.html',
  styleUrls: ['./list-empresas.component.scss'],
})
export class ListEmpresasComponent implements OnInit {
  @Input() empresaId: number = 0;
  public listEmpresa: IEmpresa[] = [];
  public isAddAddressModalOpen = false;
  public isListAddressesModalOpen = false;
  @Input() public empresas: IEmpresa[] = [];

  constructor(
    private empresaService: EmpresaService,
    private modalService: ModalService
  ) {}

  ngOnInit(): void {
    this.empresaService.empresaList().subscribe({
      next: (res) => {
        this.listEmpresa = res;
        this.empresas = res;
      },
      error: (err) => console.log(err),
    });
  }

  openAddAddressModal(empresaId: number) {
    this.empresaId = empresaId;
    this.isAddAddressModalOpen = true;
    this.modalService.open();
  }

  closeAddAddressModal() {
    this.isAddAddressModalOpen = false;
    this.modalService.close();
  }

  openListAddressesModal(empresaId: number) {
    this.empresaId = empresaId;
    this.isListAddressesModalOpen = true;
    this.modalService.open();
  }

  closeListAddressesModal() {
    this.isListAddressesModalOpen = false;
    this.modalService.close();
  }

  exportEmpresasToExcel() {
    let empresas = this.listEmpresa;

    let wb = XLSX.utils.book_new();

    let ws_data = [
      ['Id', 'Razão Social', 'Nome Fantasia', 'E-mail', 'CNPJ'],
      ...empresas.map((empresa) => [
        empresa.id,
        empresa.razaoSocial,
        empresa.nomeFantasia,
        empresa.email,
        empresa.cnpj,
      ]),
    ];

    let ws = XLSX.utils.aoa_to_sheet(ws_data);

    XLSX.utils.book_append_sheet(wb, ws, 'Empresas');

    let wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'binary' });

    let blob = new Blob([this.s2ab(wbout)], {
      type: 'application/octet-stream',
    });

    saveAs(blob, 'empresas.xlsx');
  }

  s2ab(s: any) {
    let buf = new ArrayBuffer(s.length);
    let view = new Uint8Array(buf);
    for (let i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xff;
    return buf;
  }
}
