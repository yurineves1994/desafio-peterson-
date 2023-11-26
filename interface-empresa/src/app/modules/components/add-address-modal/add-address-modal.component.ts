import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IEndereco } from 'src/app/interfaces/IEmpresa';
import { EmpresaService } from 'src/app/services/empresa.service';

@Component({
  selector: 'app-add-address-modal',
  templateUrl: './add-address-modal.component.html',
  styleUrls: ['./add-address-modal.component.scss'],
})
export class AddAddressModalComponent implements OnInit {
  errorMessage: string = '';
  @Input() empresaId: number = 0;

  constructor(
    private empresaService: EmpresaService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {}

  form: FormGroup = this.formBuilder.group({
    logradouro: ['', Validators.required],
    bairro: ['', Validators.required],
    cidade: ['', Validators.required],
    estado: ['', Validators.required],
    latitude: ['', Validators.required],
    longitude: ['', Validators.required],
    cep: ['', Validators.required],
  });

  public addEndereco(endereco: IEndereco, id: number) {
    this.empresaService.addEndereco(endereco, id).subscribe({
      next: (res) => res,
      error: (err) => this.errorMessage = err.error.error,
    });
  }

  public submitForm() {
    if (this.form.valid) {
      this.addEndereco(this.form.value, this.empresaId)
    }
  }
}
