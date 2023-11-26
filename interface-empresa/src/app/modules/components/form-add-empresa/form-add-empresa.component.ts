import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { IEmpresa } from 'src/app/interfaces/IEmpresa';
import { EmpresaService } from 'src/app/services/empresa.service';

@Component({
  selector: 'app-form-add-empresa',
  templateUrl: './form-add-empresa.component.html',
  styleUrls: ['./form-add-empresa.component.scss'],
})
export class FormAddEmpresaComponent implements OnInit {
  errorMessage: string = '';
  form: FormGroup = this.formBuilder.group({
    razaoSocial: ['', Validators.required],
    nomeFantasia: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    cnpj: ['', Validators.required],
  });

  constructor(
    private empresaService: EmpresaService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {}

  public addEmpresa(empresa: IEmpresa) {
    this.empresaService.addEmpresa(empresa).subscribe({
      next: (res) => {
        res;
        this.router.navigate(['/']);
      },
      error: (err) => (this.errorMessage = err.error.error),
    });
  }

  public submitForm() {
    if (this.form.valid) {
      this.addEmpresa(this.form.value);
    }
  }
}
