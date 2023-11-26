import { Component, Input, OnInit } from '@angular/core';
import { IEndereco } from 'src/app/interfaces/IEmpresa';
import { EmpresaService } from 'src/app/services/empresa.service';

import { saveAs } from 'file-saver';
import * as XLSX from 'xlsx';

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

  exportEmpresasToExcel() {
    let listEnderecos = this.enderecos;

    let wb = XLSX.utils.book_new();

    let ws_data = [
      ['Id', 'Logadouro', 'Bairro', 'Cidade', 'Estado', 'Latitude', 'Logitude', 'CEP' ],
      ...listEnderecos.map((empresa:IEndereco) => [
        empresa.id,
        empresa.logradouro,
        empresa.bairro,
        empresa.cidade,
        empresa.estado,
        empresa.latitude,
        empresa.longitude,
        empresa.cep,
      ]),
    ];

    let ws = XLSX.utils.aoa_to_sheet(ws_data);

    XLSX.utils.book_append_sheet(wb, ws, 'Empresas');

    let wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'binary' });

    let blob = new Blob([this.s2ab(wbout)], {
      type: 'application/octet-stream',
    });

    saveAs(blob, `enderecos-clienteId-${this.empresaId - 1}.xlsx`);
  }

  s2ab(s: any) {
    let buf = new ArrayBuffer(s.length);
    let view = new Uint8Array(buf);
    for (let i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xff;
    return buf;
  }
}
