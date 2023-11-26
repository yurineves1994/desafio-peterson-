import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IEmpresa } from 'src/app/interfaces/IEmpresa';
import { IFaleConosco } from 'src/app/interfaces/IFaleConosco';
import { EmpresaService } from 'src/app/services/empresa.service';

@Component({
  selector: 'app-form-contato',
  templateUrl: './form-contato.component.html',
  styleUrls: ['./form-contato.component.scss'],
})
export class FormContatoComponent implements OnInit {
  errorMessage: string = '';
  public listEmpresa: IEmpresa[] = [];

  form: FormGroup = this.formBuilder.group({
    nomePessoa: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    assunto: ['', Validators.required],
    mensagem: ['', Validators.required],
    IdEmpresa: ['', Validators.required],
  });

  constructor(
    private empresaService: EmpresaService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.empresaService.empresaList().subscribe({
      next: (res) => {
        this.listEmpresa = res;
        this.form.addControl('IdEmpresa', this.formBuilder.control(''));
      },
      error: (err) => this.errorMessage = err.error.error,
    });
  }

  public enviarPergunta(empresa: IFaleConosco, id: number) {
    this.empresaService.enviarPergunta(empresa, id).subscribe({
      next: (res) => res,
      error: (err) => this.errorMessage = err.error.error,
    });
  }

  public submitForm() {
    const { IdEmpresa, ...pergunta } = this.form.value;

    if (this.form.valid) {
      this.enviarPergunta(pergunta, IdEmpresa);
    }
  }
}
