import { EmpresaService } from './../../../services/empresa.service';
import { Component, OnInit, Input } from '@angular/core';
import { IEmpresa } from 'src/app/interfaces/IEmpresa';
import { ModalService } from 'src/app/services/modal.service';

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
}
